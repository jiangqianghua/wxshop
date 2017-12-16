package com.jiang.wxshop.form;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CategoryForm {
	/**
	 * 类目id
	 */
	private Integer categoryId ;
	/**
	 * 类目名称
	 */
	private String categoryName ; 
	/**
	 * 类目类型
	 */
	private Integer categoryType ;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}
	
	
}
