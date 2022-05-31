package com.ba.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, String>{
	List<Inventory> findByOgplNo(long ogplNo);
}
