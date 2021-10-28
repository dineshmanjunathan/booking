package com.ba.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.OutgoingParcel;

public interface OutgoingParcelRepository extends CrudRepository<OutgoingParcel, String> {
	

	OutgoingParcel findByOgplNo(long ogplNo);
	
	@Query(value="select * from t_outgoing_parcel where ogpl_no not in (select ogpl_no from t_inventory ti2) and to_location =?2 and from_location =?1 and booked_on =?3",nativeQuery=true)

	List<OutgoingParcel> findByFromLocationAndToLocationAndBookedOn(String fromLocation,String toLocation,String bookedOn);
	
	@Query(value="select * from t_outgoing_parcel where ogpl_no not in (select ogpl_no from t_inventory ti2) and to_location =?2 and from_location =?1",nativeQuery=true)

	List<OutgoingParcel> findByFromLocationAndToLocation(String fromLocation,String toLocation);
}
