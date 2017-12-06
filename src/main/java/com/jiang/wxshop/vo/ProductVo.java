package com.jiang.wxshop.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVo {

	@JsonProperty("name") // 返回给json的名字
	private String categroyName ;
	
	@JsonProperty("type")
	private Integer categoryType ;
	
	@JsonProperty("foods")
	private List<ProductInfoVo> productInfoVOList ;

	public String getCategroyName() {
		return categroyName;
	}

	public void setCategroyName(String categroyName) {
		this.categroyName = categroyName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public List<ProductInfoVo> getProductInfoVOList() {
		return productInfoVOList;
	}

	public void setProductInfoVOList(List<ProductInfoVo> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
	
	
	
	
}
