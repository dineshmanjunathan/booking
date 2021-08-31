package com.ba.app.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ba.app.entity.CountryCode;

@Service
public interface CountryCodeRepository extends CrudRepository<CountryCode, Long>{
	
	//void deleteById(String userId);

}