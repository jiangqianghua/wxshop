package com.jiang.wxshop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
	@Autowired
	private ProductInfoRepository repository ;
	
	@Test
	public void saveTest(){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("1223344");
		productInfo.setProductName("鱼香肉丝");
		productInfo.setProductPrice(new BigDecimal(25));
		productInfo.setProductDescription("非常好恰");
		productInfo.setCategoryType(2);
		productInfo.setProductStatus(0);
		productInfo.setProductStock(100);
		productInfo.setProductIcon("http://1111.jpg");
		ProductInfo result = repository.save(productInfo);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByProductStatus(){
		List<ProductInfo> productInfos = repository.findByProductStatus(0);
		Assert.assertNotNull(productInfos);
	}

}
