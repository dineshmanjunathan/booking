package com.ba.app.entity;

import java.util.ArrayList;

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
@Table(name = "t_inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private long ogplNo ;
	private ArrayList<String> lrnoarray;


}
