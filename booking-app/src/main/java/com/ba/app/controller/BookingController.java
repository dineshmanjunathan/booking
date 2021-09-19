package com.ba.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ba.app.entity.Booking;
import com.ba.app.entity.CountryCode;
import com.ba.app.entity.Customer;
import com.ba.app.entity.Delivery;
import com.ba.app.entity.Inventory;
import com.ba.app.entity.Location;
import com.ba.app.entity.PayType;
import com.ba.app.entity.User;
import com.ba.app.entity.Vehicle;
import com.ba.app.model.BookingRepository;
import com.ba.app.model.CustomerRepository;
import com.ba.app.model.DeliveryRepository;
import com.ba.app.model.InventoryRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.model.PayOptionRepository;
import com.ba.app.model.VehicleRepository;
import com.ba.app.vo.BookingVo;
import com.ba.app.vo.DeliveryVo;
import com.ba.app.vo.InventoryVo;
import com.ba.app.vo.LocationVo;
import com.ba.app.vo.PayOptionVo;
import com.ba.app.vo.VehicleVo;
import com.ba.utils.Utils;
import com.ba.app.vo.OutgoingParcelVo;
import com.ba.app.model.OutgoingParcelRepository;
import com.ba.app.entity.OutgoingParcel;

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
	private InventoryRepository inventoryRepository;;
	
	
	@RequestMapping(value = "/booking/save", method = RequestMethod.POST)
	public String saveBookingDetails(HttpServletRequest request, BookingVo bookingVo, ModelMap model) {
		try {
		Booking booking = new Booking();
		BeanUtils.copyProperties(bookingVo, booking);
		model.addAttribute("bookingsuccessmessage", "Booked Successfully.");
		model.addAttribute("LRNumber", "LR Number : "+booking.getLrNumber());
		
		Booking bLrNo = bookingRepository.findByLrNumber(booking.getLrNumber());
		if(bLrNo!=null && bLrNo.getLrNumber()!=null) {
			booking.setId(bLrNo.getId());
			bookingRepository.save(booking);
		}else {
			//SAVE SEQ FOR LR
			bookingRepository.getNextLRNumber();
			bookingRepository.save(booking);
			saveFromCustomer(bookingVo);
			saveToCustomer(bookingVo);

		}
		setAllLocationListInModel(model);
		}catch(Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errormsg", "Failed to Book ");
			return "booking";
		}
		return "bookingsuccess";
	}
	private void saveFromCustomer(BookingVo bookingVo) {
		
		//System.out.println("From Phone ----- "+bookingVo.get);
		if(customerRepository.findByPhoneNumber(bookingVo.getFrom_phone())==null) {
			Customer customer=new Customer();
			customer.setCustName(bookingVo.getFromName());
			customer.setPhoneNumber(bookingVo.getFrom_phone());
			customerRepository.save(customer);
		}
	}
	private void saveToCustomer(BookingVo bookingVo) {
		if(customerRepository.findByPhoneNumber(bookingVo.getTo_phone())==null) {
			Customer customer=new Customer();
			customer.setCustName(bookingVo.getToName());
			customer.setPhoneNumber(bookingVo.getTo_phone());
			customerRepository.save(customer);
		}
	}
	@RequestMapping("/outgoingParcel")
	public String outgoingParcel(HttpServletRequest request, ModelMap model) {
		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		return "outgoingParcel";
	}
	
	@RequestMapping("/incomingParcel")
	public String incomingParcel(HttpServletRequest request, ModelMap model) {
		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		return "incomingParcel";
	}
	
	@RequestMapping("/booking")
	public String booking(HttpServletRequest request,ModelMap model) {
		setAllLocationListInModel(model);
		Long nextLRNumber=bookingRepository.getcurrentLRNumber();
		String fromlocationcode = ""+request.getSession().getAttribute("USER_LOCATIONID");
		String sLR="";
		if(fromlocationcode!=null && !fromlocationcode.isEmpty() && !fromlocationcode.equalsIgnoreCase("NULL")) {
			sLR = fromlocationcode+"/"+LocalDate.now()+"/"+nextLRNumber;
		}else {
			sLR = LocalDate.now()+"/"+nextLRNumber;
		}
		
		model.addAttribute("LRnumber", sLR);
		
		return "booking";
	}

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public String saveConfigure(HttpServletRequest request, LocationVo locationVo, ModelMap model) {
		try {			
			
			if(locationVo!=null) {
				try {
					Location location = locationRepository.findById(locationVo.getId()).get();
					if(location!=null && location.getId()!=null) {
						model.addAttribute("errormsg", "Location Code ["+location.getId()+"] is already exist! ");
						return "addLocation";
					}
				} catch (Exception e) {
					// do nothing
				}
			}
			
			Location locationEntity = new Location();

			BeanUtils.copyProperties(locationVo, locationEntity, "createon", "updatedon");
			locationEntity=	locationRepository.save(locationEntity);
			model.addAttribute("successMessage", locationEntity.getLocation()+" - location added! ");
			//model.addAttribute("location", locationEntity);
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
			Location locationEntity = new Location();

			BeanUtils.copyProperties(locationVo, locationEntity, "createon", "updatedon");
			locationEntity=	locationRepository.save(locationEntity);
			model.addAttribute("successMessage", locationEntity.getLocation()+" - location added! ");
			//model.addAttribute("location", locationEntity);
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
			Location location = locationRepository.findById(id).get();
			LocationVo locationVo = new LocationVo();
			BeanUtils.copyProperties(location, locationVo);
			model.addAttribute("location", locationVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addLocation";
	}
	@RequestMapping(value = "/location/delete/{id}", method = RequestMethod.GET)
	public String locationDelete(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			locationRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
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
			PayType payOptionEntity = new PayType();

			BeanUtils.copyProperties(payOptionVo, payOptionEntity, "createon", "updatedon");
			payOptionEntity=	payOptionRepository.save(payOptionEntity);
			model.addAttribute("successMessage", payOptionEntity.getPayOption()+" - Payoption added! ");
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
			Iterable<PayType> locaIterable = payOptionRepository.findAll();
			model.addAttribute("payOptionListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "payOptionListing";
	}
	
	@RequestMapping(value = "/payOption/delete/{id}", method = RequestMethod.GET)
	public String payOptionDelete(@PathVariable ("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
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
	public String importIncomingParcel(@RequestParam("fromLocation") String fromLocation,@RequestParam("toLocation") String toLocation,@RequestParam("bookedOn") String bookedOn,@RequestParam("ogplno") long ogplNo,HttpServletRequest request, ModelMap model) {
		OutgoingParcel incomeDetails= outgoingParcelRepository.findByFromLocationAndToLocationAndBookedOnAndOgplNo(fromLocation,toLocation,bookedOn,ogplNo);
		model.addAttribute("incomeDetailsList", incomeDetails);
		List<Booking> incomingList = bookingRepository.findByLrNumberIn(incomeDetails.getOgpnoarray());
		model.addAttribute("incomeList", incomingList);
		model.addAttribute("incomeparcel", incomeDetails);
		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		return "incomingParcel";
	}
	@RequestMapping(value = "get/outgoingParcel", method = RequestMethod.GET)
	public String importOutgoingParcel(@RequestParam("fromLocation") String fromLocation,@RequestParam("toLocation") String toLocation,@RequestParam("bookedOn") String bookedOn,HttpServletRequest request, ModelMap model) {
		List<Booking> outgoingList= bookingRepository.findByFromLocationAndToLocationAndBookedOnAndOgplNoIsNull(fromLocation,toLocation,bookedOn);
		model.addAttribute("outgoingList", outgoingList);
		OutgoingParcel outgoingParcel = new OutgoingParcel();
		outgoingParcel.setFromLocation(fromLocation);
		outgoingParcel.setToLocation(toLocation);
		outgoingParcel.setBookedOn(bookedOn);
		model.addAttribute("outgoingparcel", outgoingParcel);
		setAllLocationListInModel(model);
		setAllVehileListInModel(model);
		return "outgoingParcel";
	}
	
	private void setAllVehileListInModel(ModelMap model) {
		Iterable<Vehicle> vechileIterable = vehicleRepository.findAll();
		model.addAttribute("vehicleList", vechileIterable);
	}
	
	@RequestMapping(value = "/payOption/edit/{id}", method = RequestMethod.GET)
	public String payOptionEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
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
			Vehicle vehicleEntity = new Vehicle();
			BeanUtils.copyProperties(vehicleVo, vehicleEntity, "createon", "updatedon");
			vehicleEntity=	vehicleRepository.save(vehicleEntity);
			model.addAttribute("successMessage", vehicleEntity.getVehicle()+" - Vehicle added! ");
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
			Iterable<Vehicle> locaIterable = vehicleRepository.findAll();
			model.addAttribute("vehicleListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vehicleListing";
	}
	
	@RequestMapping(value = "/vehicle/delete/{id}", method = RequestMethod.GET)
	public String vehicleDelete(@PathVariable ("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
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
			Delivery deliveryEntity = new Delivery();

			BeanUtils.copyProperties(deliveryVo, deliveryEntity, "createon", "updatedon");
			deliveryEntity=	deliveryRepository.save(deliveryEntity);
			model.addAttribute("delivery", deliveryEntity);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "delivery";
		}
		return "delivery";
	}
	
	@RequestMapping(value = "/searchParcelLRNO/{lRNo}", method = RequestMethod.GET)
	public String searchParcelLRNO(@PathVariable("lRNo") String lRNo, HttpServletRequest request, ModelMap model) {
		try {
			Delivery deliveryEntity = deliveryRepository.findByLRNo(Long.parseLong(lRNo));
			DeliveryVo deliveryVo=new DeliveryVo();
			BeanUtils.copyProperties(deliveryEntity, deliveryVo);
			model.addAttribute("delivery", deliveryVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search LRNO ");
			return "delivery";
		}
		return "delivery";
	}
	@RequestMapping(value = "/searchParcelName/{name}", method = RequestMethod.GET)
	public String searchParcelName(@PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		try {
			Delivery deliveryEntity = deliveryRepository.findByName(name);
			DeliveryVo deliveryVo=new DeliveryVo();
			BeanUtils.copyProperties(deliveryEntity, deliveryVo);
			model.addAttribute("delivery", deliveryVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Party Name ");
			return "delivery";
		}
		return "delivery";
	}
	
	@RequestMapping(value = "/searchCustomerName/{phone}", method = RequestMethod.GET)
	public ResponseEntity<String> searchCustomerName(@PathVariable("phone") Long phone, HttpServletRequest request, ModelMap model) {
		try {
			Customer customer=customerRepository.findByPhoneNumber(phone);
			if(customer!=null) {
				return new ResponseEntity<String>(customer.getCustName(), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search Customer Name");
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ogpl/save", method = RequestMethod.POST)
	public String saveOutgoingParcel(HttpServletRequest request, OutgoingParcelVo outgoingParcelVo, ModelMap model) {
		try {
		OutgoingParcel outgoingParcel = new OutgoingParcel();
		
		BeanUtils.copyProperties(outgoingParcelVo, outgoingParcel);
		long ogplNo=Utils.getOrderNumber();
		outgoingParcel.setOgplNo(ogplNo);
		outgoingParcelRepository.save(outgoingParcel);
		model.addAttribute("outgoingsuccessmessage", "Parcel Out Successfully");
		model.addAttribute("ogplno", "OGPL Number : "+ogplNo);
		if(outgoingParcel!=null) {
			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
		model.addAttribute("outgoingparcel", outgoingParcel);
		bookingRepository.updateBookingOgpl(ogplNo,outgoingParcel.getOgpnoarray());
		List<Booking> outgoingList = bookingRepository.findByLrNumberIn(outgoingParcel.getOgpnoarray());
		model.addAttribute("outgoingList", outgoingList);
		model.addAttribute("checkboxchecked", "1");
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errormsg", "Failed to out Parcel");
			return "outgoingParcel";
		}
		return "outgoingParcel";
	}
	
	@RequestMapping(value = "/incoming/save", method = RequestMethod.POST)
	public String saveIncomingParcel(HttpServletRequest request, InventoryVo inventoryVo, ModelMap model) {
		try {
		Inventory inventory = new Inventory();
		BeanUtils.copyProperties(inventoryVo, inventory);
		inventoryRepository.save(inventory);
		model.addAttribute("incomingsuccessmessage", "Income Parcel Successfully");
		if(inventory!=null) {
			setAllLocationListInModel(model);
			setAllVehileListInModel(model);
		model.addAttribute("incomeparcel", inventory);
		//bookingRepository.updateBookingOgpl(ogplNo,outgoingParcel.getOgpnoarray());
		List<Booking> incomeList = bookingRepository.findByLrNumberIn(inventory.getLrnoarray());
		model.addAttribute("incomeList", incomeList);
		model.addAttribute("checkboxchecked", "1");
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errormsg", "Failed to income Parcel");
			return "incomingParcel";
		}
		return "incomingParcel";
	}
	
	
	@RequestMapping(value = "/searchBookingParcelLRNO", method = RequestMethod.GET)
	public String searchBookingParcelLRNO(@RequestParam(required = true) String lrNumber, HttpServletRequest request, ModelMap model) {
		try {
			Booking bookingEntity = bookingRepository.findByLrNumber(lrNumber);
			if(bookingEntity!=null && bookingEntity.getLrNumber()!=null) {
				
				BookingVo bookingVO=new BookingVo();
				BeanUtils.copyProperties(bookingEntity, bookingVO, "createon", "updatedon");
				
				model.addAttribute("booking", bookingVO);
				model.addAttribute("LRnumber", bookingEntity.getLrNumber());
				setAllLocationListInModel(model);
			}else {
				model.addAttribute("LRnumber", lrNumber);
				model.addAttribute("errormsg", "No record(s) found.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed To search LRNO ");
			return "booking";
		}
		return "booking";
	}
	
	@RequestMapping("/bookingReq/delete")
	public String userDelete(@RequestParam("bid") String id, HttpServletRequest request, ModelMap model) {
		try {
			if (id != null) {
				bookingRepository.deleteById(Long.parseLong(id));
				model.addAttribute("bookingsuccessmessage", "Booking request successfully deleted");
			} else {
				model.addAttribute("errormsg", "Invalid booking request! ");
				return "booking";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Invalid booking request! ");
			return "booking";
		}
		return "bookingsuccess";
	}
}
