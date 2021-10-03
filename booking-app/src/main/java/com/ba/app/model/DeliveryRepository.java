package com.ba.app.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Delivery;
import com.ba.app.vo.BookingVo;

public interface DeliveryRepository extends CrudRepository<Delivery,Long>{
	Delivery findByLRNo(String lrNO);
	Delivery findByName(String name);
	
	
	//@Query(value="SELECT new DeliveryDto(tb.to_name) from t_booking tb join t_inventory ti on tb.lr_number =?1 and tb.ogpl_no =ti.ogpl_no",nativeQuery=true)
	@Query(value="SELECT tb.* from t_booking tb join t_inventory ti on (tb.lr_number =?1 and tb.ogpl_no =ti.ogpl_no)",nativeQuery=true)

	BookingVo findDelivery(String lrNumber);
} 
