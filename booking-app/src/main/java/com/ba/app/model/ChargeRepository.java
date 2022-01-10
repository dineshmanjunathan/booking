package com.ba.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Charge;

public interface ChargeRepository extends CrudRepository<Charge,Long>{

	List<Charge> findByChargetypeAndFromLocationAndToLocation(String filter1,String filter2,String filter3);
	List<Charge> findByChargetypeAndToLocationAndFromLocation(String filter1,String filter2,String filter3);
}
