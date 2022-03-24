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
	@Query(value = "select * from t_booking where From_Location=:filter1 and To_Location=:filter2 and (ogpl_no IS NULL OR ogpl_conn_point = true) ", nativeQuery =true)
	List<Booking> getOGPLlist(@Param("filter1") String filter1,@Param("filter2") String filter2);
	
	@Query(value = "select * from t_booking where From_Location=:filter1 and To_Location=:filter2 and (ogpl_no IS NULL OR (ogpl_conn_point = true and connection_point=true)) ", nativeQuery =true)
	List<Booking> getOGPLlist1(@Param("filter1") String filter1,@Param("filter2") String filter2);
	
	@Query(value = "select * from t_booking where From_Location=:filter1 and To_Location=:filter2 and (ogpl_no IS NULL OR (ogpl_conn_point = true and connection_point=false)) ", nativeQuery =true)
	List<Booking> getOGPLlist2(@Param("filter1") String filter1,@Param("filter2") String filter2);
	
	void deleteByLrNumber(String lrNumber);
	@Query(value = "select NEXTVAL('LRNUMBER_SEQ')", nativeQuery =true)
    Long getNextLRNumber();
	
	//@Query(value = "SELECT last_value FROM LRNUMBER_SEQ", nativeQuery =true)
	@Query(value = "SELECT setval('LRNUMBER_SEQ',nextval('LRNUMBER_SEQ')-1)", nativeQuery =true)
    Long getcurrentLRNumber();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET CONNECTION_POINT=false,OGPL_NO=:ogplno WHERE LR_NUMBER IN(:lrnumbers)", nativeQuery =true)
    int updateBookingOgpl(@Param("ogplno") long ogplno,@Param("lrnumbers") ArrayList<String> lrnumbers);
	
	List<Booking> findByLrNumberInAndIgplStatusIn(List<String> lrNumbers,ArrayList<String> igplstatus);
	
	List<Booking> findByIgplStatus(String igplstatus);
	
	@Query(value = "select * from t_booking where igpl_status in ('P','A') and From_Location=:filter1 and (ogpl_no IS NULL OR ogpl_conn_point = true)", nativeQuery =true)
	List<Booking> getBookingInventory(@Param("filter1") String filter1);

	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET IGPL_STATUS=:igplstatus WHERE LR_NUMBER IN(:lrnumbers)", nativeQuery =true)
    int updateBookingIgplStatus(@Param("igplstatus") String igplstatus,@Param("lrnumbers") ArrayList<String> lrnumbers);
	//List<Booking> findByLrNumberInAndIgplStatusIsNull(List<String> lrNumbers);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET IGPL_STATUS=:igplstatus WHERE LR_NUMBER=:lrnumbers", nativeQuery =true)
    int updateIgplStatusByLR(@Param("igplstatus") String igplstatus,@Param("lrnumbers") String lrnumbers);
	
	List<Booking> findByLrNumberAndIgplStatus(String lrNumbers,String igplstatus);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE T_BOOKING SET ogpl_conn_point=false WHERE LR_NUMBER =:lrnumber", nativeQuery =true)
    int updateBookingOgplConnPoint(@Param("lrnumber") String lrnumbers);
	//List<Booking> findByLrNumberInAndIgplStatusIsNull(List<String> lrNumbers);
	


}
