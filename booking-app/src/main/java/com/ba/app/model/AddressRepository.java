package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
