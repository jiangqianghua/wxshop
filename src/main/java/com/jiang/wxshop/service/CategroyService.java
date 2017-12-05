package com.jiang.wxshop.service;

import java.util.List;

import com.jiang.wxshop.dataobject.ProductCategory;

public interface CategroyService {

	ProductCategory findOne(Integer categroyId);
	
	List<ProductCategory> findAll();
	
	List<ProductCategory> findByCategroyTypeIn(List<Integer> categoryTypeList);
	
	ProductCategory save(ProductCategory productCategory);
	
}
