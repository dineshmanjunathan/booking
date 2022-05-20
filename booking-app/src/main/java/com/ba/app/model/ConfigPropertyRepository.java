package com.ba.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ba.app.entity.ConfigProperty;

public interface ConfigPropertyRepository extends CrudRepository<ConfigProperty, String> {

}
