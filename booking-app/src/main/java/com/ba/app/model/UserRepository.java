package com.ba.app.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Booking;
import com.ba.app.entity.User;

@Service
public interface UserRepository extends CrudRepository<User, Long>{
		
	
	Optional<User> findById(Long id);
	
	User findByUserIdIgnoreCase(String userId);
	
	User findByUserIdIgnoreCaseAndPassword(String userId, String password);
	
	List<User> findAllByOrderByIdAsc();
	
	@Transactional
	void deleteByUserId(String id);
	
	@Query(value = "select cast(phonenumber as varchar) from t_user where location_code=:location", nativeQuery =true)
	List<String> getPhone(@Param("location") String location);
	

}