package com.jiang.wxshop.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServieImplTest {
	@Autowired
	private CategoryServieImpl categoryServie ;
	@Test
	public void findOne(){
		ProductCategory productCategory = categoryServie.findOne(1);
		Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
	}
	@Test
	public void findAll(){
		List<ProductCategory> productCategories = categoryServie.findAll();
		Assert.assertNotEquals(0, productCategories.size());
	}
	@Test
	public void findByCategroyTypeIn(){
		List<ProductCategory> productCategories = categoryServie.findByCategroyTypeIn(Arrays.asList(1,2,3,4));
		Assert.assertNotEquals(0, productCategories.size());
	}
	@Test
	public void save(){
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setCategoryName("妹子哦");
		productCategory2.setCategoryType(4);
		ProductCategory result = categoryServie.save(productCategory2);
		Assert.assertNotNull(result);
	}
}
