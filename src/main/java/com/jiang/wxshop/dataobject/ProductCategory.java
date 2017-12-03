package com.jiang.wxshop.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 类目
 * @author jiangqianghua
 */
@Entity
public class ProductCategory {
	/**
	 * 类目id
	 */
	@Id    			 // 主键
	@GeneratedValue  // 自增的类型
	private Integer categoryId ;
	/**
	 * 类目名称
	 */
	private String categoryName ; 
	/**
	 * 类目类型
	 */
	private String categoryType ;
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
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	} 
	
	
}
