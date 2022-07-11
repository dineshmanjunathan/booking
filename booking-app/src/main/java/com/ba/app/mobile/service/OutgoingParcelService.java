package com.ba.app.mobile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Booking;
import com.ba.app.entity.ConnectionPoint;
import com.ba.app.entity.Inventory;
import com.ba.app.entity.OutgoingParcel;
import com.ba.app.mobile.dto.IncomingParcelRequest;
import com.ba.app.mobile.dto.IncomingResponse;
import com.ba.app.mobile.dto.OutgoingParcelRequest;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.mobile.util.CommonUtil;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.ConnectionPointRepository;
import com.ba.app.model.InventoryRepository;
import com.ba.app.model.OutgoingParcelRepository;
import com.ba.app.model.UserRepository;
import com.ba.app.vo.InventoryVo;
import com.ba.app.vo.OutgoingParcelVo;
import com.ba.utils.Utils;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OutgoingParcelService {

	@Autowired
	private ConnectionPointRepository connectionPointRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private OutgoingParcelRepository outgoingParcelRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Value("${sms.part1}")
	private String sms_part1;
	@Value("${sms.part2}")
	private String sms_part2;
	@Value("${sms.part3}")
	private String sms_part3;
	@Value("${sms.send.status}")
	private Boolean smsStatus;

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

	public BookAppResponse getOldOgplNo(String fromLocation, String toLocation) {
		{
			try {

				ConnectionPoint connectionPoint = connectionPointRepository
						.findByFromLocationAndCheckPoint(fromLocation, toLocation);
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

							connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(),
									toLocation, fromLocation);
						}

					}
				}

				outgoingList.addAll(connOutgoingList);

				List<OutgoingParcel> outgoingParcelList = outgoingParcelRepository
						.findByFromLocationAndToLocationForOGPL(fromLocation, toLocation);

				return CommonUtil.successResponse(
						outgoingParcelList.stream().map(r -> r.getOgplNo()).collect(Collectors.toList()), toLocation);

			} catch (Exception e) {
				return CommonUtil.errorResponse(null, "Unable To get The Data");
			}
		}

	}

	public BookAppResponse getIncomingOgpl(IncomingParcelRequest incomingParcelRequest) {

		try {

			List<OutgoingParcel> ogplList;
			if (Objects.nonNull(incomingParcelRequest.getBookedOn())) {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocationAndBookedOn(
						incomingParcelRequest.getFromLocation(), incomingParcelRequest.getToLocation(),
						incomingParcelRequest.getBookedOn());
			} else {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocation(
						incomingParcelRequest.getFromLocation(), incomingParcelRequest.getToLocation());
			}

			List<OutgoingParcel> finalList = new ArrayList<OutgoingParcel>();

			List<List<OutgoingParcel>> fullList = ogplList.stream()
					.collect(Collectors.groupingBy(OutgoingParcel::getOgplNo)) // Or another collector
					.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

			for (List<OutgoingParcel> data : fullList) {
				finalList.add(data.get(0));
			}

			if (!finalList.isEmpty()) {
				return CommonUtil.successResponse(finalList,
						"Parcels Imported Successfully! Please select OGPL to load Parcel");

			}

		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.errorResponse(null, "Not Able to fetch the data");
		}
		return CommonUtil.successResponse(null, "No Parcels to Import on selected Range");

	}

	public BookAppResponse loadIncomingOgpl(IncomingParcelRequest incomingParcelRequest) {
		try {
			OutgoingParcel outgoingParcel = outgoingParcelRepository.findByOgplNo(incomingParcelRequest.getOgpl());
			ArrayList<String> list = new ArrayList<String>();
			list.add("P");
			list.add("A");
			List<Booking> incomingList = bookingRepository
					.getByLrNumberInAndIgplStatusIn(outgoingParcel.getOgpnoarray(), list);

			IncomingResponse responce = new IncomingResponse();
			responce.setBooking(incomingList);
			responce.setOutgoingParcel(outgoingParcel);

			return CommonUtil.successResponse(responce,
					"Parcels listed to income for OGPL: " + incomingParcelRequest.getOgpl());

		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.errorResponse(null, "Failed to list OGPL Parcels");
		}
	}

	public BookAppResponse saveIncomingOgpl(InventoryVo inventoryVo) {

		try {

			Inventory inventory = new Inventory();
			BeanUtils.copyProperties(inventoryVo, inventory);
			inventoryRepository.save(inventory);

			if (inventory != null) {

				bookingRepository.updateBookingIgplStatus("A", inventory.getLrnoarray());

				List<Booking> incomeList = bookingRepository.findByLrNumberIn(inventory.getLrnoarray());

				for (Booking data : incomeList) {
					if (inventory.getToLocation().equals(data.getToLocation())) {

						bookingRepository.updateBookingIgplCurrentLocationStatus(inventory.getToLocation(), 2,
								data.getLrNumber());

						sendSMS(String.valueOf(data.getTo_phone()),
								bindBeforeDeliverySmscontent(data.getFromLocation(), data.getLrNumber(),
										data.getToLocation(),
										String.join(", ", userRepository.getPhone(data.getToLocation()))));

					} else {
						bookingRepository.updateBookingIgplCurrentLocationStatus(inventory.getToLocation(), 0,
								data.getLrNumber());

					}
				}

				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return CommonUtil.errorResponse(null, "Failed to income Parcel");
		}
		return CommonUtil.successResponse(null, "Parcel Incoming Sucessfully");
	}
	
	private String bindBeforeDeliverySmscontent(String fromLocation,String lrNumber,String toLocation,String phoneNumber) {
		return "Dear Customer, Your parcel has been received from "+fromLocation+". "
				+ "LR Number "+lrNumber+". Kindly collect. Contact: "+toLocation+" .PH: "+phoneNumber+" ."
				+ "Thank you. City Express Parcel";
	}
	
	public void sendSMS(String phoneNumber, String message) throws IOException {
		
		String urlString = sms_part1 + phoneNumber + sms_part2 + URLEncoder.encode(message, "UTF-8") + sms_part3;
		URL url = new URL(urlString);
		
		if(smsStatus)
		{
		URLConnection connection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			log.info("Phone Number {} Sms Reference Number {}", phoneNumber, inputLine);
		}
		bufferedReader.close();
		}
	}
}
