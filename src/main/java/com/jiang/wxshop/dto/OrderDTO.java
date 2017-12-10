package com.jiang.wxshop.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.jiang.wxshop.dataobject.OrderDetail;
import com.jiang.wxshop.enums.OrderStatusEnum;
import com.jiang.wxshop.enums.PlayStatusEnum;

/**
 * 方便引用层传递
 * @author jiangqianghua
 *
 */
public class OrderDTO {

	private String orderId ; 
	private String buyerPhone ; 
	private String buyerName ; 
	private String buyerAddress ;
	private String buyerOpenid ;
	private BigDecimal orderAmount ;
	private Integer orderStatus;
	private Integer playStatus;
	private Date createTime ;
	private Date updateTime ;
	
	private List<OrderDetail> orderDetailList ;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getBuyerOpenid() {
		return buyerOpenid;
	}
	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getPlayStatus() {
		return playStatus;
	}
	public void setPlayStatus(Integer playStatus) {
		this.playStatus = playStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}
