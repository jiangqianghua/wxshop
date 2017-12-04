package com.jiang.wxshop.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 类目
 * @author jiangqianghua
 */
@Entity
@DynamicUpdate // 动态更新时间,非常重要
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "categoryId="+categoryId + " categoryName="+categoryName + " categoryType="+categoryType;
	}
	
}
