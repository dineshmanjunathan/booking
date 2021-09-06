package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.OutgoingParcel;

public interface OutgoingParcelRepository extends CrudRepository<OutgoingParcel, String> {
	
	OutgoingParcel findByFromLocationAndToLocationAndBookedOn(String fromLocation,String toLocation,String bookedOn);

}
