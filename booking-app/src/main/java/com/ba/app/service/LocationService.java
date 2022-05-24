package com.ba.app.service;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.ba.app.entity.Location;
import com.ba.app.model.LocationRepository;

@Service
public class LocationService {
	@Autowired	
	private LocationRepository locationRepository;
	public void saveLocation(Location location) {
		locationRepository.save(location);
		
	}

}