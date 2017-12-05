package com.jiang.wxshop.dataobject;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
/**
 * 商品信息表
 * @author jiangqianghua
 *
 */
@Entity
@DynamicUpdate
public class ProductInfo {
	@Id
	private String productId;
	/**
	 * 名字
	 */
	private String productName; 
	/**
	 * 单价
	 */
	private BigDecimal productPrice;
	/**
	 * 库存
	 */
	private Integer productStock ; 
	/**
	 * 描述
	 */
	private String productDescription ;
	/**
	 * 商品状态 0 正常   1下架
	 */
	private int productStatus ;
	/**
	 * 小图
	 */
	private String productIcon ;
	/**
	 * 编号
	 */
	private Integer categoryType;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductIcon() {
		return productIcon;
	}
	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}
	public Integer getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	} 	
	
	
	
	

}
