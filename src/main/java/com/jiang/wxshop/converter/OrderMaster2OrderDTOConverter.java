package com.jiang.wxshop.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.jiang.wxshop.dataobject.OrderMaster;
import com.jiang.wxshop.dto.OrderDTO;
/**
 * orderMaster 转 orderDTO
 * @author jiangqianghua
 *
 */
public class OrderMaster2OrderDTOConverter {

	
	public static OrderDTO convert(OrderMaster orderMaster){
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO ;
	}
	
	public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
		return orderMasters.stream().map(e -> 
		convert(e)).collect(Collectors.toList());
	}
}
