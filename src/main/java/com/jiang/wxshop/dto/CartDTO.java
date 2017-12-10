package com.jiang.wxshop.dto;
/**
 * 购物车
 * @author jiangqianghua
 *
 */
public class CartDTO {
	
	private String productId ; 
	
	private Integer productQuantity ;

	
	public CartDTO() {
		super();
	}

	public CartDTO(String productId, Integer productQuantity) {
		super();
		this.productId = productId;
		this.productQuantity = productQuantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
}
