package com.ba.app.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	@Query(value = "select distict phoneNumber from t_customer where phone_number = :filter1 and customer_type=:filter2", nativeQuery =true)
	Long findByPhoneNumber(Long filter1,String filter2);
	@Query(value = "select distict custName from t_customer where cust_name = :filter1 and customer_type=:filter2", nativeQuery =true)
	String findByCustName(String filter1,String filter2);
	
	@Query(value = "select * from t_customer where phone_number = :filter1 and customer_type=:filter2 limit 1", nativeQuery =true)
	Customer findByAllPhoneNumber(Long filter1,String filter2);
}
