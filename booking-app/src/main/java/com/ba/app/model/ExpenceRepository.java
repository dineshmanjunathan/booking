package com.ba.app.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ba.app.entity.Expense;

public interface ExpenceRepository extends CrudRepository<Expense,Long>{
	
	@Query(value = "select NEXTVAL('EXPENCENUMBER_SEQ')", nativeQuery =true)
    Long getNextExpenceNumber();
	
	@Query(value = "SELECT setval('EXPENCENUMBER_SEQ',nextval('EXPENCENUMBER_SEQ')-1)", nativeQuery =true)
    Long getcurrentExpenceNumber();

}

