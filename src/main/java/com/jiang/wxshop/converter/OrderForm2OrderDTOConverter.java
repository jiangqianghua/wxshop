package com.jiang.wxshop.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.jiang.wxshop.dataobject.OrderDetail;
import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.form.OrderForm;
import com.google.gson.reflect.*;

public class OrderForm2OrderDTOConverter {
	private static Logger logger = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);
	public static OrderDTO convert(OrderForm orderForm){
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		try{
			orderDetails = gson.fromJson(orderForm.getItems(),
				new TypeToken<List<OrderDetail>>(){}.getType());
		}catch(Exception e){
			logger.error("[对象转换] 错误 , message={}",orderForm.getItems());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		orderDTO.setOrderDetailList(orderDetails);
		return orderDTO;
	}
}
