package com.jiang.wxshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiang.wxshop.converter.OrderForm2OrderDTOConverter;
import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.form.OrderForm;
import com.jiang.wxshop.service.BuyerService;
import com.jiang.wxshop.service.OrderService;
import com.jiang.wxshop.service.impl.OrderServiceImpl;
import com.jiang.wxshop.utils.ResultVOUtil;
import com.jiang.wxshop.vo.ResultVo;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderService orderService ;
	
	@Autowired
	private BuyerService buyerService ;
	
	// 创建订单
	@PostMapping("/create")
	public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm
			,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			logger.error("[创建订单]参数不正确 orderForm={}",orderForm.toString());
			throw new SellException(ResultEnum.PARAM_ERROR
					,bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			logger.error("[创建订单] 购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO createResult = orderService.create(orderDTO);
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", createResult.getOrderId());
		return ResultVOUtil.success(map) ;
	}
	
	// 分页查询订单列表
	@GetMapping("/list")
	public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
										@RequestParam(value="page",defaultValue="0") Integer page,
										@RequestParam(value="size",defaultValue="10") Integer size){
		if(StringUtils.isEmpty(openid)){
			logger.equals("[查询订单列表] openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = new PageRequest(page,size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
		return ResultVOUtil.success(orderDTOPage.getContent()) ;
	}
	// 订单详情
	@GetMapping("/detail")
	public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
									@RequestParam("orderId") String orderId){
	
		//TODO 不安全做法
		OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
		return ResultVOUtil.success(orderDTO) ;
	}
	// 取消订单
	@PostMapping("/cancel")
	public ResultVo<OrderDTO> cancel(@RequestParam("openid") String openid,
									@RequestParam("orderId") String orderId){
	
		//TODO 不安全做法
		buyerService.cancelOrder(openid, orderId);
		return ResultVOUtil.success() ;
	}
	
}
