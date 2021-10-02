package com.ba.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
	private Long id;
	private String name="Vinoth";
	private String paid="500";
	private Long noOfItems;
	private String deliveredBy="Vivek";
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
	
}
