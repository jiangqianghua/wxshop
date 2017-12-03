package com.jiang.wxshop.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.ProductCategory;;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

	@Autowired
	private ProductCategoryRepository repository ;
	
	@Test
	public void findOneTest(){
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
}
