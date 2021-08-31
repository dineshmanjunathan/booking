package com.ba.app.model;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ba.app.entity.User;

@Service
public interface UserRepository extends CrudRepository<User, Long>{
		
	Optional<User> findById(Long id);
	
	User findByUserIdIgnoreCaseAndPassword(String userId, String password);
	
	List<User> findAllByOrderByIdAsc();
	
	@Transactional
	void deleteById(String id);

}