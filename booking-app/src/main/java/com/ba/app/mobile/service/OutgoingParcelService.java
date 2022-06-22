package com.ba.app.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

@Service
public class OutgoingParcelService {

	@Autowired
	private ConnectionPointRepository connectionPointRepository;
	@Autowired
	private BookingRepository bookingRepository;

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

}
