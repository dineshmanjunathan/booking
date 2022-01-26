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
public class Expence implements Serializable{
	private static final long serialVersionUID = -8062647465959001236L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String expenceBranch;
	private String expenseCategory;
	private String expenseSubCategory;
	private String expenseNumber;
	private Long amount;
	private String createBy;
	private String paymentMode;
	private String description;
	private LocalDateTime expenseDate = LocalDateTime.now();
	//private LocalDateTime updatedon = LocalDateTime.now();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExpenceBranch() {
		return expenceBranch;
	}
	public void setExpenceBranch(String expenceBranch) {
		this.expenceBranch = expenceBranch;
	}
	public String getExpenseCategory() {
		return expenseCategory;
	}
	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	public String getExpenseSubCategory() {
		return expenseSubCategory;
	}
	public void setExpenseSubCategory(String expenseSubCategory) {
		this.expenseSubCategory = expenseSubCategory;
	}
	public String getExpenseNumber() {
		return expenseNumber;
	}
	public void setExpenseNumber(String expenseNumber) {
		this.expenseNumber = expenseNumber;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDateTime expenseDate) {
		this.expenseDate = expenseDate;
	}
}
