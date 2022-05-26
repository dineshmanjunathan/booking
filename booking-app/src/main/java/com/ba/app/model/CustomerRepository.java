package com.ba.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	@Query(value = "select distinct phone_number from t_customer where phone_number = :filter1", nativeQuery =true)
	Long findByPhoneNumber(Long filter1);
	@Query(value = "select distinct cust_name from t_customer where cust_name = :filter1", nativeQuery =true)
	String findByCustName(String filter1);
	
	@Query(value = "select * from t_customer where phone_number = :filter1 limit 1", nativeQuery =true)
	Customer findByAllPhoneNumber(Long filter1);
	
	
	List<Customer> findAllByOrderByIdAsc();
}
