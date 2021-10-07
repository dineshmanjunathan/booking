package com.ba.app.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Delivery;

@Service
public interface DeliveryListRepository extends PagingAndSortingRepository<Delivery, Long> {
	
    

}
