package com.ba.app.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Location;

public interface LocationRepository extends CrudRepository<Location,String>{

	Optional<Location> findByLocation(String location);
	
}
