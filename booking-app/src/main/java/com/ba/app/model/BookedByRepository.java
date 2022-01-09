package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.BookedBy;

public interface BookedByRepository extends CrudRepository<BookedBy,String>{

}
