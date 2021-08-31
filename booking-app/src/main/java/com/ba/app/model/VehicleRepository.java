package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle,Long>{

}
