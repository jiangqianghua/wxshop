package com.jiang.wxshop.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jiang.wxshop.converter.OrderMaster2OrderDTOConverter;
import com.jiang.wxshop.dataobject.OrderDetail;
import com.jiang.wxshop.dataobject.OrderMaster;
import com.jiang.wxshop.dataobject.ProductInfo;
import com.jiang.wxshop.dto.CartDTO;
import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.OrderStatusEnum;
import com.jiang.wxshop.enums.PlayStatusEnum;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.repository.OrderDetailRepository;
import com.jiang.wxshop.repository.OrderMasterRepository;
import com.jiang.wxshop.service.OrderService;
import com.jiang.wxshop.service.ProductService;
import com.jiang.wxshop.utils.KeyUtils;
@Service
@Transactional  // 事务
public class OrderServiceImpl implements OrderService{

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private ProductService productService ;
	@Autowired
	private OrderDetailRepository orderDetailRepository ;
	
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	@Override
	
	public OrderDTO create(OrderDTO orderDTO) {
		String orderId = KeyUtils.genUniqueKey();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		//1 查询商品
		for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
			ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
			}
			//2 计算总价
			orderAmount = productInfo.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);
			//3 写入订单详情数据库(ordermaster,orderdetail)
			orderDetail.setDetailId(KeyUtils.genUniqueKey());
			orderDetail.setOrderId(orderId);
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);
			
			CartDTO cartDTO = new CartDTO();
			cartDTO.setProductId(orderDetail.getProductId());
			cartDTO.setProductQuantity(orderDetail.getProductQuantity());
			cartDTOList.add(cartDTO);
		}
		
		//4 存入主数据库
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPlayStatus(PlayStatusEnum.WAIT.getCode());
		orderMasterRepository.save(orderMaster);
		
		//5 扣库存
		productService.descreaseStock(cartDTOList);
		
		return orderDTO;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		
		OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
		if(orderMaster == null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIT);
		}
		
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
		if(CollectionUtils.isEmpty(orderDetails)){
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetails);
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		
		Page<OrderMaster> oPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orList = OrderMaster2OrderDTOConverter.convert(oPage.getContent());
		Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orList,pageable,oPage.getTotalElements());
		return orderDTOPage ;
	}

	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {
		String errorModule = "［取消订单］";
		OrderMaster orderMaster = new OrderMaster();
		//查询订单是否完结
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("{} 订单状态不正确,orderId={},orderStatus={}",errorModule,orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster 	updateResult = orderMasterRepository.save(orderMaster);
		if(updateResult == null){
			logger.error("{} 订单更新失败 orderId={}",errorModule,orderDTO.getOrderId());
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		//返回库存
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			logger.error("{} 订单中无商品,orderId={}",errorModule,orderDTO.getOrderId());
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
				.collect(Collectors.toList());
		productService.increaseStock(cartDTOList);
		//是已经支付，需要退款
		if(orderDTO.getPlayStatus().equals(PlayStatusEnum.SUCCESS.getCode())){
			//TODO
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		String module = "［完结订单］";
		// 判断状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("{} 订单状态不正确,orderId={} , orderStatus={}",module,orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// 修改状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster 	updateResult = orderMasterRepository.save(orderMaster);
		if(updateResult == null){
			logger.error("{} 订单更新失败 orderId={}",module,orderDTO.getOrderId());
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		String module = "［支付订单］";
		// 判断状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.error("{} 订单状态不正确,orderId={} , orderStatus={}",module,orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//判断支付状态
		if(!orderDTO.getPlayStatus().equals(PlayStatusEnum.WAIT.getCode())){
			logger.error("{} 支付状态不正确,orderId={} , playStatus={}",module,orderDTO.getOrderId(),orderDTO.getPlayStatus());
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		//修改支付状态
		orderDTO.setPlayStatus(PlayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster 	updateResult = orderMasterRepository.save(orderMaster);
		if(updateResult == null){
			logger.error("{} 订单更新失败 orderId={}",module,orderDTO.getOrderId());
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return null;
	}

}
