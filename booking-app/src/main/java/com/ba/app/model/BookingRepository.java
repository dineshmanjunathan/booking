package com.ba.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ba.app.entity.Booking;

@Service
public interface BookingRepository extends CrudRepository<Booking, Long> {
	
	List<Booking> findByFromLocationAndToLocationAndBookedOnAndOgplNoIsNull(String fromLocation,String toLocation,String bookedOn);
	Booking findByLrNumber(String lrNumber);
	List<Booking> findByLrNumberIn(List<String> lrNumbers);
	Optional<Booking> findById(Long id);
	List<Booking> findByFromLocationAndToLocationAndOgplNoIsNull(String fromLocation,String toLocation);

	@Query(value = "select NEXTVAL('LRNUMBER_SEQ')", nativeQuery =true)
    Long getNextLRNumber();
	
	//@Query(value = "SELECT last_value FROM LRNUMBER_SEQ", nativeQuery =true)
	@Query(value = "SELECT setval('LRNUMBER_SEQ',nextval('LRNUMBER_SEQ')-1)", nativeQuery =true)
    Long getcurrentLRNumber();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET OGPL_NO=:ogplno WHERE LR_NUMBER IN(:lrnumbers)", nativeQuery =true)
    int updateBookingOgpl(@Param("ogplno") long ogplno,@Param("lrnumbers") ArrayList<String> lrnumbers);
	
	List<Booking> findByLrNumberInAndIgplStatus(List<String> lrNumbers,String igplstatus);
	
	List<Booking> findByIgplStatus(String igplstatus);
	List<Booking> findByIgplStatusAndOgplNoIsNull(String igplstatus);

	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET IGPL_STATUS=:igplstatus WHERE LR_NUMBER IN(:lrnumbers)", nativeQuery =true)
    int updateBookingIgplStatus(@Param("igplstatus") String igplstatus,@Param("lrnumbers") ArrayList<String> lrnumbers);
	//List<Booking> findByLrNumberInAndIgplStatusIsNull(List<String> lrNumbers);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET IGPL_STATUS=:igplstatus WHERE LR_NUMBER=:lrnumbers", nativeQuery =true)
    int updateIgplStatusByLR(@Param("igplstatus") String igplstatus,@Param("lrnumbers") String lrnumbers);


}
