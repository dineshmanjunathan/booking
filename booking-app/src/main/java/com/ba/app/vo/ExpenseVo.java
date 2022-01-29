package com.ba.app.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseVo implements Serializable{
	private static final long serialVersionUID = -7637438372792211143L;
	
	private Long id;
	private String expenseBranch;
	
	private Long expenseCategoryId;
	private String expenseCategory;
	
	private Long expenseSubCategoryId;
	private String expenseSubCategory;
	
	private String expenseNumber;
	private Long amount;
	private String createBy;
	private String paymentMode;
	private String description;
	
	private String expenseDate;
		
}
