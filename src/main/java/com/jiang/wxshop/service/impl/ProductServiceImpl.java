package com.jiang.wxshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jiang.wxshop.dataobject.ProductInfo;
import com.jiang.wxshop.enums.ProductStatusEnum;
import com.jiang.wxshop.repository.ProductInfoRepository;
import com.jiang.wxshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoRepository respository ;
	@Override
	public ProductInfo findOne(String productId) {
		// TODO Auto-generated method stub
		return respository.findOne(productId);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		// TODO Auto-generated method stub
		return respository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return respository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return respository.save(productInfo);
	}

	
}
