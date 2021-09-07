package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Location;

public interface LocationRepository extends CrudRepository<Location,String>{

}
