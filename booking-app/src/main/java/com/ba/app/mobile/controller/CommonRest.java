package com.ba.app.mobile.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ba.app.mobile.jwt.util.BookAppResponse;
import com.ba.app.mobile.jwt.util.CommonUtil;

@RestController
@CrossOrigin
public class CommonRest {

	@PostMapping("/mobile/api/check")
	public BookAppResponse getCheck()
	{
		return CommonUtil.successResponse("Sucess", "CheckedSucess");
	}
}
