package com.jiang.wxshop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repository ;
	
	@Test
	public void saveTest(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("002");
		orderDetail.setOrderId("1");
		orderDetail.setProductIcon("http://XXX.png");
		orderDetail.setProductId("33344");
		orderDetail.setProductName("香干肉");
		orderDetail.setProductPrice(new BigDecimal(25.0));
		orderDetail.setProductQuantity(1);
		OrderDetail result = repository.save(orderDetail);
		Assert.assertNotNull(result);
	}
	@Test
	public void findByDetailId(){
		List<OrderDetail> results = repository.findByOrderId("1512830019206951796");
		//Assert.assertNotEquals(1, results.size());
		System.out.println(results.size());
	}
}
