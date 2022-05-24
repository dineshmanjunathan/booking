package com.ba.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_expence")
public class Expense implements Serializable{
	private static final long serialVersionUID = -8062647465959001236L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String expenseBranch;
	private String expenseCategory;
	private String expenseSubCategory;
	private String expenseNumber;
	private Long amount;
	private String createBy;
	private String paymentMode;
	private String description;
	private LocalDateTime expenseDate;
	

	
}
