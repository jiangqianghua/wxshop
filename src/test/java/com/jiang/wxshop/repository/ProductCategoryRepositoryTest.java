package com.jiang.wxshop.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jiang.wxshop.dataobject.ProductCategory;;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

	@Autowired
	private ProductCategoryRepository repository ;
	
	//insert into product_category(category_name,category_type) values("热销榜",2);
	@Test
	public void findOneTest(){
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
	
	@Test
	public void saveTest(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("男人最爱榜单");
		productCategory.setCategoryType(3);
		repository.save(productCategory);
		
	}
	
	
	@Test
	@Transactional // 设置事务完全回滚，测试后表不回数据自动清除
	public void updateTest(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryId(2);
		productCategory.setCategoryName("人妖最爱榜单");
		productCategory.setCategoryType(4);
		ProductCategory result = repository.save(productCategory);
		Assert.assertNotNull(result);
		
	}
	
	@Test
	public void findByCategoryTypeInTest()
	{
		List<Integer> list = Arrays.asList(2,3,4);
		List<ProductCategory> result = repository.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0, result);
	}
}
