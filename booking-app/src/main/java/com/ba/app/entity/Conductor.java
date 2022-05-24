package com.ba.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author USER
 *
 */
@Getter
@Setter
@Entity
@Table(name = "t_conductor")
public class Conductor implements Serializable {

	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	private String id;
	private String name;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	
	
}
