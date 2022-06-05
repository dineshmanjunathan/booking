package com.ba.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Location;

public interface LocationRepository extends CrudRepository<Location,String>{

	Optional<Location> findByLocation(String location);
	
	
	@Query(value="select * from t_location",nativeQuery = true)
	List<Location> getAllLocation();
	
}
