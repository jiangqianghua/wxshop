package com.jiang.wxshop.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.OrderMaster;
import com.jiang.wxshop.dataobject.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repository ;
	
	@Test
	public void findOneTest(){
		OrderMaster orderMaster = repository.findOne("1512830019206951796");
		System.out.println(orderMaster.toString());
	}
	@Test
	public void saveTest(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("2");
		orderMaster.setBuyerName("胡丹");
		orderMaster.setBuyerPhone("15801523721");
		orderMaster.setBuyerAddress("六里桥");
		orderMaster.setBuyerOpenid("150700");
		orderMaster.setOrderAmount(new BigDecimal(12.5));
		
		OrderMaster result = repository.save(orderMaster);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByBuyerOpenid(){
		PageRequest request = new PageRequest(0,1); 
		Page<OrderMaster> result= repository.findByBuyerOpenid("150700", request);
		System.out.println(result.getTotalElements());
	}
}
