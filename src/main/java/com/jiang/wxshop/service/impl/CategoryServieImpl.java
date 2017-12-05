package com.jiang.wxshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.wxshop.dataobject.ProductCategory;
import com.jiang.wxshop.repository.ProductCategoryRepository;
import com.jiang.wxshop.service.CategroyService;
@Service
public class CategoryServieImpl implements CategroyService{
	@Autowired
	private ProductCategoryRepository repository ;
	@Override
	public ProductCategory findOne(Integer categroyId) {
		// TODO Auto-generated method stub
		return repository.findOne(categroyId);
	}

	@Override
	public List<ProductCategory> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategroyTypeIn(List<Integer> categoryTypeList) {
		// TODO Auto-generated method stub
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return repository.save(productCategory);
	}
	

}
