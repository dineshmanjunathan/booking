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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	private String userId;
	@JsonInclude(Include.NON_NULL)
	private String name;
	@JsonInclude(Include.NON_NULL)
	private String email;
	@JsonInclude(Include.NON_NULL)
	private Long phonenumber;
	@JsonInclude(Include.NON_NULL)
	private String password;
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime createon = LocalDateTime.now();
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime updatedon = LocalDateTime.now();
	@JsonInclude(Include.NON_NULL)
	private String role;
	@JsonInclude(Include.NON_NULL)
	private String memberStatus;
	@OneToOne()
	@JoinColumn(name = "location_code")
	@JsonInclude(Include.NON_NULL)
	private Location location;
	@JsonInclude(Include.NON_NULL)
	private String loginStatus;
	
	@Transient
	@JsonInclude(Include.NON_NULL)
	private String token;
	
		
}
