package com.jiang.wxshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiang.wxshop.dataobject.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{
	
	List<OrderDetail> findByOrderId(String orderId);
	
	
}
