package com.ba.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ba.app.entity.CountryCode;
import com.ba.app.entity.Location;
import com.ba.app.entity.User;
import com.ba.app.model.CountryCodeRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.model.UserRepository;
import com.ba.app.vo.CountryCodeVo;
import com.ba.app.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryCodeRepository countryCodeRepository;
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
	
	@RequestMapping("/")
	public String landingPage(HttpServletRequest request, ModelMap model) {
		return "login";
	}

	@RequestMapping("/login")
	public String inlogin(HttpServletRequest request, ModelMap model) {
		return "login";
	}

	@RequestMapping("/menu")
	public String menu(HttpServletRequest request, ModelMap model) {
		//SESSION VALIDATION
		if(sessionValidation(request, model)!=null) return "login";
		return "menu";
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, ModelMap model) {
		UserVo ab = (UserVo) request.getSession().getAttribute("USER");
		model.addAttribute("CURRENT_USER", ab);
		return "home";
	}

	@RequestMapping("/register")
	public String user(HttpServletRequest request, ModelMap model) {
		/*
		 * Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
		 * model.addAttribute("countryCodeList", countryCodeList);
		 */
		return "user";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, ModelMap model) {
		if (request.getSession() != null) {
			request.getSession().invalidate();
			request.getSession().removeAttribute("USER_ID");
			model.addAttribute("logoutmsg", "Successfully logged out");
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, UserVo uservo, ModelMap model) {
		try {
			User user = userRepository.findByUserIdIgnoreCaseAndPassword(uservo.getUserId(), uservo.getPassword());
			if (user != null && user.getUserId() != null) {
				if (!uservo.getPassword().equals(user.getPassword())) {
					model.addAttribute("errormsg", "Password is incorrect!");
					return "login";
				}

				request.getSession().setAttribute("USER_ID", user.getId());
				request.getSession().setAttribute("USER_NAME", user.getName());
				request.getSession().setAttribute("ROLE", user.getRole());
				if (user.getLocation() != null && user.getLocation().getLocation() != null) {
					request.getSession().setAttribute("USER_LOCATION", user.getLocation().getLocation());
					request.getSession().setAttribute("USER_LOCATIONID", user.getLocation().getId());
				}
				return "menu";
			} else {
				model.addAttribute("errormsg", "User Id or Password is incorrect!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "User does not exists!");
		}
		return "login";
	}

	@RequestMapping(value = "/userlisting", method = RequestMethod.GET)
	public String adminListingSubmit(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Iterable<User> userList = userRepository.findAllByOrderByIdAsc();
			model.addAttribute("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userListing";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(HttpServletRequest request, UserVo user, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			User existUser = null;
			if (user != null && user.getId() != null) {
				existUser = userRepository.findById(user.getId()).get();
			}
			if (existUser != null && existUser.getId() != null) {

			} else {
				User alreadyExist = userRepository.findByUserIdIgnoreCase(user.getUserId());
				if (alreadyExist != null && alreadyExist.getUserId() != null) {
					model.addAttribute("errormsg", "User ID already exist! ");
					Iterable<User> userList = userRepository.findAllByOrderByIdAsc();
					model.addAttribute("userList", userList);
					return "userListing";
				}
			}
			String role = (String) request.getSession().getAttribute("ROLE");
			User userEntity = new User();
			model.addAttribute("user", user);

			BeanUtils.copyProperties(user, userEntity, "createon", "updatedon");
			userRepository.save(userEntity);
			
			if (userEntity.getLocation() != null && userEntity.getLocation().getLocation() != null) {
				request.getSession().setAttribute("USER_LOCATION", userEntity.getLocation().getLocation());
				request.getSession().setAttribute("USER_LOCATIONID", userEntity.getLocation().getId());
			}

			// if (role != null && role.equals("ADMIN")) {
			if (role != null) {
				if (existUser != null && existUser.getId() != null) {
					model.addAttribute("successMessage", "Updated successfully.");
				} else {
					model.addAttribute("successMessage", "User created successfully.");
				}
				Iterable<User> userList = userRepository.findAllByOrderByIdAsc();
				model.addAttribute("userList", userList);
				return "userListing";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "User creation failed! ");
			return "user";
		}
		return "login";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String useradd(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "useredit";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String useredit(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			User user = userRepository.findById(Long.parseLong(id)).get();
			model.addAttribute("user", user);
			setAllLocationListInModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "useredit";
	}

	@RequestMapping("/user/delete")
	public String userDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			String userId = request.getSession().getAttribute("USER_ID").toString();

			if (id != null && id.equals(userId)) {
				model.addAttribute("errormsg", "Same user ID cannot be deleted!");
				Iterable<User> userList = userRepository.findAllByOrderByIdAsc();
				model.addAttribute("userList", userList);
			} else {

				userRepository.deleteById(Long.parseLong(id));

				model.addAttribute("successMessage", "User deleted successfully.");
				Iterable<User> userList = userRepository.findAllByOrderByIdAsc();
				model.addAttribute("userList", userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userListing";
	}

	@RequestMapping("/countryCodeListing")
	public String countryCodeListing(HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}

	@RequestMapping(value = "/countryCode/delete", method = RequestMethod.GET)
	public String countryCodeDelete(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			countryCodeRepository.deleteById(Long.parseLong(id));
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}

	@RequestMapping(value = "/countryCode/edit", method = RequestMethod.GET)
	public String countryCodeEdit(@RequestParam("id") String id, HttpServletRequest request, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			CountryCode countryCode = countryCodeRepository.findById(Long.parseLong(id)).get();
			CountryCodeVo countryCodeVo = new CountryCodeVo();
			BeanUtils.copyProperties(countryCodeVo, countryCode);
			model.addAttribute("countryCode", countryCodeVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCode";
	}

	@RequestMapping(value = "/countryCode/edit", method = RequestMethod.POST)
	public String countryCodeEditSubmit(HttpServletRequest request, CountryCodeVo countryCodeVo, ModelMap model) {
		CountryCode countryCode = new CountryCode();
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			BeanUtils.copyProperties(countryCode, countryCodeVo);
			countryCodeRepository.save(countryCode);
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList);
			model.addAttribute("successMessage", "Successfully Edited Admin Record");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}

	@RequestMapping(value = "/countryCode/save", method = RequestMethod.POST)
	public String countryCodeSubmit(HttpServletRequest request, CountryCodeVo countryCodeVo, ModelMap model) {
		try {
			//SESSION VALIDATION
			if(sessionValidation(request, model)!=null) return "login";
			CountryCode countryCode = new CountryCode();
			BeanUtils.copyProperties(countryCode, countryCodeVo);
			countryCodeRepository.save(countryCode);
			Iterable<CountryCode> countryCodeList = countryCodeRepository.findAll();
			model.addAttribute("countryCodeList", countryCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "countryCodeListing";
	}

	@RequestMapping("/contactus")
	public String contactus(HttpServletRequest request, ModelMap model) {
		//SESSION VALIDATION
		if(sessionValidation(request, model)!=null) return "login";
		return "contactus";
	}

	private void setAllLocationListInModel(ModelMap model) {
		Iterable<Location> locaIterable = locationRepository.findAll();
		model.addAttribute("locationList", locaIterable);
	}

	/*
	 * @RequestMapping("/get/member") public ResponseEntity<String>
	 * findMember(@RequestParam("memberId") String memberId, HttpServletRequest
	 * request, ModelMap model) { User member =
	 * userRepository.findById(memberId).get(); return new
	 * ResponseEntity<String>(member.getName(), HttpStatus.OK); }
	 */
}
