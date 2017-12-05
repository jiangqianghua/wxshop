package com.jiang.wxshop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

	@Autowired
	private ProductServiceImpl productService ;
	
	@Test
	public void  findOne(){
		ProductInfo productInfo = productService.findOne("1223344");
		Assert.assertEquals("1223344", productInfo.getProductId());
	}
	@Test
	public void  findUpAll(){
		List<ProductInfo> productInfos = productService.findUpAll();
		Assert.assertNotNull(productInfos);
	}
	@Test
	public void  findAll(){
		PageRequest request = new PageRequest(0, 2); // 第0页，分两条
		Page<ProductInfo> prPage = productService.findAll(request);	
		System.out.println(prPage.getNumberOfElements());
	}
	@Test
	public void save(){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("33344");
		productInfo.setProductName("香干肉丝");
		productInfo.setProductPrice(new BigDecimal(25));
		productInfo.setProductDescription("味道鲜美");
		productInfo.setCategoryType(3);
		productInfo.setProductStatus(0);
		productInfo.setProductStock(100);
		productInfo.setProductIcon("http://1111.jpg");
		ProductInfo result = productService.save(productInfo);
		Assert.assertNotNull(result);
	}
}
