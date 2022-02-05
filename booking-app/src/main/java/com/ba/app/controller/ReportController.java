package com.ba.app.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ba.app.entity.Booking;
import com.ba.app.entity.Delivery;
import com.ba.app.entity.Location;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.DeliveryRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.model.OutgoingParcelRepository;
import com.ba.app.vo.OGPLReport;

@Controller
public class ReportController {

	@Autowired
	private OutgoingParcelRepository reportRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private LocationRepository locationRepository;
	
	private String sessionValidation(HttpServletRequest request, ModelMap model) {
		try {
			if (request.getSession().getAttribute("USER_ID") != null) {
				return null;
			}else {
				model.addAttribute("errormsg", "Your login session has expired. Please login again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	@RequestMapping("/report/ogpl")
	public String ogplReport(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			LinkedList<Object[]> list = reportRepository.findAllOgplRecord();
			System.out.println(list);
			LinkedList<OGPLReport> ogplReportList = new LinkedList<OGPLReport>();
			for(Object[] str:list) {
				OGPLReport ogplReport = new OGPLReport();
				ogplReport.setBookedon((""+str[0]).replace("null", "-"));
				ogplReport.setFromLocation((""+str[1]).replace("null", "-"));
				ogplReport.setToLocation((""+str[2]).replace("null", "-"));
				ogplReport.setOgplNo((""+str[3]).replace("null", "-"));
				ogplReport.setVehicleNo((""+str[4]).replace("null", "-"));
				ogplReport.setDriver((""+str[5]).replace("null", "-"));
				ogplReport.setConductor((""+str[6]).replace("null", "-"));
				ogplReport.setPreparedBy((""+str[7]).replace("null", "-"));
				ogplReport.setPayType((""+str[8]).replace("null", "-"));
				ogplReport.setFreightvalue((""+str[9]).replace("null", "-"));
				ogplReport.setLoadingcharges((""+str[10]).replace("null", "-"));
				ogplReport.setDoorpickcharges((""+str[11]).replace("null", "-"));
				ogplReport.setTotalpaid((""+str[12]).replace("null", "-"));
				ogplReport.setTotaltopay((""+str[13]).replace("null", "-"));
				ogplReport.setLRno((""+str[14]).replace("null", "-"));
				ogplReport.setId((""+str[15]).replace("null", "-"));
				ogplReportList.add(ogplReport);
			}
			
			
			model.addAttribute("OGPL", ogplReportList);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OGPLReport";
	}
	
	@RequestMapping("/report/delivery")
	public String deliveryReport(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			Iterable<Delivery> delivery = deliveryRepository.findAll();
			
			model.addAttribute("DeliveryReport", delivery);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DeliveryReport";
	}
	
	@RequestMapping("/report/booking")
	public String bookingReport(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			Iterable<Booking> booking = bookingRepository.findAll();
			
			model.addAttribute("Booking", booking);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookingReport";
	}
	
	private void setAllLocationListInModel(ModelMap model) {
		Iterable<Location> locaIterable = locationRepository.findAll();
		model.addAttribute("locationList", locaIterable);
	}
}
