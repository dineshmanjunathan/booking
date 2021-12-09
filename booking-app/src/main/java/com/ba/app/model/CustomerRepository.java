package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByPhoneNumber(Long phNumber);
	Customer findByCustName(String name);
}
