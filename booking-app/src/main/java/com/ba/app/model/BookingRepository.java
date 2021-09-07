package com.ba.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Booking;

@Service
public interface BookingRepository extends CrudRepository<Booking, String> {
	
	List<Booking> findByFromLocationAndToLocationAndBookedOn(String fromLocation,String toLocation,String bookedOn);
	Booking findByLrNumber(String lrNumber);
	List<Booking> findByLrNumber(List<String> lrNumbers);
	Booking findById(Long id);

	@Query(value = "select NEXTVAL('LRNUMBER_SEQ')", nativeQuery =true)
    Long getNextLRNumber();
	
	//@Query(value = "SELECT last_value FROM LRNUMBER_SEQ", nativeQuery =true)
	@Query(value = "SELECT setval('LRNUMBER_SEQ',nextval('LRNUMBER_SEQ')-1)", nativeQuery =true)
    Long getcurrentLRNumber();
	
}
