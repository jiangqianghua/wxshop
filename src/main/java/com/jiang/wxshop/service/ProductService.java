package com.jiang.wxshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jiang.wxshop.dataobject.ProductInfo;

public interface ProductService {

	ProductInfo findOne(String productId);
	
	List<ProductInfo	> findUpAll();
	/**
	 * 分页查找
	 * @param pageable
	 * @return
	 */
	Page<ProductInfo> findAll(Pageable pageable);
	
	ProductInfo save(ProductInfo productInfo);
	
	// 加库存
	
	// 减库存
}
