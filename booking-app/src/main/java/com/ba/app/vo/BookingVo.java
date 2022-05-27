package com.ba.app.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class BookingVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187348510206952329L;

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
	private String checkpoint;
	private LocalDateTime createon;
	private Integer discount;
}
