package com.ba.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Charge;

public interface ChargeRepository extends CrudRepository<Charge,Long>{

	List<Charge> findByFromLocationAndToLocation(String filter1,String filter2);
	Charge findByFromLocationAndToLocationAndChargetype(String filter1,String filter2,String filter3);
	List<Charge> findByChargetypeAndFromLocationAndToLocation(String filter1,String filter2,String filter3);
	List<Charge> findByChargetypeAndToLocationAndFromLocation(String filter1,String filter2,String filter3);
	
	@Query(value="select * from t_charge order by from_location,to_location",nativeQuery = true)
	List<Charge> getAllChargeData();
}
