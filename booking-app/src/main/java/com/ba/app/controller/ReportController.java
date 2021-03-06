package com.ba.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ba.app.dto.OgplDetailReport;
import com.ba.app.entity.Booking;
import com.ba.app.entity.Charge;
import com.ba.app.entity.Delivery;
import com.ba.app.entity.Location;
import com.ba.app.entity.OutgoingParcel;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.ChargeRepository;
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

	@Autowired
	private OutgoingParcelRepository outgoingParcelRepository;

	@Autowired
	private ChargeRepository chargeRepository;

	@Autowired
	private EntityManager em;

	private String sessionValidation(HttpServletRequest request, ModelMap model) {
		try {
			if (request.getSession().getAttribute("USER_ID") != null) {
				return null;
			} else {
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
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			LinkedList<Object[]> list = reportRepository.findAllOgplRecord();
			System.out.println(list);
			LinkedList<OGPLReport> ogplReportList = new LinkedList<OGPLReport>();
			for (Object[] str : list) {
				OGPLReport ogplReport = new OGPLReport();
				ogplReport.setBookedon(("" + str[0]).replace("null", "-"));
				ogplReport.setFromLocation(("" + str[1]).replace("null", "-"));
				ogplReport.setToLocation(("" + str[2]).replace("null", "-"));
				ogplReport.setOgplNo(("" + str[3]).replace("null", "-"));
				ogplReport.setVehicleNo(("" + str[4]).replace("null", "-"));
				ogplReport.setDriver(("" + str[5]).replace("null", "-"));
				ogplReport.setConductor(("" + str[6]).replace("null", "-"));
				ogplReport.setPreparedBy(("" + str[7]).replace("null", "-"));
				ogplReport.setPayType(("" + str[8]).replace("null", "-"));
				ogplReport.setFreightvalue(("" + str[9]).replace("null", "-"));
				ogplReport.setLoadingcharges(("" + str[10]).replace("null", "-"));
				ogplReport.setDoorpickcharges(("" + str[11]).replace("null", "-"));
				ogplReport.setTotalpaid(("" + str[12]).replace("null", "-"));
				ogplReport.setTotaltopay(("" + str[13]).replace("null", "-"));
				ogplReport.setLRno(("" + str[14]).replace("null", "-"));
				ogplReport.setId(("" + str[15]).replace("null", "-"));
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
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

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
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

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

	@RequestMapping("/report/ogplConsolidatedReport")
	public String ogplConsolidatedReport(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			float total = 0.00f;

			Iterable<OutgoingParcel> list = outgoingParcelRepository.findAll();

			List<OgplDetailReport> detailReports = new ArrayList<OgplDetailReport>();

			Integer uploadingCharge = 0;

			for (OutgoingParcel data : list) {
				OgplDetailReport entity = new OgplDetailReport();

				List<Integer> duCharge = new ArrayList<Integer>();
				Charge chargeData = chargeRepository.findByFromLocationAndToLocationAndChargetype(
						data.getFromLocation(), data.getToLocation(), "FUEL CHARGES");

				Optional<Location> loData = locationRepository.findById(data.getToLocation());

				if (loData.isPresent()) {
					if (Objects.nonNull(loData.get().getUploadingCharge())) {
						uploadingCharge = loData.get().getUploadingCharge();
					}
				}

				data.getOgpnoarray().stream().forEach(r -> {
					Delivery delivery = deliveryRepository.findByLRNo(r);
					if (Objects.nonNull(delivery)) {
						duCharge.add(
								Integer.parseInt(delivery.getDemurrage().isEmpty() ? "0" : delivery.getDemurrage()));
					}

				});
				entity.setDemurage(String.valueOf(duCharge.stream().reduce(0, (a, b) -> a + b)));

				LinkedList<Object[]> bookings = bookingRepository.getOgplDetailedReport(data.getOgpnoarray());
				for (Object[] str : bookings) {

					entity.setOgplNo(data.getOgplNo());
					entity.setTotLR(String.valueOf(data.getOgpnoarray().size()));
					entity.setPaid(("" + str[0]).replace("null", "0.00"));
					entity.setToPay(("" + str[1]).replace("null", "0.00"));
					entity.setFright(("" + str[2]).replace("null", "0.00"));
					entity.setLoading(("" + str[3]).replace("null", "0.00"));
					entity.setBookingDiscount(("" + str[4]).replace("null", "0.00"));
					entity.setDeliveryDiscount(("" + str[5]).replace("null", "0.00"));
					entity.setFuel(Objects.nonNull(chargeData)
							? String.valueOf(Integer.parseInt(chargeData.getValue()) * data.getOgpnoarray().size())
							: "-");
					entity.setUnloading(
							String.valueOf(uploadingCharge * Integer.parseInt(("" + str[6]).replace("null", "0"))));

					entity.setLrNumber(String.join(",", data.getOgpnoarray()));

					detailReports.add(entity);

				}
			}

			List<OgplDetailReport> response = new ArrayList<OgplDetailReport>();

			for (OgplDetailReport r : detailReports) {

				total = (Float.parseFloat(r.getPaid()) + Float.parseFloat(r.getToPay())
						+ Float.parseFloat(r.getUnloading()) + Float.parseFloat(r.getDemurage()))
						- Float.parseFloat(r.getDeliveryDiscount());

				r.setTotalCost(String.valueOf(total));
				response.add(r);
			}

			model.addAttribute("OGPL", response);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OGPLDetailedReport";

	}

	@RequestMapping(value = "/report/ogplConsolidatedFilterReport")
	public String ogplConsolidatedFilterReport(@Param("ogpl") String ogpl, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			model.clear();
			if (ogpl.equalsIgnoreCase("ALL")) {
				ogpl = "%";
			}

			Query query = em.createNativeQuery(
					"select * from t_outgoing_parcel where CAST(ogpl_no as text) like (?) and cast(booked_on as date) between cast(? as date) and cast(? as date)",
					OutgoingParcel.class);
			query.setParameter(1, ogpl);
			query.setParameter(2, fromDate);
			query.setParameter(3, toDate);

			List<OutgoingParcel> list = query.getResultList();

			List<OgplDetailReport> detailReports = new ArrayList<OgplDetailReport>();

			Integer uploadingCharge = 0;

			for (OutgoingParcel data : list) {
				OgplDetailReport entity = new OgplDetailReport();

				List<Integer> duCharge = new ArrayList<Integer>();
				Charge chargeData = chargeRepository.findByFromLocationAndToLocationAndChargetype(
						data.getFromLocation(), data.getToLocation(), "FUEL CHARGES");

				Optional<Location> loData = locationRepository.findById(data.getToLocation());

				if (loData.isPresent()) {
					if (Objects.nonNull(loData.get().getUploadingCharge())) {
						uploadingCharge = loData.get().getUploadingCharge();
					}
				}

				data.getOgpnoarray().stream().forEach(r -> {
					Delivery delivery = deliveryRepository.findByLRNo(r);
					if (Objects.nonNull(delivery)) {
						duCharge.add(
								Integer.parseInt(delivery.getDemurrage().isEmpty() ? "0" : delivery.getDemurrage()));
					}

				});
				entity.setDemurage(String.valueOf(duCharge.stream().reduce(0, (a, b) -> a + b)));

				LinkedList<Object[]> bookings = bookingRepository.getOgplDetailedReport(data.getOgpnoarray());

				for (Object[] str : bookings) {
					entity.setOgplNo(data.getOgplNo());
					entity.setTotLR(String.valueOf(data.getOgpnoarray().size()));
					entity.setPaid(("" + str[0]).replace("null", "0.00"));
					entity.setToPay(("" + str[1]).replace("null", "0.00"));
					entity.setFright(("" + str[2]).replace("null", "0.00"));
					entity.setLoading(("" + str[3]).replace("null", "0.00"));
					entity.setBookingDiscount(("" + str[4]).replace("null", "0.00"));
					entity.setDeliveryDiscount(("" + str[5]).replace("null", "0.00"));
					entity.setFuel(Objects.nonNull(chargeData)
							? String.valueOf(Integer.parseInt(chargeData.getValue()) * data.getOgpnoarray().size())
							: "-");

					entity.setUnloading(
							String.valueOf(uploadingCharge * Integer.parseInt(("" + str[6]).replace("null", "0"))));
					entity.setLrNumber(String.join(",", data.getOgpnoarray()));

					detailReports.add(entity);

				}
			}

			List<OgplDetailReport> response = new ArrayList<OgplDetailReport>();
			float total=0.00f;
			for (OgplDetailReport r : detailReports) {

				total = (Float.parseFloat(r.getPaid()) + Float.parseFloat(r.getToPay())
						+ Float.parseFloat(r.getUnloading()) + Float.parseFloat(r.getDemurage()))
						- Float.parseFloat(r.getDeliveryDiscount());

				r.setTotalCost(String.valueOf(total));
				response.add(r);
			}

			model.addAttribute("OGPL", response);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OGPLDetailedReport";

	}

	@RequestMapping(value = "/report/ogplConsolidatedFilterReportPOPUp")
	public String ogplConsolidatedFilterReportPOPUp(@Param("ogpl") Long ogpl, HttpServletRequest request,
			ModelMap model) {
		try {

			Map<String, Integer> duChargeMap = new HashMap<String, Integer>();

			ArrayList<String> lrArray = new ArrayList<>();
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			Query query = em.createNativeQuery("select * from t_outgoing_parcel where ogpl_no=?", OutgoingParcel.class);
			query.setParameter(1, ogpl);

			List<OutgoingParcel> list = query.getResultList();

			System.out.println(list.size());

			List<Object> detailReports = new ArrayList<Object>();

			Integer uploadingCharge = 0;
			Charge chargeData = null;

			for (OutgoingParcel data : list) {

				List<Integer> duCharge = new ArrayList<Integer>();
				chargeData = chargeRepository.findByFromLocationAndToLocationAndChargetype(data.getFromLocation(),
						data.getToLocation(), "FUEL CHARGES");

				Optional<Location> loData = locationRepository.findById(data.getToLocation());

				if (loData.isPresent()) {
					if (Objects.nonNull(loData.get().getUploadingCharge())) {
						uploadingCharge = loData.get().getUploadingCharge();
					}
				}
				data.getOgpnoarray().stream().forEach(r -> {
					Delivery delivery = deliveryRepository.findByLRNo(r);
					if (Objects.nonNull(delivery)) {
						duChargeMap.put(r,
								Integer.parseInt(delivery.getDemurrage().isEmpty() ? "0" : delivery.getDemurrage()));
					}

				});

				lrArray = data.getOgpnoarray();

			}

			Integer totUnloadCharges = 0;
			LinkedList<Object[]> bookings = bookingRepository.getOgplDetailedReportPOPUP(lrArray);
			for (Object[] str : bookings) {
				OgplDetailReport entity = new OgplDetailReport();

				entity.setDemurage((String.valueOf(duChargeMap.get(str[6]))).replace("null", "-"));
				entity.setOgplNo(ogpl);
				entity.setTotLR(("" + str[6]).replace("null", "-"));
				entity.setPaid(("" + str[0]).replace("null", "-"));
				entity.setToPay(("" + str[1]).replace("null", "-"));
				entity.setFright(("" + str[2]).replace("null", "-"));
				entity.setLoading(("" + str[3]).replace("null", "-"));
				entity.setBookingDiscount(("" + str[4]).replace("null", "-"));
				entity.setDeliveryDiscount(("" + str[5]).replace("null", "-"));
				entity.setFuel(
						Objects.nonNull(chargeData) ? String.valueOf(Integer.parseInt(chargeData.getValue())) : "-");
				entity.setUnloading(String.valueOf(uploadingCharge * Integer.parseInt("" + str[7])));

				detailReports.add(entity);

			}

			System.out.println(detailReports.toString());

			model.addAttribute("OGPL", detailReports);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OGPLDetailedReportPOP";

	}

}
