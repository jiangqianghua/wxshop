package com.jiang.wxshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiang.wxshop.dataobject.ProductInfo;
import com.jiang.wxshop.dto.CartDTO;
import com.jiang.wxshop.enums.ProductStatusEnum;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
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

	@Override
	@Transactional  // 支持事务回滚
	public void increaseStock(List<CartDTO> cartDTOList) {
		for(CartDTO cartDTO:cartDTOList){
			ProductInfo productInfo = respository.findOne(cartDTO.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			respository.save(productInfo);
		}
		
	}

	@Override
	@Transactional // 支持事务回滚
	public void descreaseStock(List<CartDTO> cartDTOList) {
		for(CartDTO cartDTO:cartDTOList){
			ProductInfo productInfo = respository.findOne(cartDTO.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
			}
			Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
			if(result < 0){
				throw new SellException(ResultEnum.PRODUCTION_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			respository.save(productInfo);
		}
		
	}

	
}
