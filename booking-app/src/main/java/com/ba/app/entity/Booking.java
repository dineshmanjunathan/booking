package com.ba.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_booking")
public class Booking implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String fromLocation;
	private String toLocation ;
	private Long bookingNo;
	private String bookedOn ;
	private String lrNumber;
	private String fromName;
	private String toName;
	
	private Long from_phone;
	private String remarks;
	private BigDecimal fromValue;
	private Long invNo;
	private Long tinNo;
	private String billDesc;
	private Long billValue;
	private String bookedBy;
	private Boolean isPrinted;
	
	private Long to_phone;
	private Long item_count;
	private Long weight;
	private String freight_status;
	private BigDecimal total_charges;
	private BigDecimal paid;
	private BigDecimal topay;
	private BigDecimal cash;
	private BigDecimal refund;
	private BigDecimal total;
	private Long billNumber;
	private Long ogplNo;
	private String payOption;
	private String loadingchargespay;
	private BigDecimal loadingcharges;
	private String doorpickchargespay;
	private BigDecimal doorpickcharges;
	private BigDecimal othercharges;
	private BigDecimal freightvalue;
	private String igplStatus;
}
