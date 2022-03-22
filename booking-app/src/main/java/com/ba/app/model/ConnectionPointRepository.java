package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.ConnectionPoint;

public interface ConnectionPointRepository extends CrudRepository<ConnectionPoint, String> {

	ConnectionPoint findByFromLocationAndToLocation(String filter1, String filter2);
	
	ConnectionPoint findByFromLocationAndCheckPoint(String filter1, String filter2);
}
