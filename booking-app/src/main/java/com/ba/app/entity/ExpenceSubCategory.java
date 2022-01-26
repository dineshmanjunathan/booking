package com.ba.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_expenceSubCategory")
public class ExpenceSubCategory implements Serializable {

	private static final long serialVersionUID = -345566216496700053L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String subCategory;
	private String desciption;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
	@OneToOne()
	@JoinColumn(name = "expenceCategory")
	private ExpenceCategory expenceCategory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public LocalDateTime getCreateon() {
		return createon;
	}
	public void setCreateon(LocalDateTime createon) {
		this.createon = createon;
	}
	public LocalDateTime getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(LocalDateTime updatedon) {
		this.updatedon = updatedon;
	}
	public ExpenceCategory getExpenceCategory() {
		return expenceCategory;
	}
	public void setExpenceCategory(ExpenceCategory expenceCategory) {
		this.expenceCategory = expenceCategory;
	}	
}