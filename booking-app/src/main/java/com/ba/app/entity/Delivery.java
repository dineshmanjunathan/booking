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
@Table(name = "t_delivery")
public class Delivery implements Serializable {

	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String paid;
	private Long noOfItems;
	private String deliveredBy;
	//private String deliveredBy;
	private Long no;
	private String ogpl;
	private String fromDate;
	private String toDate;
	private Long lRNo;
	private Long deliveryBillNo;
	private String deliveryDate;
	private String toPay;
	private String hamali;
	private String unloadingCharges;
	private String doorDeliveryCharges;
	private String demurrage;
	private String others;
	private String status;
	private String refund;
	private String ddVehicle;
	private String total;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	
	
}
