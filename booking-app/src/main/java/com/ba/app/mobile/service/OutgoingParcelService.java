package com.ba.app.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Booking;
import com.ba.app.entity.ConnectionPoint;
import com.ba.app.entity.OutgoingParcel;
import com.ba.app.mobile.dto.OutgoingParcelRequest;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.mobile.util.CommonUtil;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.ConnectionPointRepository;
import com.ba.app.model.OutgoingParcelRepository;
import com.ba.app.vo.OutgoingParcelVo;
import com.ba.utils.Utils;

@Service
public class OutgoingParcelService {

	@Autowired
	private ConnectionPointRepository connectionPointRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private OutgoingParcelRepository outgoingParcelRepository;

	public BookAppResponse importOutgoingParcel(OutgoingParcelRequest outgoingParcelRequest) {
		try {
			String fromLocation = outgoingParcelRequest.getFromLocation();
			String toLocation = outgoingParcelRequest.getToLocation();

			ConnectionPoint connectionPoint = connectionPointRepository.findByFromLocationAndCheckPoint(fromLocation,
					toLocation);
			List<Booking> connOutgoingList = null;
			List<Booking> outgoingList = null;
			outgoingList = bookingRepository.getOGPLlist(fromLocation, toLocation);
			if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {
				connOutgoingList = bookingRepository.getOGPLlist1(fromLocation, connectionPoint.getToLocation());

			} else {
				connectionPoint = connectionPointRepository.findByToLocationAndCheckPoint(toLocation, fromLocation);
				if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {
					connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
							fromLocation);

				} else {
					outgoingList = new ArrayList<Booking>();
					connOutgoingList = bookingRepository.getOGPLlist3(fromLocation, toLocation);

					if (Objects.nonNull(connOutgoingList.get(0).getOgplNo())) {

						connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
								fromLocation);
					}

				}
			}

			outgoingList.addAll(connOutgoingList);

			return CommonUtil.successResponse(outgoingList, null);

		} catch (Exception e) {
			return CommonUtil.successResponse(null, "Data Not Found");
		}
	}

	public BookAppResponse saveOutgoingParcel(OutgoingParcelVo outgoingParcelVo) {
		try {
			Boolean ogplStatus = false;
			long ogplNo = 0;
			Boolean connectionPonitStatus = false;
			OutgoingParcel outgoingParcel = new OutgoingParcel();

			BeanUtils.copyProperties(outgoingParcelVo, outgoingParcel);

			if (Long.parseLong(outgoingParcelVo.getOgplNo()) == 0 || Objects.isNull(outgoingParcelVo.getOgplNo())) {
				ogplNo = Utils.getOrderNumber();
			} else {
				ogplNo = Long.parseLong(outgoingParcelVo.getOgplNo());

				ogplStatus = true;

			}

			List<Booking> outgoingList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
			for (Booking booking : outgoingList) {
				connectionPonitStatus = false;

				ConnectionPoint connectionPoint = null;
				if (outgoingParcel != null && outgoingParcel.getFromLocation() != null) {

					connectionPoint = connectionPointRepository.findByFromLocationAndToLocation(
							outgoingParcel.getFromLocation(), outgoingParcel.getToLocation());

					if (connectionPoint != null) {
						connectionPonitStatus = true;
					}

					bookingRepository.updateConnectionPointStatus(booking.getLrNumber(), connectionPonitStatus);

					if (booking.isConnectionPoint() && connectionPoint != null
							&& connectionPoint.getCheckPoint() != null) {
						outgoingParcel.setToLocation(connectionPoint.getCheckPoint());
					} else if (!booking.isConnectionPoint() && connectionPoint != null
							&& connectionPoint.getCheckPoint() != null) {
						outgoingParcel.setFromLocation(connectionPoint.getCheckPoint());

					} else {
						// do nothing
					}

				}
				outgoingParcel.setOgplNo(ogplNo);
				String sCurrentDate = Utils.getStringCurrentDatewithFormat("YYYY-MM-dd");
				outgoingParcel.setBookedOn(sCurrentDate);

				if (ogplStatus) {
					OutgoingParcel outgoingParcelNew = outgoingParcelRepository.findByOgplNo(ogplNo);

					ArrayList<String> ogplArray = outgoingParcelNew.getOgpnoarray();
					ArrayList<String> newOgpls = outgoingParcel.getOgpnoarray();

					ArrayList<String> combinedList = Stream.of(ogplArray, newOgpls).flatMap(x -> x.stream())
							.collect(Collectors.toCollection(ArrayList::new));

					outgoingParcelNew.setOgpnoarray(
							combinedList.stream().distinct().collect(Collectors.toCollection(ArrayList::new)));

					outgoingParcelRepository.save(outgoingParcelNew);
				} else {
					outgoingParcelRepository.save(outgoingParcel);
				}
			}

			if (outgoingParcel != null) {

				List<Booking> incomeList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
				for (Booking booking : incomeList) {
					if (!booking.isConnectionPoint()) {
						bookingRepository.updateBookingOgplConnPoint(booking.getLrNumber());
					}
				}

				bookingRepository.updateBookingOgpl(ogplNo, outgoingParcel.getOgpnoarray());
			}

		} catch (Exception ex) {
			return CommonUtil.errorResponse(null, "Failed to out Parcel");
		}
		return CommonUtil.successResponse(null, "OGPL Saved");
	}
	
	public BookAppResponse getOldOgplNo(String fromLocation,String toLocation) {
		{
			try {
				
				ConnectionPoint connectionPoint = connectionPointRepository.findByFromLocationAndCheckPoint(fromLocation,
						toLocation);
				List<Booking> connOutgoingList = null;
				List<Booking> outgoingList = null;
				outgoingList = bookingRepository.getOGPLlist(fromLocation, toLocation);

				System.out.println("connectionPoint-->" + connectionPoint);
				if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {

					connOutgoingList = bookingRepository.getOGPLlist1(fromLocation, connectionPoint.getToLocation());

				} else {
					connectionPoint = connectionPointRepository.findByToLocationAndCheckPoint(toLocation, fromLocation);
					if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {
						connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
								fromLocation);

					} else {
						outgoingList = new ArrayList<Booking>();
						connOutgoingList = bookingRepository.getOGPLlist3(fromLocation, toLocation);

						if (Objects.nonNull(connOutgoingList.get(0).getOgplNo())) {

							connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
									fromLocation);
						}

					}
				}

				outgoingList.addAll(connOutgoingList);


				List<OutgoingParcel> outgoingParcelList = outgoingParcelRepository
						.findByFromLocationAndToLocationForOGPL(fromLocation, toLocation);

				return CommonUtil.successResponse(outgoingParcelList.stream().map(r->r.getOgplNo()).collect(Collectors.toList()), toLocation);

			
			} catch (Exception e) {
				return CommonUtil.errorResponse(null,"Unable To get The Data");
			}
		}

}
	}
