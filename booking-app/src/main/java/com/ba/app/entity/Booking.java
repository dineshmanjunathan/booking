package com.ba.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "t_booking")
@AllArgsConstructor
public class Booking implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187348510206952329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonInclude(Include.NON_NULL)
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
	private String invNo;
	private String tinNo;
	private String billDesc;
	private Long billValue;
	private String bookedBy;
	private Boolean isPrinted;
	
	private Long to_phone;
	private Long item_count;
	private BigDecimal weight;
	private String freight_status;
	private BigDecimal total_charges;
	private BigDecimal paid;
	private BigDecimal topay;
	private BigDecimal cash;
	private BigDecimal refund;
	private BigDecimal total;
	private String billNumber;
	private Long ogplNo;
	private String payOption;
	private String loadingchargespay;
	private BigDecimal loadingcharges;
	private String doorpickchargespay;
	private BigDecimal doorpickcharges;
	private BigDecimal othercharges;
	private BigDecimal freightvalue;
	private String igplStatus;
	private boolean connectionPoint;
	private boolean ogplConnPoint;
	
	private Boolean connectionPointStatus;
	
	//0 - booking 1- mid point 2 - ready to delvert
	
	private Integer pointStatus;
	private String currentLocation;
	private LocalDateTime createon;
	private Integer discount;
	
	private Integer deliveryDiscount;

	public Booking(BigDecimal paid, BigDecimal topay, BigDecimal loadingcharges, BigDecimal freightvalue,
			Integer discount, Integer deliveryDiscount) {
		super();
		this.paid = paid;
		this.topay = topay;
		this.loadingcharges = loadingcharges;
		this.freightvalue = freightvalue;
		this.discount = discount;
		this.deliveryDiscount = deliveryDiscount;
	}


	
	
}
