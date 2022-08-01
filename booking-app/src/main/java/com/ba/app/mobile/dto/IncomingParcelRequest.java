package com.ba.app.mobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomingParcelRequest {
	@JsonInclude(Include.NON_NULL)
	private String fromLocation;
	@JsonInclude(Include.NON_NULL)
	private String toLocation;
	@JsonInclude(Include.NON_NULL)
	private String bookedOn;
	@JsonInclude(Include.NON_NULL)
	private long ogpl;
	
}
