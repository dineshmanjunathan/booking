package com.ba.app.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OgplDetailReport {
	
	private long ogplNo ;
	private String totLR;
	private String paid;
	private String toPay;
	private String fright;
	private String loading;
	private String fuel;
	private String unloading;
	private String demurage;
	private String bookingDiscount;
	private String deliveryDiscount;
	private String lrNumber;
	private int totalCost;



}
