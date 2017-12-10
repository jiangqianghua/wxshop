package com.jiang.wxshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.service.OrderService;

@RestController
@RequestMapping("/seller/product")
public class SellerOrderController {

	@Autowired
	private OrderService orderService ;
	/**
	 * 订单查询
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
		return new ModelAndView("order/list",map) ;
	}
}
