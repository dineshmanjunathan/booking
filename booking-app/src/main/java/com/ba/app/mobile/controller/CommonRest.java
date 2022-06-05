package com.ba.app.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ba.app.entity.User;
import com.ba.app.mobile.service.CommonService;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.mobile.util.CommonUtil;

@RestController
@CrossOrigin
@RequestMapping("/mobile/api")
public class CommonRest {
	
	@Autowired
	private CommonService commonService;

	@PostMapping("/getFromLocation")
	public BookAppResponse getFromLocation(@RequestHeader("Authorization") String token)
	{
		return commonService.getFromLocation(token);
	}
	
	@PostMapping("/getToLocation")
	public BookAppResponse getToLocation(@RequestHeader("Authorization") String token)
	{
		return commonService.getToLocation(token);
	}
}
