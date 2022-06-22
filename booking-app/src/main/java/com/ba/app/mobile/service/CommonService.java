package com.ba.app.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ba.app.entity.Conductor;
import com.ba.app.entity.Driver;
import com.ba.app.entity.Location;
import com.ba.app.entity.User;
import com.ba.app.entity.Vehicle;
import com.ba.app.mobile.jwt.JwtTokenUtil;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.mobile.util.CommonEnums;
import com.ba.app.mobile.util.CommonUtil;
import com.ba.app.model.ConductorRepository;
import com.ba.app.model.DriverRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.model.UserRepository;
import com.ba.app.model.VehicleRepository;

@Service
public class CommonService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private ConductorRepository conductorRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	public BookAppResponse getFromLocation(String token) {

		String userId = jwtTokenUtil.getUsernameFromToken(token);

		if (Objects.nonNull(userId) && !isAdmin(userId)) {

			Location data = userRepository.findByUserIdIgnoreCase(userId).getLocation();
			List<Location> listData = new ArrayList<Location>();
			listData.add(data);
			return CommonUtil.successResponse(listData, null);
		}

		return CommonUtil.successResponse(locationRepository.getAllLocation(), null);
	}
	
	public BookAppResponse getToLocation(String token) {

		String userId = jwtTokenUtil.getUsernameFromToken(token);
		List<Location> listLocation=locationRepository.getAllLocation();

		if (Objects.nonNull(userId) && !isAdmin(userId)) {

			Location data = userRepository.findByUserIdIgnoreCase(userId).getLocation();
			
			listLocation=listLocation.stream().filter(r->r.getId()!=data.getId()).collect(Collectors.toList());
			
			return CommonUtil.successResponse(listLocation, null);
		}

		return CommonUtil.successResponse(listLocation, null);
	}

	private Boolean isAdmin(String userId) {
		return userId.equalsIgnoreCase(CommonEnums.ADMIN_USER.get());
	}

	public BookAppResponse getContainer() {
		Iterable<Conductor> locaIterable = conductorRepository.findAll();		
		if(Objects.isNull(locaIterable))
		{
			return CommonUtil.successResponse(null, "Data Not Found");
		}
		return CommonUtil.successResponse(locaIterable, null);
	}
	
	public BookAppResponse getDriver() {
		Iterable<Driver> locaIterable = driverRepository.findAll();
		if(Objects.isNull(locaIterable))
		{
			return CommonUtil.successResponse(null, "Data Not Found");
		}
		return CommonUtil.successResponse(locaIterable, null);

	}

	public BookAppResponse getVehicle() {
		Iterable<Vehicle> locaIterable = vehicleRepository.findAll();
		if(Objects.isNull(locaIterable))
		{
			return CommonUtil.successResponse(null, "Data Not Found");
		}
		return CommonUtil.successResponse(locaIterable, null);
	}




}
