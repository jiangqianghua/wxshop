package com.jiang.wxshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jiang.wxshop.dataobject.OrderMaster;
import com.jiang.wxshop.dto.OrderDTO;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable) ;
//	OrderMaster findOneById(String orderId);
}
