package com.jiang.wxshop.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.service.OrderService;
import com.jiang.wxshop.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {
	private static Logger logger = LoggerFactory.getLogger(SellerOrderController.class);
	@Autowired
	private OrderService orderService ;
	/**
	 * 订单查询 127.0.0.1:8085/sell/seller/product/list
	 * @param page  当前查询第几页  从1开始
	 * @param size  每一个显示多少条
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value="page",defaultValue="1") Integer page,
							@RequestParam(value="size",defaultValue="10") Integer size,
							Map<String, Object> map){
		PageRequest request = new PageRequest(page-1, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(request);
		map.put("orderDTOPage", orderDTOPage);
		map.put("currentPage",page);
		map.put("size", size);
		return new ModelAndView("order/list",map) ;
	}
	
	/**
	 * 取消订单 http://127.0.0.1:8085/sell/seller/order/cancel?orderId=1
	 * @param orderId
	 * @param map
	 * @return
	 */
	@GetMapping("/cancel")
	public ModelAndView cancel(@RequestParam("orderId") String orderId,
								Map<String,Object> map){
		OrderDTO orderDTO = null ;
		try
		{
			orderDTO = orderService.findOne(orderId);
			orderService.cancel(orderDTO);
		}
		catch(Exception e){
			logger.error("[卖家端取消订单] 发生异常{}",e.getMessage());
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new  ModelAndView("common/error",map);
		}
		map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success",map) ;
	}
	
	/**
	 * 订单详情
	 * @param orderId
	 * @param map
	 * @return
	 */
	@GetMapping("/detial")
	public ModelAndView detail(@RequestParam("orderId") String orderId,
								Map<String,Object> map){ 
		OrderDTO orderDTO = new OrderDTO() ;
		try
		{
			orderDTO = orderService.findOne(orderId);
		}
		catch(Exception e){
			logger.error("[卖家端查询订单详情] 发生异常{}",e.getMessage());
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new  ModelAndView("common/error",map);
		}
		map.put("orderDTO", orderDTO);
		return new ModelAndView("order/detail",map);
	}
	/**
	 * 完结订单
	 * @param orderId
	 * @param map
	 * @return
	 */
	@GetMapping("/finish")
	public ModelAndView finish(@RequestParam("orderId") String orderId,
								Map<String,Object> map){ 
		
		OrderDTO orderDTO = new OrderDTO() ;
		try
		{
			orderDTO = orderService.findOne(orderId);
			orderService.finish(orderDTO);
		}
		catch(Exception e){
			logger.error("[卖家端完结订单] 发生异常{}",e.getMessage());
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new  ModelAndView("common/error",map);
		}
		map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success",map) ;
	}
}
