package com.ba.app.vo;

import java.util.ArrayList;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OutgoingParcelVo {
	private long id;
	private String fromLocation;
	private String toLocation ;
	private String driver ;
	private String conductor ;
	private String preparedBy ;
	private String details ;
	private String bookedOn ;
	private String deliveredBy ;
	private String vehicleNo ;
	private String ogplNo ;
	private ArrayList ogpnoarray;
}
