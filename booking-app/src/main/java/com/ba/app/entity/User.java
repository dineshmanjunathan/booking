package com.ba.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private String name;
	private String email;
	private Long phonenumber;
	private String password;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	private String role;
	private String memberStatus;
	@OneToOne()
	@JoinColumn(name = "location_id")
	private Location location;
	private String loginStatus;
		
}
