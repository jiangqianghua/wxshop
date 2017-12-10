package com.jiang.wxshop.service;
/**
 * 订单运用层
 * @author jiangqianghua
 *
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jiang.wxshop.dto.OrderDTO;

public interface OrderService {
	/**
	 * 创建订单
	 * @param orderDTO
	 * @return
	 */
	OrderDTO create(OrderDTO orderDTO);
	/**
	 * 查找单个订单
	 * @param orderDTO
	 * @return
	 */
	OrderDTO findOne(String orderId);
	/**
	 * 分页查询
	 * @param buyerOpenid
	 * @param pageable
	 * @return
	 */
	Page<OrderDTO> findList(String buyerOpenid , Pageable pageable);
	
	OrderDTO cancel(OrderDTO orderDTO);
	/**
	 * 完结订单
	 * @param orderDTO
	 * @return
	 */
	OrderDTO finish(OrderDTO orderDTO);
	/**
	 * 支付订单
	 * @param orderDTO
	 * @return
	 */
	OrderDTO paid(OrderDTO orderDTO);
	
	/**
	 * 查询订单，分页显示 后台管理用
	 * @param buyerOpenid
	 * @param pageable
	 * @return
	 */
	Page<OrderDTO> findList(Pageable pageable);
}
