package com.ba.app.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.ba.app.entity.ExpenceCategory;

public class ExpenceSubCategoryVo implements Serializable {

	private static final long serialVersionUID = 8728101942361418427L;
	
	private Long id;
	private String subCategory;
	private String desciption;
	private LocalDateTime createon = LocalDateTime.now();
	private LocalDateTime updatedon = LocalDateTime.now();
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
