package com.jiang.wxshop.service;

import com.jiang.wxshop.dto.OrderDTO;

public interface BuyerService {

	OrderDTO findOrderOne(String openId,String orderId);
	
	OrderDTO cancelOrder(String openId,String orderId);
}
