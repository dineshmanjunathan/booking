package com.ba.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "t_item_delivery")
public class Delivery implements Serializable {

	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fromName;
	private String toName;
	private String noOfItems;
	private String ddVehicle;
	private String status;
	private String deliveredBy;
	private String from_phone;
	private String to_phone;
	private String ogpl;
	private String fromLocation;
	private String toLocation;
	private String lRNo;
	private String deliveryBillNo;
	private String deliveryDate;
	private String unloadingCharges;
	private String doorDeliveryCharges;
	private String demurrage;
	private String paid;
	private String toPay;
	private String total;
	private String cash;
	private String refund;
	private LocalDateTime createon;
	private LocalDateTime updatedon = LocalDateTime.now();
	
	
}
