package com.ba.app.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OGPLReport implements Serializable {
	private static final long serialVersionUID = -7187348510206952329L;
	private String id;
	private String bookedon ;
	private String fromLocation;
	private String toLocation ;
	private String ogplNo ;
	private String vehicleNo ;
	private String driver ;
	private String conductor ;
	private String preparedBy ;
	private String PayType ;
	private String freightvalue ;
	private String loadingcharges ;
	private String doorpickcharges ;
	private String totalpaid ;
	private String totaltopay ;

}
