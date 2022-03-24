package com.ba.app.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.OutgoingParcel;

public interface OutgoingParcelRepository extends CrudRepository<OutgoingParcel, String> {
	

	OutgoingParcel findByOgplNo(long ogplNo);
	
	@Query(value="select * from t_outgoing_parcel where ogpl_no not in (select ogpl_no from t_inventory ti2) and from_location =?1 and to_location =?2 and booked_on =?3",nativeQuery=true)

	List<OutgoingParcel> findByFromLocationAndToLocationAndBookedOn(String fromLocation,String toLocation,String bookedOn);
	
	@Query(value="select * from t_outgoing_parcel where ogpl_no not in (select ogpl_no from t_inventory ti2) and from_location =?1 and to_location =?2",nativeQuery=true)

	List<OutgoingParcel> findByFromLocationAndToLocation(String fromLocation,String toLocation);
	
	@Query(value="select * from t_outgoing_parcel where ogpl_no not in (select ogpl_no from t_inventory ti2) and from_location =?2 and to_location =?1",nativeQuery=true)

	List<OutgoingParcel> findByFromLocationAndToLocation();
	
	@Query(value = "select o.booked_on,o.from_location,o.to_location,o.ogpl_no,o.vehicle_no,o.driver,o.conductor,o.prepared_by, b.pay_option as Pay_Type, sum(b.freightvalue) as freight_value, sum(b.loadingcharges) as loading_charges, sum(b.doorpickcharges) as doorpickcharges, sum(b.paid) as total_paid, sum(b.topay) as total_topay,b.Lr_number,ROW_NUMBER () OVER (ORDER BY o.booked_on) as id from t_outgoing_parcel o ,t_booking b where o.ogpl_no = b.ogpl_no group by o.booked_on,o.from_location,o.to_location,o.ogpl_no,o.vehicle_no,o.driver,o.conductor,o.prepared_by, b.pay_option,b.loadingchargespay,b.doorpickchargespay,b.Lr_number", nativeQuery = true)

	LinkedList<Object[]> findAllOgplRecord();
}
