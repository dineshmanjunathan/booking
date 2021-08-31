package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.PayType;

public interface PayOptionRepository extends CrudRepository<PayType,Long>{

}
