package com.ba.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ba.app.entity.BookedBy;
import com.ba.app.entity.Charge;
import com.ba.app.entity.Conductor;
import com.ba.app.entity.Driver;
import com.ba.app.entity.Location;
import com.ba.app.model.BookedByRepository;
import com.ba.app.model.ChargeRepository;
import com.ba.app.model.ConductorRepository;
import com.ba.app.model.DriverRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.vo.BookedByVo;
import com.ba.app.vo.ChargeVo;
import com.ba.app.vo.ConductorVo;
import com.ba.app.vo.DriverVo;
import com.ba.utils.ConfigProperties;

@Controller
public class AdminController {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private ConductorRepository conductorRepository;
	
	@Autowired
	private BookedByRepository bookedByRepository;
	
	@Autowired
	private ChargeRepository chargeRepository;

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
	@RequestMapping("/driverListing")
	public String driverListing(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Iterable<Driver> locaIterable = driverRepository.findAll();
			model.addAttribute("driverListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "driverListing";
	}
	@RequestMapping("/conductorListing")
	public String conductorListing(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Iterable<Conductor> locaIterable = conductorRepository.findAll();
			model.addAttribute("conductorListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "conductorListing";
	}
	@RequestMapping("/bookedByListing")
	public String countryCodeListing(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Iterable<BookedBy> locaIterable = bookedByRepository.findAll();
			model.addAttribute("bookedByListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookedByListing";
	}
	@RequestMapping("/chargeListing")
	public String chargeListing(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			List<Charge> locaIterable = chargeRepository.getAllChargeData();

			
			List<ChargeVo> listChargeVos = getListOfChargeData(locaIterable);
			
			
			
			model.addAttribute("chargeListing", listChargeVos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "chargeListing";
	}
	private List<ChargeVo> getListOfChargeData(List<Charge> locaIterable) {
		
		String tempFromLocation="";
		String tempToLocation="";
		ChargeVo chargeVo=new ChargeVo();
		
		
		
		
		List<ChargeVo> listChargeVos=new ArrayList<ChargeVo>();
		for(Charge data:locaIterable)
		{
			if(!tempFromLocation.equals(data.getFromLocation()) || !tempToLocation.equals(data.getToLocation()))
			{
				 chargeVo=new ChargeVo();
			}
			
			if(data.getChargetype().equals(ConfigProperties.FREIGHT_CHARGES.get()))
			{
				chargeVo.setFreight(data.getValue());
			}
			
			if(data.getChargetype().equals(ConfigProperties.LOADING_CHARGES.get()))
			{
				chargeVo.setLoading(data.getValue());
			}
			
			if(data.getChargetype().equals(ConfigProperties.FUEL_CHARGES.get()))
			{
				chargeVo.setFuel(data.getValue());
			}
			chargeVo.setFromLocation(data.getFromLocation());
			chargeVo.setToLocation(data.getToLocation());
			listChargeVos.add(chargeVo);
			tempFromLocation=data.getFromLocation();
			tempToLocation=data.getToLocation();
			
		}
		return listChargeVos.stream().distinct().collect(Collectors.toList());
	}
	@RequestMapping(value = "/addDriver", method = RequestMethod.POST)
	public String saveDelivery(HttpServletRequest request, DriverVo driverVo, ModelMap model) {
		try {		
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			if(driverVo!=null) {
				try {
					Driver driver = driverRepository.findById(driverVo.getId()).get();
					if(driver!=null && driver.getId()!=null) {
						model.addAttribute("errormsg", "Driver Code ["+driver.getId()+"] is already exist! ");
						return "addDriver";
					}
				} catch (Exception e) {
					// do nothing
				}
			}
			Driver driverEntity = new Driver();

			BeanUtils.copyProperties(driverVo, driverEntity, "createon", "updatedon");
			driverEntity=	driverRepository.save(driverEntity);
			
			model.addAttribute("driver", driverEntity);
			model.addAttribute("successMessage", "Driver successfully added");
			
			Iterable<Driver> locaIterable = driverRepository.findAll();
			model.addAttribute("driverListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addDriver";
		}
		return "driverListing";
	}
	@RequestMapping(value = "/driver/edit/{id}", method = RequestMethod.GET)
	public String driverEdit(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Driver driverEntity  = driverRepository.findById(id).get();
			DriverVo driverVo = new DriverVo();
			BeanUtils.copyProperties(driverEntity, driverVo);
			model.addAttribute("driver", driverVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addDriver";
	}
	@RequestMapping(value = "/driver/delete/{id}", method = RequestMethod.GET)
	public String driverDelete(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			driverRepository.deleteById(id);
			model.addAttribute("successMessage", "Deleted Successfully");
			Iterable<Driver> locaIterable = driverRepository.findAll();
			model.addAttribute("driverListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "driverListing";
	}
	
	@RequestMapping(value = "/addConductor", method = RequestMethod.POST)
	public String saveConductor(HttpServletRequest request, ConductorVo conductorVo, ModelMap model) {
		try {		
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			if(conductorVo!=null) {
				try {
					Conductor conductor = conductorRepository.findById(conductorVo.getId()).get();
					if(conductor!=null && conductor.getId()!=null) {
						model.addAttribute("errormsg", "Conductor Code ["+conductor.getId()+"] is already exist! ");
						return "addConductor";
					}
				} catch (Exception e) {
					// do nothing
				}
			}
			Conductor conductorEntity = new Conductor();

			BeanUtils.copyProperties(conductorVo, conductorEntity, "createon", "updatedon");
			conductorEntity =	conductorRepository.save(conductorEntity);
			
			model.addAttribute("coductor", conductorEntity);
			model.addAttribute("successMessage", "Conductor successfully added");
			
			Iterable<Conductor> locaIterable = conductorRepository.findAll();
			model.addAttribute("conductorListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addConductor";
		}
		return "conductorListing";
	}
	@RequestMapping(value = "/conductor/edit/{id}", method = RequestMethod.GET)
	public String conductorEdit(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Conductor conductorVoEntity  = conductorRepository.findById(id).get();
			ConductorVo conductorVo = new ConductorVo();
			BeanUtils.copyProperties(conductorVoEntity, conductorVo);
			model.addAttribute("conductor", conductorVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addConductor";
	}
	@RequestMapping(value = "/conductor/delete/{id}", method = RequestMethod.GET)
	public String conductorDelete(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			conductorRepository.deleteById(id);
			model.addAttribute("successMessage", "Deleted Successfully");
			Iterable<Conductor> locaIterable = conductorRepository.findAll();
			model.addAttribute("conductorListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "conductorListing";
	}
	
	@RequestMapping(value = "/addBookedBy", method = RequestMethod.POST)
	public String saveBookedBy(HttpServletRequest request, BookedByVo bookedByVo, ModelMap model) {
		try {		
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			if(bookedByVo!=null) {
				try {
					BookedBy bookedBy = bookedByRepository.findById(bookedByVo.getId()).get();
					if(bookedBy!=null && bookedBy.getId()!=null) {
						model.addAttribute("errormsg", "Name ["+bookedBy.getId()+"] is already exist! ");
						return "addBookedBy";
					}
				} catch (Exception e) {
					// do nothing
				}
			}
			BookedBy bookedByEntiry = new BookedBy();

			BeanUtils.copyProperties(bookedByVo, bookedByEntiry, "createon", "updatedon");
			bookedByEntiry = bookedByRepository.save(bookedByEntiry);
			
			model.addAttribute("bookedby", bookedByEntiry);
			model.addAttribute("successMessage", "Booked By successfully added");
			
			Iterable<BookedBy> locaIterable = bookedByRepository.findAll();
			model.addAttribute("bookedByListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addBookedBy";
		}
		return "bookedByListing";
	}
	@RequestMapping(value = "/bookedby/edit/{id}", method = RequestMethod.GET)
	public String bookedByEdit(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			BookedBy bookedByEntiry  = bookedByRepository.findById(id).get();
			BookedByVo bookedByVo = new BookedByVo();
			BeanUtils.copyProperties(bookedByEntiry, bookedByVo);
			model.addAttribute("bookedby", bookedByVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addBookedBy";
	}
	@RequestMapping(value = "/bookedby/delete/{id}", method = RequestMethod.GET)
	public String bookedByDelete(@PathVariable("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			bookedByRepository.deleteById(id);
			model.addAttribute("successMessage", "Deleted Successfully");
			Iterable<BookedBy> locaIterable = bookedByRepository.findAll();
			model.addAttribute("bookedByListing", locaIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookedByListing";
	}
	@RequestMapping(value = "/editDriver", method = RequestMethod.POST)
	public String updateDriver(HttpServletRequest request, DriverVo driverVo, ModelMap model) {
		try {			
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Driver driverEntity = new Driver();

			BeanUtils.copyProperties(driverVo, driverEntity, "createon", "updatedon");
			driverEntity=	driverRepository.save(driverEntity);
			model.addAttribute("successMessage", driverEntity.getId()+" - Driver has been updated! ");
			//model.addAttribute("location", locationEntity);
			Iterable<Driver> locaIterable = driverRepository.findAll();
			model.addAttribute("driverListing", locaIterable);
			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update driver! ");
			return "addDriver";
		}
		return "driverListing";
	}
	@RequestMapping(value = "/editConductor", method = RequestMethod.POST)
	public String updateConductor(HttpServletRequest request, ConductorVo conductorVo, ModelMap model) {
		try {			
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Conductor conductorEntity = new Conductor();

			BeanUtils.copyProperties(conductorVo, conductorEntity, "createon", "updatedon");
			conductorEntity=	conductorRepository.save(conductorEntity);
			model.addAttribute("successMessage", conductorEntity.getId()+" - Conductor has been updated! ");
			//model.addAttribute("location", locationEntity);
			Iterable<Conductor> locaIterable = conductorRepository.findAll();
			model.addAttribute("conductorListing", locaIterable);
			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update driver! ");
			return "addConductor";
		}
		return "conductorListing";
	}
	@RequestMapping(value = "/editBookedby", method = RequestMethod.POST)
	public String updateBookedby(HttpServletRequest request, BookedByVo bookedByVo, ModelMap model) {
		try {			
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			BookedBy bookedByEntity = new BookedBy();

			BeanUtils.copyProperties(bookedByVo, bookedByEntity, "createon", "updatedon");
			bookedByEntity=	bookedByRepository.save(bookedByEntity);
			model.addAttribute("successMessage", bookedByEntity.getId()+" - Booked name updated! ");
			//model.addAttribute("location", locationEntity);
			Iterable<BookedBy> locaIterable = bookedByRepository.findAll();
			model.addAttribute("bookedByListing", locaIterable);
			// TODO SMS to member mobile number
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update driver! ");
			return "addBookedBy";
		}
		return "bookedByListing";
	}
	@RequestMapping(value = "/addCharge", method = RequestMethod.POST)
	public String saveCharge(HttpServletRequest request, ChargeVo chargeVo, ModelMap model) {
		try {		
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			List<Charge> inputList=new ArrayList<Charge>();
			
			Charge input=new Charge();
			
			if(!chargeVo.getFreight().isEmpty())
			{
				input=new Charge();
				input.setFromLocation(chargeVo.getFromLocation());
				input.setToLocation(chargeVo.getToLocation());
				input.setChargetype(ConfigProperties.FREIGHT_CHARGES.get());
				input.setValue(chargeVo.getFreight());
				inputList.add(input);
			}
			
			if(!chargeVo.getFuel().isEmpty())
			{
				input=new Charge();
				input.setFromLocation(chargeVo.getFromLocation());
				input.setToLocation(chargeVo.getToLocation());
				input.setChargetype(ConfigProperties.FUEL_CHARGES.get());
				input.setValue(chargeVo.getFuel());
				inputList.add(input);
			}
			
			if(!chargeVo.getLoading().isEmpty())
			{
				input=new Charge();
				input.setFromLocation(chargeVo.getFromLocation());
				input.setToLocation(chargeVo.getToLocation());
				input.setChargetype(ConfigProperties.LOADING_CHARGES.get());
				input.setValue(chargeVo.getLoading());
				inputList.add(input);
			}
			
			for(Charge data:inputList)
			{
				setAllLocationListInModel(model);
				if(data.getFromLocation().equals(data.getToLocation())) {
					model.addAttribute("errormsg", "From location and To location should not be the same!");
					return "addCharge";
				}
				List<Charge> list = chargeRepository.findByChargetypeAndFromLocationAndToLocation(data.getChargetype(),data.getFromLocation(),data.getToLocation());
				if(list!=null && list.size()>0) {
					model.addAttribute("errormsg", "Charge Type is already exist! ");
					return "addCharge";
				}else {
					list = chargeRepository.findByChargetypeAndToLocationAndFromLocation(chargeVo.getChargetype(),chargeVo.getFromLocation(),chargeVo.getToLocation());
					if(list!=null && list.size()>0) {
						model.addAttribute("errormsg", "Charge Type is already exist! ");
						model.addAttribute("charge", chargeVo);
						return "addCharge";
					}
				}
				
			}
			
			chargeRepository.saveAll(inputList);
			
			
			List<Charge> locaIterable = chargeRepository.getAllChargeData();
			
			List<ChargeVo> listChargeVos = getListOfChargeData(locaIterable);

			model.addAttribute("chargeListing", listChargeVos);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new location! ");
			return "addCharge";
		}
		return "chargeListing";
	}
	@RequestMapping(value = "/chargeedit/{fromLocation}/{toLocation}", method = RequestMethod.GET)
	public String chargeEdit(@PathVariable("fromLocation") String fromLocation, @PathVariable("toLocation") String toLocation, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			List<Charge> chargeEntiry  = chargeRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
			String tempFromLocation="";
			String tempToLocation="";
			
ChargeVo chargeVo=new ChargeVo();
			
			List<ChargeVo> listChargeVos=new ArrayList<ChargeVo>();
			for(Charge data:chargeEntiry)
			{
				if(!tempFromLocation.equals(data.getFromLocation()) && !tempToLocation.equals(data.getToLocation()))
				{
					 chargeVo=new ChargeVo();
				}
				
				if(data.getChargetype().equals(ConfigProperties.FREIGHT_CHARGES.get()))
				{
					chargeVo.setFreight(data.getValue());
				}
				
				if(data.getChargetype().equals(ConfigProperties.LOADING_CHARGES.get()))
				{
					chargeVo.setLoading(data.getValue());
				}
				
				if(data.getChargetype().equals(ConfigProperties.FUEL_CHARGES.get()))
				{
					chargeVo.setFuel(data.getValue());
				}
				chargeVo.setFromLocation(data.getFromLocation());
				chargeVo.setToLocation(data.getToLocation());
				listChargeVos.add(chargeVo);
				tempFromLocation=data.getFromLocation();
				tempToLocation=data.getToLocation();
				
			}
			
			
			model.addAttribute("charge", listChargeVos.get(0));
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addCharge";
	}
	@RequestMapping(value = "/chargedelete/{fromLocation}/{toLocation}", method = RequestMethod.GET)
	public String chargeDelete(@PathVariable("fromLocation") String fromLocation, @PathVariable("toLocation") String toLocation, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			List<Charge> listCharge=chargeRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
			
			
			for(Charge data:listCharge)
			{
			chargeRepository.deleteById(data.getId());
			}
			
			model.addAttribute("successMessage", "Deleted Successfully");
			List<Charge> locaIterable = chargeRepository.getAllChargeData();
			
			model.addAttribute("chargeListing", getListOfChargeData(locaIterable));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "chargeListing";
	}
	@RequestMapping(value = "/editCharge", method = RequestMethod.POST)
	public String updateCharge(HttpServletRequest request, ChargeVo chargeVo, ModelMap model) {
		try {			
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			
			List<Charge> inputList=new ArrayList<Charge>();
			
			Charge input=new Charge();
			
			if(!chargeVo.getFreight().isEmpty())
			{
				input=chargeRepository.findByFromLocationAndToLocationAndChargetype(chargeVo.getFromLocation(), chargeVo.getToLocation(), ConfigProperties.FREIGHT_CHARGES.get());
				input.setValue(chargeVo.getFreight());
				inputList.add(input);
			}
			
			if(!chargeVo.getFuel().isEmpty())
			{
				input=chargeRepository.findByFromLocationAndToLocationAndChargetype(chargeVo.getFromLocation(), chargeVo.getToLocation(), ConfigProperties.FUEL_CHARGES.get());

				input.setValue(chargeVo.getFuel());
				inputList.add(input);
			}
			
			if(!chargeVo.getLoading().isEmpty())
			{
				input=chargeRepository.findByFromLocationAndToLocationAndChargetype(chargeVo.getFromLocation(), chargeVo.getToLocation(), ConfigProperties.LOADING_CHARGES.get());
				input.setValue(chargeVo.getLoading());
				inputList.add(input);
			}
			
			for(Charge data:inputList)
			{
				setAllLocationListInModel(model);
				if(data.getFromLocation().equals(data.getToLocation())) {
					model.addAttribute("errormsg", "From location and To location should not be the same!");
					model.addAttribute("charge", chargeVo);
					return "addCharge";
				}
			}
			
			chargeRepository.saveAll(inputList);
			
			
			List<Charge> locaIterable = chargeRepository.getAllChargeData();
			
			List<ChargeVo> listChargeVos = getListOfChargeData(locaIterable);

			model.addAttribute("chargeListing", listChargeVos);
			

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update charge type! ");
			return "addDriver";
		}
		return "chargeListing";
	}
	@RequestMapping("/addchargepage")
	public String addChargePage(HttpServletRequest request, ModelMap model, ChargeVo chargeVo) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addCharge";
	}
	private void setAllLocationListInModel(ModelMap model) {
		Iterable<Location> locaIterable = locationRepository.findAll();
		model.addAttribute("locationList", locaIterable);
	}
}
