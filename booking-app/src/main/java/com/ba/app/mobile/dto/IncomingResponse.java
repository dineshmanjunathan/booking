package com.ba.app.mobile.dto;

import java.util.List;

import com.ba.app.entity.Booking;
import com.ba.app.entity.OutgoingParcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomingResponse {
	@JsonInclude(Include.NON_NULL)
	private List<Booking> booking;
	@JsonInclude(Include.NON_NULL)
	private OutgoingParcel outgoingParcel;

}
