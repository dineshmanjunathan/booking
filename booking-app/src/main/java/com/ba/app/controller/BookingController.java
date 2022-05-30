package com.ba.app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ba.app.entity.BookedBy;
import com.ba.app.entity.Booking;
import com.ba.app.entity.Charge;
import com.ba.app.entity.Conductor;
import com.ba.app.entity.ConfigProperty;
import com.ba.app.entity.ConnectionPoint;
import com.ba.app.entity.Customer;
import com.ba.app.entity.Delivery;
import com.ba.app.entity.Driver;
import com.ba.app.entity.Inventory;
import com.ba.app.entity.Location;
import com.ba.app.entity.OutgoingParcel;
import com.ba.app.entity.PayType;
import com.ba.app.entity.PaymentType;
import com.ba.app.entity.User;
import com.ba.app.entity.Vehicle;
import com.ba.app.model.BookedByRepository;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.ChargeRepository;
import com.ba.app.model.ConductorRepository;
import com.ba.app.model.ConfigPropertyRepository;
import com.ba.app.model.ConnectionPointRepository;
import com.ba.app.model.CustomerRepository;
import com.ba.app.model.DeliveryRepository;
import com.ba.app.model.DriverRepository;
import com.ba.app.model.InventoryRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.model.OutgoingParcelRepository;
import com.ba.app.model.PayOptionRepository;
import com.ba.app.model.PaymentTypeRepository;
import com.ba.app.model.UserRepository;
import com.ba.app.model.VehicleRepository;
import com.ba.app.vo.BookingVo;
import com.ba.app.vo.DeliveryVo;
import com.ba.app.vo.InventoryVo;
import com.ba.app.vo.LocationVo;
import com.ba.app.vo.OutgoingParcelVo;
import com.ba.app.vo.PayOptionVo;
import com.ba.app.vo.PaymentTypeVo;
import com.ba.app.vo.UserVo;
import com.ba.app.vo.VehicleVo;
import com.ba.utils.ConfigProperties;
import com.ba.utils.DeliverySlipGenerator;
import com.ba.utils.LuggageSlipGenerator;
import com.ba.utils.Utils;

@Controller
public class BookingController {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private PayOptionRepository payOptionRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OutgoingParcelRepository outgoingParcelRepository;;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private ConductorRepository conductorRepository;
	@Autowired
	private BookedByRepository bookedByRepository;
	@Autowired
	private ChargeRepository chargeRepository;

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	@Autowired
	private ConnectionPointRepository connectionPointRepository;

	@Autowired
	private ConfigPropertyRepository configPropertyRepository;

	@Autowired
	private UserRepository userRepository;

	private String lRNumber, bookingDate, fromToLocation;

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

	@RequestMapping(value = "/booking/save", method = RequestMethod.POST)
	public String saveBookingDetails(HttpServletRequest request, BookingVo bookingVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			Booking booking = new Booking();
			BeanUtils.copyProperties(bookingVo, booking);
			model.addAttribute("bookingsuccessmessage", "Booked Successfully.");
			lRNumber = booking.getLrNumber();
			bookingDate = booking.getBookedOn();
			fromToLocation = booking.getFromLocation() + " - " + booking.getToLocation();
			model.addAttribute("LRNumber", lRNumber);
			model.addAttribute("BookingDate", bookingDate);
			model.addAttribute("FromToLocation", fromToLocation);

			Booking bLrNo = bookingRepository.findByLrNumber(booking.getLrNumber());
			booking.setIgplStatus("P");
			booking.setPointStatus(0);
			booking.setCurrentLocation(booking.getFromLocation());
			booking.setCreateon(LocalDateTime.now());
			booking.setConnectionPointStatus(true);
			ConnectionPoint connPointList = connectionPointRepository
					.findByFromLocationAndToLocation(booking.getFromLocation(), booking.getToLocation());
			if (connPointList != null && connPointList.getCheckPoint() != null) {
				booking.setConnectionPoint(true);
				booking.setOgplConnPoint(true);
			}
			if (bLrNo != null && bLrNo.getLrNumber() != null) {
				booking.setId(bLrNo.getId());
				bookingRepository.save(booking);
			} else {
				// SAVE SEQ FOR LR
				bookingRepository.getNextLRNumber();
				bookingRepository.save(booking);
				saveFromCustomer(bookingVo);
				saveToCustomer(bookingVo);

			}
			setAllLocationListInModel(model);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errormsg", "Failed to Book ");
			return "booking";
		}
		return "bookingsuccess";
	}

	private void saveFromCustomer(BookingVo bookingVo) {
		try {
			// System.out.println("From Phone ----- "+bookingVo.get);
			Long phoneNumber = customerRepository.findByPhoneNumber(bookingVo.getFrom_phone());
			if (phoneNumber != null && phoneNumber > 0) {
				Customer customer = customerRepository.findByAllPhoneNumber(bookingVo.getFrom_phone());
				if (customer != null) {
					customer.setCustName(bookingVo.getFromName());
					customerRepository.save(customer);
				}
			} else {
				Customer customer = new Customer();
				customer.setCustName(bookingVo.getFromName());
				customer.setPhoneNumber(bookingVo.getFrom_phone());
				customerRepository.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveToCustomer(BookingVo bookingVo) {
		try {
			Long phoneNumber = customerRepository.findByPhoneNumber(bookingVo.getTo_phone());
			if (phoneNumber != null && phoneNumber > 0) {
				Customer customer = customerRepository.findByAllPhoneNumber(bookingVo.getTo_phone());
				if (customer != null) {
					customer.setCustName(bookingVo.getToName());
					customerRepository.save(customer);
				}
			} else {
				Customer customer = new Customer();
				customer.setCustName(bookingVo.getToName());
				customer.setPhoneNumber(bookingVo.getTo_phone());
				customerRepository.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/outgoingParcel")
	public String outgoingParcel(HttpServletRequest request, ModelMap model) {

		// SESSION VALIDATION
		if (sessionValidation(request, model) != null)
			return "login";

		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		setAllConductorListInModel(model);
		setAllDriverListInModel(model);
		return "outgoingParcel";
	}

	@RequestMapping("/incomingParcel")
	public String incomingParcel(HttpServletRequest request, ModelMap model) {
		// SESSION VALIDATION
		if (sessionValidation(request, model) != null)
			return "login";
		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		setAllConductorListInModel(model);
		setAllDriverListInModel(model);
		return "incomingParcel";
	}

	@RequestMapping("/booking")
	public String booking(HttpServletRequest request, ModelMap model) {
		// SESSION VALIDATION
		if (sessionValidation(request, model) != null)
			return "login";
		setAllLocationListInModel(model);
		Long nextLRNumber = bookingRepository.getcurrentLRNumber();
		String fromlocationcode = "" + request.getSession().getAttribute("USER_LOCATIONID");
		String sLR = "";
		if (fromlocationcode != null && !fromlocationcode.isEmpty() && !fromlocationcode.equalsIgnoreCase("NULL")) {
			sLR = fromlocationcode + "/" + LocalDate.now() + "/" + nextLRNumber;
		} else {
			sLR = LocalDate.now() + "/" + nextLRNumber;
		}
		// From and To location should not be Same starts
		ArrayList<Location> locationList = (ArrayList) model.get("locationList");
		for (int i = 0; i < locationList.size(); i++) {
			Location location = locationList.get(i);
			if (location != null && location.getId() != null
					&& location.getId().trim().equalsIgnoreCase(fromlocationcode)) {
				locationList.remove(i);
			}
		}
		model.addAttribute("locationList", locationList);
		// From and To location should not be Same ends
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = formatter.format(date);
		lRNumber = sLR;
		model.addAttribute("LRnumber", lRNumber);
		// model.addAttribute("bookedOn", currentDate);
		BookingVo bookingVO = new BookingVo();
		bookingVO.setBookedOn(currentDate);
		model.addAttribute("booking", bookingVO);

		Optional<ConfigProperty> mobileNumberFirstDigitValidation = configPropertyRepository
				.findById(ConfigProperties.MOBILE_F_D_V.get());

		if (mobileNumberFirstDigitValidation.isPresent()) {
			model.addAttribute(ConfigProperties.MOBILE_F_D_V.get(),
					Integer.parseInt(mobileNumberFirstDigitValidation.get().getValue()));
		}

		setAllBookednameListInModel(model); // to set all booked name

		return "booking";
	}

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public String saveConfigure(HttpServletRequest request, LocationVo locationVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			if (locationVo != null) {
				try {
					Location location = locationRepository.findById(locationVo.getId()).get();
					if (location != null && location.getId() != null) {
						model.addAttribute("errormsg", "Location Code [" + location.getId() + "] is already exist! ");
						return "addLocation";
					}
				} catch (Exception e) {
					// do nothing
				}
			}

			Location locationEntity = new Location();

			BeanUtils.copyProperties(locationVo, locationEntity, "createon", "updatedon");
			locationEntity = locationRepository.save(locationEntity);
			model.addAttribute("successMessage", locationEntity.getLocation() + " - location added! ");
			// model.addAttribute("location", locationEntity);
			Iterable<Location> locaIterable = locationRepository.findAll();
			model.addAttribute("locationListing", locaIterable);
			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addLocation";
		}
		return "locationListing";
	}

	@RequestMapping(value = "/editLocation", method = RequestMethod.POST)
	public String updateLocationConfigure(HttpServletRequest request, LocationVo locationVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Location locationEntity = new Location();

			BeanUtils.copyProperties(locationVo, locationEntity, "createon", "updatedon");
			locationEntity = locationRepository.save(locationEntity);
			model.addAttribute("successMessage", locationEntity.getLocation() + " - location added! ");
			// model.addAttribute("location", locationEntity);
			Iterable<Location> locaIterable = locationRepository.findAll();
			model.addAttribute("locationListing", locaIterable);
			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addLocation";
		}
		return "locationListing";
	}

	@RequestMapping("/locationListing")
	public String countryCodeListing(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<Location> locaIterable = locationRepository.findAll();
			model.addAttribute("locationListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "locationListing";
	}

	@RequestMapping(value = "/location/edit/{id}", method = RequestMethod.GET)
	public String locationEdit(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Location location = locationRepository.findById(id).get();
			LocationVo locationVo = new LocationVo();
			BeanUtils.copyProperties(location, locationVo);
			model.addAttribute("location", locationVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addLocation";
	}

	@RequestMapping(value = "/locationDelete", method = RequestMethod.GET)
	public String locationDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			locationRepository.deleteById(id);
			model.addAttribute("successMessage", "Deleted Successfully");
			Iterable<Location> locaIterable = locationRepository.findAll();
			model.addAttribute("locationListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "locationListing";
	}

	@RequestMapping(value = "/payOption", method = RequestMethod.POST)
	public String savePayout(HttpServletRequest request, PayOptionVo payOptionVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			PayType payOptionEntity = new PayType();

			BeanUtils.copyProperties(payOptionVo, payOptionEntity, "createon", "updatedon");
			payOptionEntity = payOptionRepository.save(payOptionEntity);
			model.addAttribute("successMessage", payOptionEntity.getPayOption() + " - Payoption added! ");
			Iterable<PayType> locaIterable = payOptionRepository.findAll();
			model.addAttribute("payOptionListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to Pay Option! ");
			return "payOption";
		}
		return "payOptionListing";
	}

	@RequestMapping("/payOptionListing")
	public String payOptionListing(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<PayType> locaIterable = payOptionRepository.findAll();
			model.addAttribute("payOptionListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payOptionListing";
	}

	@RequestMapping(value = "/payOption/delete/{id}", method = RequestMethod.GET)
	public String payOptionDelete(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			payOptionRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<PayType> locaIterable = payOptionRepository.findAll();
			model.addAttribute("payOptionListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payOptionListing";
	}

	private void setAllLocationListInModel(ModelMap model) {
		Iterable<Location> locaIterable = locationRepository.findAll();
		model.addAttribute("locationList", locaIterable);
	}

	@RequestMapping(value = "get/incomingParcel", method = RequestMethod.GET)
	public String importIncomingParcel(@RequestParam("fromLocation") String fromLocation,
			@RequestParam("toLocation") String toLocation, @RequestParam("bookedOn") String bookedOn,
			HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			List<OutgoingParcel> ogplList;
			if (bookedOn != null && bookedOn.trim().length() > 0) {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocationAndBookedOn(fromLocation, toLocation,
						bookedOn);
			} else {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
			}
			
			
			List<OutgoingParcel> finalList=new ArrayList<OutgoingParcel>();

			List<List<OutgoingParcel>> fullList = ogplList.stream()
			                .collect(Collectors.groupingBy(OutgoingParcel::getOgplNo)) // Or another collector
			                .entrySet()
			                .stream()
			                .map(Map.Entry::getValue)
			                .collect(Collectors.toList());

			for(List<OutgoingParcel> data:fullList)
			{
				finalList.add(data.get(0));
			}
			
			model.addAttribute("ogplList", finalList);
			/*
			 * if (ogplList != null && ogplList.size() > 0) { OutgoingParcel og =
			 * ogplList.get(0); model.addAttribute("fromLocation", og.getToLocation());
			 * model.addAttribute("toLocation", og.getFromLocation()); } else {
			 * model.addAttribute("toLocation", toLocation);
			 * model.addAttribute("fromLocation", fromLocation); }
			 */
			model.addAttribute("toLocation", toLocation);
			model.addAttribute("fromLocation", fromLocation);

			model.addAttribute("bookedOn", bookedOn);
			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);
			if (ogplList != null && ogplList.size() > 0) {
				model.addAttribute("successMessage",
						"Parcels Imported Successfully! Please select OGPL to load Parcel");
			} else {
				model.addAttribute("successMessage", "No Parcels to Import on selected Range");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to Import Parcel!");
		}
		return "incomingParcel";
	}

	@RequestMapping(value = "get/outgoingParcel", method = RequestMethod.GET)
	public String importOutgoingParcel(@RequestParam("fromLocation") String fromLocation,
			@RequestParam("toLocation") String toLocation, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			ConnectionPoint connectionPoint = connectionPointRepository.findByFromLocationAndCheckPoint(fromLocation,
					toLocation);
			List<Booking> connOutgoingList = null;
			List<Booking> outgoingList = null;
			outgoingList = bookingRepository.getOGPLlist(fromLocation, toLocation);

			System.out.println("connectionPoint-->" + connectionPoint);
			if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {
				System.out.println("connectionPoint.getCheckPoint()-->" + connectionPoint.getCheckPoint());

				connOutgoingList = bookingRepository.getOGPLlist1(fromLocation, connectionPoint.getToLocation());

			} else {
				connectionPoint = connectionPointRepository.findByToLocationAndCheckPoint(toLocation, fromLocation);
				if (connectionPoint != null && connectionPoint.getCheckPoint() != null) {
					connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
							fromLocation);

				} else {
					outgoingList = new ArrayList<Booking>();
					connOutgoingList = bookingRepository.getOGPLlist3(fromLocation, toLocation);

					if (Objects.nonNull(connOutgoingList.get(0).getOgplNo())) {

						connOutgoingList = bookingRepository.getOGPLlist2(connectionPoint.getFromLocation(), toLocation,
								fromLocation);
					}

				}
			}

			outgoingList.addAll(connOutgoingList);

			model.addAttribute("outgoingList", outgoingList);
			OutgoingParcel outgoingParcel = new OutgoingParcel();
			outgoingParcel.setFromLocation(fromLocation);
			outgoingParcel.setToLocation(toLocation);
			model.addAttribute("outgoingparcel", outgoingParcel);
			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to Import Parcel!");
		}
		return "outgoingParcel";
	}

	private void setAllVehileListInModel(ModelMap model) {
		Iterable<Vehicle> vechileIterable = vehicleRepository.findAll();
		model.addAttribute("vehicleList", vechileIterable);
	}

	@RequestMapping(value = "/payOption/edit/{id}", method = RequestMethod.GET)
	public String payOptionEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			PayType payOption = payOptionRepository.findById(id).get();
			PayOptionVo payOptionVo = new PayOptionVo();
			BeanUtils.copyProperties(payOption, payOptionVo);
			model.addAttribute("payOption", payOptionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payOption";
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	public String saveVehicle(HttpServletRequest request, VehicleVo vehicleVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Vehicle vehicleEntity = new Vehicle();
			BeanUtils.copyProperties(vehicleVo, vehicleEntity, "createon", "updatedon");
			vehicleEntity = vehicleRepository.save(vehicleEntity);
			model.addAttribute("successMessage", vehicleEntity.getVehicle() + " - Vehicle added! ");
			Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();
			model.addAttribute("vehicleListing", vehicleIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to Pay Option! ");
			return "vehicleDetails";
		}
		return "vehicleListing";
	}

	@RequestMapping("/vehicleListing")
	public String saveVehicleListing(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<Vehicle> locaIterable = vehicleRepository.findAll();
			model.addAttribute("vehicleListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vehicleListing";
	}

	@RequestMapping(value = "/vehicle/delete/{id}", method = RequestMethod.GET)
	public String vehicleDelete(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			vehicleRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();
			model.addAttribute("vehicleListing", vehicleIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vehicleListing";
	}

	@RequestMapping(value = "/vehicle/edit/{id}", method = RequestMethod.GET)
	public String vehicleEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Vehicle vehicle = vehicleRepository.findById(id).get();
			VehicleVo vehicleVo = new VehicleVo();
			BeanUtils.copyProperties(vehicle, vehicleVo);
			model.addAttribute("vehicle", vehicleVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vehicleDetails";
	}

	@RequestMapping(value = "/addDelivery", method = RequestMethod.POST)
	public String saveDelivery(HttpServletRequest request, DeliveryVo deliveryVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			if (deliveryVo != null && deliveryVo.getLRNo() != null) {
				List checkList = bookingRepository.findByLrNumberAndIgplStatus(deliveryVo.getLRNo(), "D");
				if (checkList != null && checkList.size() > 0) {
					model.addAttribute("warningmsg", "LR Number already deliveried!");
					return "delivery";
				}
			}
			Delivery deliveryEntity = new Delivery();

			BeanUtils.copyProperties(deliveryVo, deliveryEntity, "createon", "updatedon");

			deliveryEntity.setCreateon(LocalDateTime.now());
			deliveryEntity = deliveryRepository.save(deliveryEntity);

			if (deliveryEntity != null && deliveryEntity.getLRNo() != null) {
				bookingRepository.updateIgplStatusByLR("D", deliveryEntity.getLRNo());
			}

			model.addAttribute("delivery", deliveryVo);
			model.addAttribute("DeliverysuccessMessage", deliveryEntity.getLRNo() + " - Save Delivery Successfull!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "delivery";
		}
		return "delivery";
	}

	@RequestMapping(value = "/dbSearchParcelLRNO", method = RequestMethod.GET)
	public @ResponseBody List<String> dbSearchParcelLRNO(@RequestParam(required = true) String lrNumber,
			@RequestParam(required = true) String userId) {
		List<String> booking = new ArrayList<String>();
		try {
			String location = "%";

			if (!userId.equalsIgnoreCase("ADMIN")) {

				location = userRepository.findByUserIdIgnoreCase(userId).getLocation().getId();

			}
			booking = bookingRepository.getLrNumberForDropDown("%" + lrNumber + "%", location);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return booking;
	}

	@RequestMapping(value = "/searchParcelLRNO", method = RequestMethod.GET)
	public String searchParcelLRNO(@RequestParam(required = true) String lrNumber, HttpServletRequest request,
			ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			Booking booking = bookingRepository.findByLrNumber(lrNumber);

			Boolean topayValue = false;

			if (booking != null && booking.getOgplNo() != null) {

				if (booking.getCurrentLocation().equals(booking.getToLocation()) && booking.getPointStatus() == 2) {
					Inventory inventory = inventoryRepository.findByOgplNo(booking.getOgplNo());

					if (Objects.nonNull(booking.getTopay())) {
						topayValue = true;
					}

					model.addAttribute("topayValue", topayValue);
					model.addAttribute("deliveryB", booking);
					model.addAttribute("deliveryI", inventory);
					model.addAttribute("DeliverysuccessMessage", "Search by LR number: " + lrNumber);
				} else {
					model.addAttribute("errormsg", "Not Yet to Reach Location");

				}
			} else {
				model.addAttribute("errormsg", "Invalid LR number provided! ");
			}
			Delivery delivery = new Delivery();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = formatter.format(date);
			delivery.setDeliveryDate(currentDate);
			if (booking != null && booking.getBookedOn() != null) {
				String startDate = booking.getBookedOn();
				Date stDate = formatter.parse(startDate);
				date = formatter.parse(currentDate);
				long diffTime = date.getTime() - stDate.getTime();
				long diffDays = TimeUnit.MILLISECONDS.toDays(diffTime) % 365;
				if (diffDays < 3) {
					delivery.setDemurrage("0");
				} else {
					long charge = (diffDays - 2) * 10;
					delivery.setDemurrage("" + charge);
				}
			}
			setAllVehileListInModel(model);
			setAllLocationListInModel(model);
			model.addAttribute("delivery", delivery);
			model.addAttribute("enabled", false);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search LR number ");
			return "delivery";
		}
		return "delivery";
	}

	/*
	 * @RequestMapping(value = "/searchParcelName/{name}", method =
	 * RequestMethod.GET) public String searchParcelName(@PathVariable("name")
	 * String name, HttpServletRequest request, ModelMap model) { try { // SESSION
	 * VALIDATION if (sessionValidation(request, model) != null) return "login";
	 * Delivery deliveryEntity = deliveryRepository.findByName(name); DeliveryVo
	 * deliveryVo = new DeliveryVo(); BeanUtils.copyProperties(deliveryEntity,
	 * deliveryVo); model.addAttribute("delivery", deliveryVo);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); model.addAttribute("errormsg",
	 * "Failed To search Party Name "); return "delivery"; } return "delivery"; }
	 */

	@RequestMapping(value = "/getCharges", method = RequestMethod.POST)
	public ResponseEntity<String> getCharges(HttpServletRequest request, ModelMap model) {
		try {
			String fromLocation = request.getParameter("fromLocation");
			String toLocation = request.getParameter("toLocation");

			List<Charge> charge = chargeRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
			if (charge != null && charge.size() > 0) {

			} else {
				charge = chargeRepository.findByFromLocationAndToLocation(toLocation, fromLocation);
			}
			Map<String, String> map = new TreeMap<String, String>();
			if (charge != null && charge.size() > 0) {
				for (Charge ch : charge) {
					map.put(ch.getChargetype(), ch.getValue());
				}
			}
			return new ResponseEntity<String>(map.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Charges");
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	@RequestMapping(value = "/getLoadingCharges", method = RequestMethod.POST)
	public ResponseEntity<String> getLoadingCharges(HttpServletRequest request, ModelMap model) {
		try {
			String fromLocation = request.getParameter("fromLocation");
			String toLocation = request.getParameter("toLocation");

			Charge charge = chargeRepository.findByFromLocationAndToLocationAndChargetype(fromLocation, toLocation,
					"LOADING CHARGES");
			if (charge != null && charge.getValue() != null) {

			} else {
				charge = chargeRepository.findByFromLocationAndToLocationAndChargetype(toLocation, fromLocation,
						"LOADING CHARGES");
			}
			if (charge != null && charge.getValue() != null) {
				return new ResponseEntity<String>(charge.getValue(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Charges");
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	@RequestMapping(value = "/getFuelCharges", method = RequestMethod.POST)
	public ResponseEntity<String> getFuelCharges(HttpServletRequest request, ModelMap model) {
		try {
			String fromLocation = request.getParameter("fromLocation");
			String toLocation = request.getParameter("toLocation");

			Charge charge = chargeRepository.findByFromLocationAndToLocationAndChargetype(fromLocation, toLocation,
					"FUEL CHARGES");
			if (charge != null && charge.getValue() != null) {

			} else {
				charge = chargeRepository.findByFromLocationAndToLocationAndChargetype(toLocation, fromLocation,
						"FUEL CHARGES");
			}
			if (charge != null && charge.getValue() != null) {
				return new ResponseEntity<String>(charge.getValue(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Charges");
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	@RequestMapping(value = "/searchFromCustomerName/{phone}", method = RequestMethod.GET)
	public ResponseEntity<Customer> searchFromCustomerName(@PathVariable("phone") Long phone,
			HttpServletRequest request, ModelMap model) {

		Customer customer = null;
		try {
			customer = customerRepository.findByAllPhoneNumber(phone);
			if (customer != null) {
				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Customer Name");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchToCustomerName/{phone}", method = RequestMethod.GET)
	public ResponseEntity<Customer> searchToCustomerName(@PathVariable("phone") Long phone, HttpServletRequest request,
			ModelMap model) {

		Customer customer = null;
		try {
			customer = customerRepository.findByAllPhoneNumber(phone);
			if (customer != null) {
				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Customer Name");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/ogpl/save", method = RequestMethod.POST)
	public String saveOutgoingParcel(HttpServletRequest request, OutgoingParcelVo outgoingParcelVo, ModelMap model) {
		try {

			Boolean connectionPonitStatus = false;
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			OutgoingParcel outgoingParcel = new OutgoingParcel();

			BeanUtils.copyProperties(outgoingParcelVo, outgoingParcel);

			long ogplNo = Utils.getOrderNumber();
			List<Booking> outgoingList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
			for (Booking booking : outgoingList) {
				connectionPonitStatus = false;

				ConnectionPoint connectionPoint = null;
				if (outgoingParcel != null && outgoingParcel.getFromLocation() != null) {

					connectionPoint = connectionPointRepository.findByFromLocationAndToLocation(
							outgoingParcel.getFromLocation(), outgoingParcel.getToLocation());

					if (connectionPoint != null) {
						connectionPonitStatus = true;
					}

					bookingRepository.updateConnectionPointStatus(booking.getLrNumber(), connectionPonitStatus);

					if (booking.isConnectionPoint() && connectionPoint != null
							&& connectionPoint.getCheckPoint() != null) {
						outgoingParcel.setToLocation(connectionPoint.getCheckPoint());
					} else if (!booking.isConnectionPoint() && connectionPoint != null
							&& connectionPoint.getCheckPoint() != null) {
						outgoingParcel.setFromLocation(connectionPoint.getCheckPoint());

					} else {
						// do nothing
					}

				}
				outgoingParcel.setOgplNo(ogplNo);
				String sCurrentDate = Utils.getStringCurrentDatewithFormat("YYYY-MM-dd");
				outgoingParcel.setBookedOn(sCurrentDate);
				outgoingParcelRepository.save(outgoingParcel);

			}
			model.addAttribute("outgoingsuccessmessage", "Parcel Out Successfully");
			model.addAttribute("ogplno", "OGPL Number : " + ogplNo);

			if (outgoingParcel != null) {
				setAllLocationListInModel(model);
				setAllVehileListInModel(model);
				setAllConductorListInModel(model);
				setAllDriverListInModel(model);
//				model.addAttribute("outgoingparcel", outgoingParcel);

				List<Booking> incomeList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
				for (Booking booking : incomeList) {
					if (!booking.isConnectionPoint()) {
						bookingRepository.updateBookingOgplConnPoint(booking.getLrNumber());
					}
				}

				bookingRepository.updateBookingOgpl(ogplNo, outgoingParcel.getOgpnoarray());
				outgoingList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
//				model.addAttribute("outgoingList", outgoingList);
//				model.addAttribute("checkboxchecked", "1");
			}

			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);

		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errormsg", "Failed to out Parcel");
			return "outgoingParcel";
		}
		return "outgoingParcel";
	}

	@RequestMapping(value = "/incoming/save", method = RequestMethod.POST)
	public String saveIncomingParcel(HttpServletRequest request, InventoryVo inventoryVo, ModelMap model) {
		try {

			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			Inventory inventory = new Inventory();
			BeanUtils.copyProperties(inventoryVo, inventory);
			inventoryRepository.save(inventory);
			model.addAttribute("successMessage", "Parcel Income Successfull!");

			if (inventory != null) {
				setAllLocationListInModel(model);
				setAllVehileListInModel(model);
				setAllConductorListInModel(model);
				setAllDriverListInModel(model);
//				model.addAttribute("incomeparcel", inventory);
//				model.addAttribute("ogplno", inventoryVo.getOgplNo());
//				model.addAttribute("fromLocation", inventory.getFromLocation());
//				model.addAttribute("toLocation", inventory.getToLocation());
//				model.addAttribute("bookedOn", inventory.getBookedOn());

				bookingRepository.updateBookingIgplStatus("A", inventory.getLrnoarray());

				List<Booking> incomeList = bookingRepository.findByLrNumberIn(inventory.getLrnoarray());

				for (Booking data : incomeList) {
					if (inventory.getToLocation().equals(data.getToLocation())) {
						bookingRepository.updateBookingIgplCurrentLocationStatus(inventory.getToLocation(), 2,
								data.getLrNumber());
					} else {
						bookingRepository.updateBookingIgplCurrentLocationStatus(inventory.getToLocation(), 0,
								data.getLrNumber());

					}
				}

				/*
				 * model.addAttribute("incomeparcelList", incomeList);
				 * model.addAttribute("checkboxchecked", "1"); List<OutgoingParcel> ogplList; if
				 * (inventory.getBookedOn() != null && inventory.getBookedOn().trim().length() >
				 * 0) { ogplList =
				 * outgoingParcelRepository.findByFromLocationAndToLocationAndBookedOn(
				 * inventory.getFromLocation(), inventory.getToLocation(),
				 * inventory.getBookedOn()); } else { ogplList =
				 * outgoingParcelRepository.findByFromLocationAndToLocation(inventory.
				 * getFromLocation(), inventory.getToLocation());
				 * 
				 * } model.addAttribute("ogplList", ogplList);
				 */

			}

			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);

		} catch (Exception ex) {
			ex.printStackTrace();
			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);
			model.addAttribute("errormsg", "Failed to income Parcel");
			return "incomingParcel";
		}

		return "incomingParcel";
	}

	@RequestMapping(value = "/searchBookingParcelLRNO", method = RequestMethod.GET)
	public String searchBookingParcelLRNO(@RequestParam(required = true) String lrNumber, HttpServletRequest request,
			ModelMap model) {
		try {

			Boolean printStatus = false;

			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			lRNumber = lrNumber;
			if (lrNumber != null && !lrNumber.trim().isEmpty()) {
				Booking bookingEntity = bookingRepository.findByLrNumber(lrNumber);

				if (Objects.nonNull(bookingEntity.getIsPrinted())) {
					printStatus = bookingEntity.getIsPrinted();
				}

				if (bookingEntity != null && bookingEntity.getLrNumber() != null) {

					BookingVo bookingVO = new BookingVo();
					BeanUtils.copyProperties(bookingEntity, bookingVO, "createon", "updatedon");

					model.addAttribute("booking", bookingVO);
					model.addAttribute("printStatus", printStatus);

					model.addAttribute("LRnumber", lRNumber);
					setAllLocationListInModel(model);
					setAllBookednameListInModel(model);
				} else {
					model.addAttribute("LRnumber", lRNumber);
					model.addAttribute("errormsg", "No record(s) found.");
				}
			} else {
				setAllLocationListInModel(model);
				setAllBookednameListInModel(model);
				model.addAttribute("LRnumber", lRNumber);
				model.addAttribute("errormsg", "Enter LR number and try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search LRNO ");
			setAllLocationListInModel(model);
			return "booking";
		}
		return "booking";
	}

	@RequestMapping(value = "load/incomingParcel", method = RequestMethod.GET)
	public String loadIncomingParcel(@RequestParam("fromLocation") String fromLocation,
			@RequestParam("toLocation") String toLocation, @RequestParam("bookedOn") String bookedOn,
			@RequestParam("ogpl") long ogpl, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			OutgoingParcel outgoingParcel = outgoingParcelRepository.findByOgplNo(ogpl);
			ArrayList<String> list = new ArrayList<String>();
			list.add("P");
			list.add("A");
			List<Booking> incomingList = bookingRepository
					.getByLrNumberInAndIgplStatusIn(outgoingParcel.getOgpnoarray(), list);

			model.addAttribute("incomeparcelList", incomingList);
			model.addAttribute("incomeparcel", outgoingParcel);
			if (outgoingParcel != null) {
				model.addAttribute("fromLocation", outgoingParcel.getFromLocation());
				model.addAttribute("toLocation", outgoingParcel.getToLocation());
				model.addAttribute("bookedOn", outgoingParcel.getBookedOn());
			}
			model.addAttribute("ogplno", ogpl);

			List<OutgoingParcel> ogplList;
			if (bookedOn != null && bookedOn.trim().length() > 0) {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocationAndBookedOn(fromLocation, toLocation,
						bookedOn);
			} else {
				ogplList = outgoingParcelRepository.findByFromLocationAndToLocation(fromLocation, toLocation);

			}
			
			List<OutgoingParcel> finalList=new ArrayList<OutgoingParcel>();

			List<List<OutgoingParcel>> fullList = ogplList.stream()
			                .collect(Collectors.groupingBy(OutgoingParcel::getOgplNo)) // Or another collector
			                .entrySet()
			                .stream()
			                .map(Map.Entry::getValue)
			                .collect(Collectors.toList());

			for(List<OutgoingParcel> data:fullList)
			{
				finalList.add(data.get(0));
			}
			
			model.addAttribute("ogplList", finalList);

			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
			setAllConductorListInModel(model);
			setAllDriverListInModel(model);
			model.addAttribute("successMessage", "Parcels listed to income for OGPL: " + ogpl);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to list OGPL Parcels");
		}
		return "incomingParcel";
	}

	@RequestMapping("/bookingReq/delete")
	public String userDelete(@RequestParam("bid") String id, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			if (id != null) {
				model.addAttribute("LRNumber", lRNumber);
				bookingRepository.deleteById(Long.parseLong(id));
				model.addAttribute("bookingsuccessmessage",
						"Booking request successfully deleted.  " + " LR Number : " + lRNumber);
			} else {
				model.addAttribute("errormsg", "Invalid booking request! ");
				return "booking";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Invalid booking request! ");
			return "booking";
		}
		return "booking";
	}

	@RequestMapping("/deliveryinventory")
	public String inventoryList(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			// Pageable firstPageWithTwoElements = PageRequest.of(0, 1000);

			// Page<Delivery> allList =
			// deliveryListRepository.findAll(firstPageWithTwoElements);

			// List<Delivery> deliveryList = new ArrayList<Delivery>();
			// if(allList != null && allList.hasContent()) {
			// deliveryList = allList.getContent();
			// }else {
			// model.addAttribute("errormsg", "No records!");
			// }

			String userId = request.getSession().getAttribute("USER_LOGIN_ID").toString();
			String location = "%";

			if (!userId.equalsIgnoreCase("ADMIN")) {

				location = userRepository.findByUserIdIgnoreCase(userId).getLocation().getId();

			}

			List<Booking> allList = bookingRepository.getDeliveryInventory(location);

			model.addAttribute("deliveryinventory", allList);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Something is wrong! please try again.");
			return "login";
		}
		return "deliveryinventory";
	}

	@RequestMapping("/delivery")
	public String delivery(HttpServletRequest request, ModelMap model) {
		// SESSION VALIDATION
		if (sessionValidation(request, model) != null)
			return "login";
		// setAllLocationListInModel(model);

		return "delivery";
	}

	@RequestMapping("/bookinginventory")
	public String deliveryList(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			String location = "%";

			String userId = request.getSession().getAttribute("USER_LOGIN_ID").toString();

			if (!userId.equalsIgnoreCase("ADMIN")) {

				location = userRepository.findByUserIdIgnoreCase(userId).getLocation().getId();

			}

			String fromlocationcode = "" + request.getSession().getAttribute("USER_LOCATIONID");
			List<Booking> allList = bookingRepository.getBookingInventoryNew(location);

			model.addAttribute("bookinginventory", allList);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Something is wrong! please try again.");
			return "login";
		}
		return "bookinginventory";
	}

	@RequestMapping("/inventoryMenu")
	public String inventoryMenu(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Something is wrong! please try again.");
			return "login";
		}
		return "inventoryMenu";
	}

	private void setAllDriverListInModel(ModelMap model) {
		Iterable<Driver> locaIterable = driverRepository.findAll();
		model.addAttribute("driverList", locaIterable);
	}

	private void setAllConductorListInModel(ModelMap model) {
		Iterable<Conductor> locaIterable = conductorRepository.findAll();
		model.addAttribute("conductorList", locaIterable);
	}

	private void setAllBookednameListInModel(ModelMap model) {
		Iterable<BookedBy> locaIterable = bookedByRepository.findAll();
		model.addAttribute("bookedNameList", locaIterable);
	}

	@RequestMapping("/paymentTypeListing")
	public String paymentTypeListing(HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<PaymentType> paymentTypeListing = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentTypeListing);
//			Iterable<ExpenceCategory> expenceCategory = expenceCategoryRepository.findAll();
//			model.addAttribute("expenceCategoryListing", expenceCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "paymentTypeListing";
	}

	@RequestMapping(value = "/addPaymentType", method = RequestMethod.POST)
	public String savePaymentType(HttpServletRequest request, PaymentTypeVo paymentTypeVo, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			if (paymentTypeVo != null) {
				try {
					PaymentType paymentType = paymentTypeRepository.findById(paymentTypeVo.getId()).get();
					if (paymentType != null && paymentType.getId() != null) {
						model.addAttribute("errormsg", "Payment Type [" + paymentType.getId() + "] is already exist! ");
						return "addPaymentType";
					}
				} catch (Exception e) {
				}
			}

			PaymentType paymentTypeEntity = new PaymentType();

			BeanUtils.copyProperties(paymentTypeVo, paymentTypeEntity, "createon", "updatedon");
			paymentTypeEntity = paymentTypeRepository.save(paymentTypeEntity);
			model.addAttribute("successMessage", paymentTypeEntity.getPaymentType() + " - Payment type added! ");
			Iterable<PaymentType> paymentTypeIterable = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentTypeIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new sub payment type! ");
			return "addPaymentType";
		}
		return "paymentTypeListing";
	}

	@RequestMapping(value = "/editPaymentType", method = RequestMethod.POST)
	public String updatePaymentType(HttpServletRequest request, PaymentTypeVo paymentTypeVo, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			PaymentType paymentTypeEntity = new PaymentType();

			BeanUtils.copyProperties(paymentTypeVo, paymentTypeEntity, "createon", "updatedon");
			paymentTypeEntity = paymentTypeRepository.save(paymentTypeEntity);
			model.addAttribute("successMessage", paymentTypeEntity.getPaymentType() + " - payment type updated! ");
			Iterable<PaymentType> paymentTypeIterable = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentTypeIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update payment type! ");
			return "addPaymentType";
		}
		return "paymentTypeListing";
	}

	@RequestMapping(value = "/paymentType/edit/{id}", method = RequestMethod.GET)
	public String paymentTypeEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			PaymentType paymentType = paymentTypeRepository.findById(id).get();
			PaymentTypeVo paymentTypeVo = new PaymentTypeVo();
			BeanUtils.copyProperties(paymentType, paymentTypeVo);
			model.addAttribute("paymentType", paymentTypeVo);
			Iterable<PaymentType> paymentTypeIterable = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentTypeIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addPaymentType";
	}

	@RequestMapping(value = "/paymentType/delete/{id}", method = RequestMethod.GET)
	public String paymentTypeDelete(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			paymentTypeRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<PaymentType> paymentTypeIterable = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentTypeIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "paymentTypeListing";
	}

	@RequestMapping(value = "/paymentType/add", method = RequestMethod.GET)
	public String paymentTypeAdd(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<PaymentType> paymentType = paymentTypeRepository.findAll();
			model.addAttribute("paymentTypeListing", paymentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addPaymentType";
	}

	@RequestMapping(value = "/booking/print", method = RequestMethod.GET)
	public void print(@RequestParam(required = true) String lrNumber, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) {
		try {
			if (sessionValidation(request, model) == null) {

				Booking bookingEntity = bookingRepository.findByLrNumber(lrNumber);

				// change printing status

				bookingEntity.setIsPrinted(true);
				bookingRepository.save(bookingEntity);

				if (bookingEntity != null && bookingEntity.getLrNumber() != null) {
					BookingVo bookingVO = new BookingVo();
					bookingVO.setCreateon(bookingEntity.getCreateon());
					BeanUtils.copyProperties(bookingEntity, bookingVO, "createon", "updatedon");

					Optional<Location> location = locationRepository.findById(bookingEntity.getFromLocation());
					if (location.isPresent()) {
						bookingVO.setBillDesc(location.get().getAddress());
					}

					byte[] bookingData = LuggageSlipGenerator.getInstance().getReportDataSource(bookingVO);
					String base64Response = Base64.getEncoder().encodeToString(bookingData);
					response.getWriter().write(base64Response);
				} else {
					response.getWriter().write("LRnumber No record(s) found.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@RequestMapping(value = "/delivery/print", method = RequestMethod.GET)
	public void printDelivery(@RequestParam(required = true) String lrNumber, HttpServletRequest request,
			ModelMap model, HttpServletResponse response) {
		try {
			if (sessionValidation(request, model) == null) {

				Delivery deliveryEntity = deliveryRepository.findByLRNo(lrNumber);

				System.out.println(deliveryEntity.getCreateon());

				if (deliveryEntity != null && deliveryEntity.getLRNo() != null) {
					DeliveryVo deliveryVo = new DeliveryVo();
					deliveryVo.setCreateon(deliveryEntity.getCreateon());
					BeanUtils.copyProperties(deliveryEntity, deliveryVo, "createon", "updatedon");

					System.out.println(deliveryVo.getCreateon());

					byte[] bookingData = DeliverySlipGenerator.getInstance().getReportDataSource(deliveryVo);
					String base64Response = Base64.getEncoder().encodeToString(bookingData);
					response.getWriter().write(base64Response);
				} else {
					response.getWriter().write("LRnumber No record(s) found.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// add by guna

	@RequestMapping(value = "/customerListing", method = RequestMethod.GET)
	public String adminListingSubmit(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<Customer> userList = customerRepository.findAllByOrderByIdAsc();
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "customerListing";
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.GET)
	public String customerAdd(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "customeredit";
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public String saveCustomer(HttpServletRequest request, Customer customerRequest, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";

			Customer customer = customerRepository.findByAllPhoneNumber(customerRequest.getPhoneNumber());

			if (Objects.nonNull(customer)) {
				customer.setDiscount(customerRequest.getDiscount());
				customer = customerRepository.save(customer);
			} else {
				customer = customerRepository.save(customerRequest);
			}

			model.addAttribute("successMessage", "Customer Data Saved Successfully !");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Customer Data Not Saved!");
		}

		model.addAttribute("userList", customerRepository.findAll());

		return "customerListing";
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public String customerEdit(Customer customerRequest, HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			model.addAttribute("user", customerRepository.findById(customerRequest.getId()).get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "customeredit";
	}

	@RequestMapping("/deliveryDiscount")
	public String deliveryDiscount(HttpServletRequest request, ModelMap model) {
		// SESSION VALIDATION
		if (sessionValidation(request, model) != null)
			return "login";
		// setAllLocationListInModel(model);

		return "deliveryDiscount";
	}

	@RequestMapping(value = "/searchParcelLRNOdbDiscount", method = RequestMethod.GET)
	public String searchParcelLRNOdbDiscount(@RequestParam(required = true) String lrNumber, HttpServletRequest request,
			ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			Booking booking = bookingRepository.findByLrNumber(lrNumber);

			Boolean topayValue = false;

			if (booking != null && booking.getOgplNo() != null) {

				if (booking.getCurrentLocation().equals(booking.getToLocation()) && booking.getPointStatus() == 2) {
					Inventory inventory = inventoryRepository.findByOgplNo(booking.getOgplNo());

					if (Objects.nonNull(booking.getTopay())) {
						topayValue = true;
					}

					model.addAttribute("topayValue", topayValue);
					model.addAttribute("deliveryB", booking);
					model.addAttribute("deliveryI", inventory);
					model.addAttribute("DeliverysuccessMessage", "Search by LR number: " + lrNumber);
				} else {
					model.addAttribute("errormsg", "Not Yet to Reach Location");

				}
			} else {
				model.addAttribute("errormsg", "Invalid LR number provided! ");
			}
			Delivery delivery = new Delivery();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = formatter.format(date);
			delivery.setDeliveryDate(currentDate);
			if (booking != null && booking.getBookedOn() != null) {
				String startDate = booking.getBookedOn();
				Date stDate = formatter.parse(startDate);
				date = formatter.parse(currentDate);
				long diffTime = date.getTime() - stDate.getTime();
				long diffDays = TimeUnit.MILLISECONDS.toDays(diffTime) % 365;
				if (diffDays < 3) {
					delivery.setDemurrage("0");
				} else {
					long charge = (diffDays - 2) * 10;
					delivery.setDemurrage("" + charge);
				}
			}
			setAllVehileListInModel(model);
			setAllLocationListInModel(model);
			model.addAttribute("delivery", delivery);
			model.addAttribute("enabled", false);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search LR number ");
			return "deliveryDiscount";
		}
		return "deliveryDiscount";
	}

	@RequestMapping(value = "/addDeliveryDiscount", method = RequestMethod.POST)
	public String addDeliveryDiscount(HttpServletRequest request, DeliveryVo deliveryVo, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";

			bookingRepository.updateDeliveyDiscount(deliveryVo.getDeliveryDiscount(), deliveryVo.getLRNo());

			model.addAttribute("delivery", deliveryVo);
			model.addAttribute("DeliverysuccessMessage",
					deliveryVo.getLRNo() + " - Add Delivery Discount Successfull!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "deliveryDiscount";
		}
		return "deliveryDiscount";
	}
}
