package com.ba.app.mobile.jwt.util;

public class CommonUtil {

	public static <T> BookAppResponse successResponse(T data,String msg)
	{
		BookAppResponse response=new BookAppResponse();
		response.setIsSuccess(true);
		response.setIsError(false);
		response.setData(data);
		response.setMessage(msg);
		
		return response;
	}
	
	public static <T> BookAppResponse errorResponse(T data,String msg)
	{
		BookAppResponse response=new BookAppResponse();
		response.setIsSuccess(false);
		response.setIsError(true);
		response.setData(null);
		response.setMessage(msg);
		
		return response;
	}
	
	
	
}
