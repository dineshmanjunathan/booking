package com.ba.app.mobile.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookAppResponse<T> {

	private Boolean isSuccess;
	private Boolean isError;
	@JsonInclude(Include.NON_NULL)
	private String message;
	@JsonInclude(Include.NON_NULL)
	private T data;
}
