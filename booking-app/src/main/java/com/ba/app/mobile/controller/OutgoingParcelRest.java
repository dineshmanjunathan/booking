package com.ba.app.mobile.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ba.app.mobile.dto.IncomingParcelRequest;
import com.ba.app.mobile.dto.OutgoingParcelRequest;
import com.ba.app.mobile.service.OutgoingParcelService;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.vo.InventoryVo;
import com.ba.app.vo.OutgoingParcelVo;



@RestController
@CrossOrigin
@RequestMapping("/mobile/api")
public class OutgoingParcelRest {
	
	@Autowired
	private OutgoingParcelService outgoingParcelService;
	
	
	@PostMapping("/get-outgoingParcel")
	public BookAppResponse importOutgoingParcel(@RequestBody OutgoingParcelRequest outgoingParcelRequest) {
		
		return outgoingParcelService.importOutgoingParcel(outgoingParcelRequest);
		
	}
	
	@PostMapping("/get-old-ogpl")
	public BookAppResponse getOldOgplNo(@RequestBody OutgoingParcelVo outgoingParcelVo) {
		
		return outgoingParcelService.getOldOgplNo(outgoingParcelVo.getFromLocation(),outgoingParcelVo.getToLocation());
		
	}
	
	@PostMapping("/save-outgoingParcel")
	public BookAppResponse saveOutgoingParcel(@RequestBody OutgoingParcelVo outgoingParcelVo) {
		
		return outgoingParcelService.saveOutgoingParcel(outgoingParcelVo);
		
	}
	
	@PostMapping("/getIncomingOgpl")
	public BookAppResponse getIncomingOgpl(@RequestBody IncomingParcelRequest incomingParcelRequest) {
		
		return outgoingParcelService.getIncomingOgpl(incomingParcelRequest);
		
	}
	
	@PostMapping("/loadIncomingOgpl")
	public BookAppResponse loadIncomingOgpl(@RequestBody IncomingParcelRequest incomingParcelRequest) {
		
		return outgoingParcelService.loadIncomingOgpl(incomingParcelRequest);
		
	}
	
	@PostMapping("/saveIncomingOgpl")
	public BookAppResponse saveIncomingOgpl(@RequestBody InventoryVo inventoryVo) {
		
		return outgoingParcelService.saveIncomingOgpl(inventoryVo);
		
	}
	

}
