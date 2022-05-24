package com.ba.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ba.app.entity.Expense;
import com.ba.app.entity.ExpenceCategory;
import com.ba.app.entity.ExpenceSubCategory;
import com.ba.app.entity.Location;
import com.ba.app.model.ExpenceCategoryRepository;
import com.ba.app.model.ExpenceRepository;
import com.ba.app.model.ExpenceSubCategoryRepository;
import com.ba.app.model.LocationRepository;
import com.ba.app.vo.ExpenceCategoryVo;
import com.ba.app.vo.ExpenceSubCategoryVo;
import com.ba.app.vo.ExpenseVo;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ExpenseController {

	private ExpenceRepository expenceRepository;
	private ExpenceCategoryRepository expenceCategoryRepository;
	private ExpenceSubCategoryRepository expenceSubCategoryRepository;
	private LocationRepository locationRepository;

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

	@RequestMapping("/expence")
	public String expence(HttpServletRequest request, ModelMap model) {
		if (sessionValidation(request, model) != null)
			return "login";
		Iterable<Expense> expences = expenceRepository.findAll();
		Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
		Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();

		Map<Long, ExpenceCategory> expenseCategories = new HashMap<Long, ExpenceCategory>();
		Map<Long, ExpenceSubCategory> expenseSubCategories = new HashMap<Long, ExpenceSubCategory>();
		for (ExpenceCategory expenceCategory : expenceCategoryIterable) {
			expenseCategories.put(expenceCategory.getId(), expenceCategory);
		}
		for (ExpenceSubCategory expenceSubCategory : expenceSubCategoryIterable) {
			expenseSubCategories.put(expenceSubCategory.getId(), expenceSubCategory);
		}

		List<ExpenseVo> expenceVos = new ArrayList<ExpenseVo>();
		for (Expense expence : expences) {
			ExpenseVo expenceVo = new ExpenseVo();
			expenceVo.setId(expence.getId());

			expenceVo.setExpenseNumber(expence.getExpenseNumber());
			expenceVo.setExpenseBranch(expence.getExpenseBranch());
			expenceVo.setDescription(expence.getDescription());
			expenceVo.setPaymentMode(expence.getPaymentMode());
			expenceVo.setAmount(expence.getAmount());
			if (expence.getExpenseDate() != null) {
				expenceVo.setExpenseDate(
						expence.getExpenseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd ")));
			}
			expenceVo.setCreateBy(expence.getCreateBy());

			expenceVo.setExpenseCategoryId(Long.parseLong(expence.getExpenseCategory()));
			expenceVo.setExpenseCategory(expenseCategories.get(expenceVo.getExpenseCategoryId()).getCategory());
			expenceVo.setExpenseSubCategoryId(Long.parseLong(expence.getExpenseSubCategory()));
			expenceVo.setExpenseSubCategory(
					expenseSubCategories.get(expenceVo.getExpenseSubCategoryId()).getSubCategory());

			expenceVos.add(expenceVo);
		}

		model.addAttribute("expences", expenceVos);

		return "expence";
	}

	@RequestMapping("/expence/add")
	public String expenceAdd(HttpServletRequest request, ModelMap model) {
		if (sessionValidation(request, model) != null)
			return "login";
		Long nextExpenceNumber = expenceRepository.getcurrentExpenceNumber();
		String fromlocationcode = "" + request.getSession().getAttribute("USER_LOCATIONID");
		String expenceNo = "";
		if (fromlocationcode != null && !fromlocationcode.isEmpty() && !fromlocationcode.equalsIgnoreCase("NULL")) {
			expenceNo = request.getSession().getAttribute("USER_ID") + "/" + fromlocationcode + "/"
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")) + "/" + nextExpenceNumber;
		} else {
			expenceNo = LocalDate.now() + "/" + nextExpenceNumber;
		}
		model.addAttribute("expenseNo", expenceNo);

		Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
		model.addAttribute("expenseCategoryListing", expenceCategoryIterable);

		Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();
		model.addAttribute("expenseSubCategoryListing", expenceSubCategoryIterable);

		Iterable<Location> locaIterable = locationRepository.findAll();
		model.addAttribute("locationListing", locaIterable);

		ExpenseVo expenceVo = new ExpenseVo();
		expenceVo.setCreateBy((String) request.getSession().getAttribute("USER_NAME"));
		expenceVo.setExpenseDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd ")));
		model.addAttribute("expense", expenceVo);
		return "addExpence";
	}

	@RequestMapping(value = "/addExpence", method = RequestMethod.POST)
	public String saveExpence(HttpServletRequest request, ExpenseVo expenceVo, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			if (expenceVo != null) {
				try {
					Expense expence1 = expenceRepository.findById(expenceVo.getId()).get();
					if (expence1 != null && expence1.getId() != null) {
						model.addAttribute("errormsg", "Expence Code [" + expence1.getId() + "] is already exist! ");
						return "addExpence";
					}
				} catch (Exception e) {
				}
			}

			Expense expenceEntity = new Expense();

			BeanUtils.copyProperties(expenceVo, expenceEntity, "createon", "updatedon");
			expenceEntity.setExpenseDate(
					LocalDate.parse(expenceVo.getExpenseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd ")).atStartOfDay());
			expenceEntity = expenceRepository.save(expenceEntity);
			expenceRepository.getNextExpenceNumber();
			model.addAttribute("successMessage", expenceEntity.getExpenseNumber() + " - Expence added! ");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new expence! ");
			return "addExpence";
		}
		return "redirect:/expence";
	}

	@RequestMapping("/expence/edit/{id}")
	public String expenceEdit(HttpServletRequest request, @PathVariable Long id, ModelMap model) {
		if (sessionValidation(request, model) != null)
			return "login";

		Optional<Expense> expenseEntityOpt = expenceRepository.findById(id);
		ExpenseVo expenceVo = new ExpenseVo();
		if (expenseEntityOpt.isPresent()) {

			Expense expenseEntity = expenseEntityOpt.get();

			Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
			Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();

			Map<Long, ExpenceCategory> expenseCategories = new HashMap<Long, ExpenceCategory>();
			Map<Long, ExpenceSubCategory> expenseSubCategories = new HashMap<Long, ExpenceSubCategory>();
			for (ExpenceCategory expenceCategory : expenceCategoryIterable) {
				expenseCategories.put(expenceCategory.getId(), expenceCategory);
			}
			for (ExpenceSubCategory expenceSubCategory : expenceSubCategoryIterable) {
				expenseSubCategories.put(expenceSubCategory.getId(), expenceSubCategory);
			}
			model.addAttribute("expenseCategoryListing", expenceCategoryIterable);
			model.addAttribute("expenseSubCategoryListing", expenceSubCategoryIterable);

			Iterable<Location> locaIterable = locationRepository.findAll();
			model.addAttribute("locationListing", locaIterable);

			expenceVo.setId(expenseEntity.getId());

			expenceVo.setExpenseNumber(expenseEntity.getExpenseNumber());
			expenceVo.setExpenseBranch(expenseEntity.getExpenseBranch());
			expenceVo.setDescription(expenseEntity.getDescription());
			expenceVo.setPaymentMode(expenseEntity.getPaymentMode());
			expenceVo.setAmount(expenseEntity.getAmount());
			if (expenseEntity.getExpenseDate() != null) {
				expenceVo.setExpenseDate(
						expenseEntity.getExpenseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd ")));
			}
			expenceVo.setCreateBy(expenseEntity.getCreateBy());

			expenceVo.setExpenseCategoryId(Long.parseLong(expenseEntity.getExpenseCategory()));
			expenceVo.setExpenseCategory(expenseCategories.get(expenceVo.getExpenseCategoryId()).getCategory());
			expenceVo.setExpenseSubCategoryId(Long.parseLong(expenseEntity.getExpenseSubCategory()));
			expenceVo.setExpenseSubCategory(
					expenseSubCategories.get(expenceVo.getExpenseSubCategoryId()).getSubCategory());
			model.addAttribute("expenseNo", expenceVo.getExpenseNumber());
		} else {
			model.addAttribute("errormsg", "No record(s) found.");
		}
		model.addAttribute("expense", expenceVo);
		return "addExpence";
	}

	@RequestMapping(value = "/editExpence", method = RequestMethod.POST)
	public String editExpence(HttpServletRequest request, ExpenseVo expenceVo, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			try {
				Optional<Expense> expenceEntityOpt = expenceRepository.findById(expenceVo.getId());
				if (!expenceEntityOpt.isPresent()) {
					model.addAttribute("errormsg", "Expence does not exist! ");
					return "addExpence";
				} else {
					Expense expenceEntity = expenceEntityOpt.get();
					String role = (String) request.getSession().getAttribute("ROLE");
					if (StringUtils.equalsIgnoreCase(role, "ADMIN")) {
						expenceEntity.setExpenseCategory(expenceVo.getExpenseCategory());
						expenceEntity.setExpenseSubCategory(expenceVo.getExpenseSubCategory());
						expenceEntity.setAmount(expenceVo.getAmount());
						expenceEntity.setPaymentMode(expenceVo.getPaymentMode());
					}					
					expenceEntity.setDescription(expenceVo.getDescription());
					expenceEntity = expenceRepository.save(expenceEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errormsg", "Failed to update expence! ");
				return "addExpence";
			}
			model.addAttribute("successMessage", expenceVo.getExpenseNumber() + " - Expence updated! ");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update expence! ");
			return "addExpence";
		}
		return "redirect:/expence";
	}

	// Expense category

	@RequestMapping("/expenseCategoryListing")
	public String expenseCategoryListing(HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<ExpenceCategory> expenceCategory = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expenseCategoryListing";
	}

	@RequestMapping(value = "/addExpenseCategory", method = RequestMethod.POST)
	public String saveExpenseCategory(HttpServletRequest request, ExpenceCategoryVo expenceCategoryVo, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			if (expenceCategoryVo != null) {
				try {
					ExpenceCategory expenceCategory = expenceCategoryRepository.findById(expenceCategoryVo.getId())
							.get();
					if (expenceCategory != null && expenceCategory.getId() != null) {
						model.addAttribute("errormsg",
								"ExpenceCategory [" + expenceCategory.getId() + "] is already exist! ");
						return "addExpenseCategory";
					}
				} catch (Exception e) {
				}
			}

			ExpenceCategory expenceCategoryEntity = new ExpenceCategory();

			BeanUtils.copyProperties(expenceCategoryVo, expenceCategoryEntity, "createon", "updatedon");
			expenceCategoryEntity = expenceCategoryRepository.save(expenceCategoryEntity);
			model.addAttribute("successMessage", expenceCategoryEntity.getCategory() + " - category added! ");
			Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new category! ");
			return "addExpenseCategory";
		}
		return "expenseCategoryListing";
	}

	@RequestMapping(value = "/editExpenseCategory", method = RequestMethod.POST)
	public String updateExpenseCategory(HttpServletRequest request, ExpenceCategoryVo expenceCategoryVo,
			ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			ExpenceCategory expenceCategoryEntity = new ExpenceCategory();

			BeanUtils.copyProperties(expenceCategoryVo, expenceCategoryEntity, "createon", "updatedon");
			expenceCategoryEntity = expenceCategoryRepository.save(expenceCategoryEntity);
			model.addAttribute("successMessage", expenceCategoryEntity.getCategory() + " - category updated! ");
			Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update category! ");
			return "addExpenseCategory";
		}
		return "expenseCategoryListing";
	}

	@RequestMapping(value = "/expenseCategory/edit/{id}", method = RequestMethod.GET)
	public String expenseCategoryEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			ExpenceCategory expenceCategory = expenceCategoryRepository.findById(id).get();
			ExpenceCategoryVo expenceCategoryVo = new ExpenceCategoryVo();
			BeanUtils.copyProperties(expenceCategory, expenceCategoryVo);
			model.addAttribute("expenceCategory", expenceCategoryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addExpenseCategory";
	}

	@RequestMapping(value = "/expenseCategory/delete/{id}", method = RequestMethod.GET)
	public String expenseCategoryDelete(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			expenceCategoryRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expenseCategoryListing";
	}

	// Sub categories

	@RequestMapping("/expenseSubCategoryListing")
	public String expenseSubCategoryListing(HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<ExpenceSubCategory> expenceSubCategory = expenceSubCategoryRepository.findAll();
			model.addAttribute("expenceSubCategoryListing", expenceSubCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expenseSubCategoryListing";
	}

	@RequestMapping(value = "/addExpenseSubCategory", method = RequestMethod.POST)
	public String saveExpenseSubCategory(HttpServletRequest request, ExpenceSubCategoryVo expenceSubCategoryVo,
			ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			if (expenceSubCategoryVo != null) {
				try {
					ExpenceSubCategory expenceSubCategory = expenceSubCategoryRepository
							.findById(expenceSubCategoryVo.getId()).get();
					if (expenceSubCategory != null && expenceSubCategory.getId() != null) {
						model.addAttribute("errormsg",
								"ExpenceSubCategory [" + expenceSubCategory.getId() + "] is already exist! ");
						return "addExpenseSubCategory";
					}
				} catch (Exception e) {
				}
			}

			ExpenceSubCategory expenceSubCategoryEntity = new ExpenceSubCategory();

			BeanUtils.copyProperties(expenceSubCategoryVo, expenceSubCategoryEntity, "createon", "updatedon");
			expenceSubCategoryEntity = expenceSubCategoryRepository.save(expenceSubCategoryEntity);
			model.addAttribute("successMessage", expenceSubCategoryEntity.getSubCategory() + " - sub category added! ");
			Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();
			model.addAttribute("expenceSubCategoryListing", expenceSubCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to add new sub category! ");
			return "addExpenseSubCategory";
		}
		return "expenseSubCategoryListing";
	}

	@RequestMapping(value = "/editExpenseSubCategory", method = RequestMethod.POST)
	public String updateExpenseSubCategory(HttpServletRequest request, ExpenceSubCategoryVo expenceSubCategoryVo,
			ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			ExpenceSubCategory expenceSubCategoryEntity = new ExpenceSubCategory();

			BeanUtils.copyProperties(expenceSubCategoryVo, expenceSubCategoryEntity, "createon", "updatedon");
			expenceSubCategoryEntity = expenceSubCategoryRepository.save(expenceSubCategoryEntity);
			model.addAttribute("successMessage",
					expenceSubCategoryEntity.getSubCategory() + " - sub category updated! ");
			Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();
			model.addAttribute("expenceSubCategoryListing", expenceSubCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "Failed to update sub category! ");
			return "addExpenseSubCategory";
		}
		return "expenseSubCategoryListing";
	}

	@RequestMapping(value = "/expenseSubCategory/edit/{id}", method = RequestMethod.GET)
	public String expenseSubCategoryEdit(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			ExpenceSubCategory expenceSubCategory = expenceSubCategoryRepository.findById(id).get();
			ExpenceSubCategoryVo expenceSubCategoryVo = new ExpenceSubCategoryVo();
			BeanUtils.copyProperties(expenceSubCategory, expenceSubCategoryVo);
			model.addAttribute("expenceSubCategory", expenceSubCategoryVo);
			Iterable<ExpenceCategory> expenceCategoryIterable = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addExpenseSubCategory";
	}

	@RequestMapping(value = "/expenseSubCategory/delete/{id}", method = RequestMethod.GET)
	public String expenseSubCategoryDelete(@PathVariable("id") Long id, HttpServletRequest request, ModelMap model) {
		try {
			if (sessionValidation(request, model) != null)
				return "login";
			expenceSubCategoryRepository.deleteById(id);
			model.addAttribute("deletesuccessmessage", "Deleted Successfully");
			Iterable<ExpenceSubCategory> expenceSubCategoryIterable = expenceSubCategoryRepository.findAll();
			model.addAttribute("expenceSubCategoryListing", expenceSubCategoryIterable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expenseSubCategoryListing";
	}

	@RequestMapping(value = "/expenseSubCategory/add", method = RequestMethod.GET)
	public String expenseSubCategoryAdd(HttpServletRequest request, ModelMap model) {
		try {
			// SESSION VALIDATION
			if (sessionValidation(request, model) != null)
				return "login";
			Iterable<ExpenceCategory> expenceCategory = expenceCategoryRepository.findAll();
			model.addAttribute("expenceCategoryListing", expenceCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addExpenseSubCategory";
	}

}
