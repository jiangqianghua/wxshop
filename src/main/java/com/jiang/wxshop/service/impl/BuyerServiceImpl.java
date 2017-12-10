package com.jiang.wxshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.service.BuyerService;
import com.jiang.wxshop.service.OrderService;
@Service
@Transactional  // 事务
public class BuyerServiceImpl implements BuyerService{

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderService orderService ;
	@Override
	public OrderDTO findOrderOne(String openId, String orderId) {
		return checkOrderOwner(openId,orderId);
	}
	
	@Override
	public OrderDTO cancelOrder(String openId, String orderId) {
		OrderDTO orderDTO = checkOrderOwner(openId,orderId);
		if(orderDTO == null){
			logger.error("[取消订单] 查不到改订单 orderid={}",orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIT);
		}
		return orderService.cancel(orderDTO) ;
	}

	private OrderDTO	 checkOrderOwner(String openId, String orderId){
		OrderDTO orderDTO = orderService.findOne(orderId);
		if(orderDTO == null){
			return null ;
		}
		if(!orderDTO.getBuyerOpenid().equals(openId)){
			logger.error("[查询订单] 订单的openid不一致,openid={}",openId);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}

}
