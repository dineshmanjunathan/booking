package com.ba.app.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryVo implements Serializable {
	private static final long serialVersionUID = -7187348510206952329L;
	private Long id;
	private String name;
	private String paid;
	private Long noOfItems;
	private String deliveredBy;
	//private String deliveredBy;
	private Long no;
	private Long ogpl;
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
