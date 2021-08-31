package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Delivery;

public interface DeliveryRepository extends CrudRepository<Delivery,Long>{
	Delivery findByLRNo(Long lrNO);
	Delivery findByName(String name);

}
