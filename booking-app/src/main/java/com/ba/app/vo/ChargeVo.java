package com.ba.app.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeVo implements Serializable {
	private static final long serialVersionUID = -7187348510206952329L;
	private String chargetype;
	private String value;
	private Long id;
	private String fromLocation;
	private String toLocation ;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();

}
