package com.jiang.wxshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.OrderDetail;
import com.jiang.wxshop.dto.OrderDTO;
import com.jiang.wxshop.enums.OrderStatusEnum;
import com.jiang.wxshop.enums.PlayStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

	private final String openid = "1590700";
	private final String orderid = "1512830019206951796";
	@Autowired
	private OrderServiceImpl orderServiceImpl ;
	
	@Test
	public void createTest(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("江强华11");
		orderDTO.setBuyerAddress("新力帝泊湾");
		orderDTO.setBuyerPhone("15801523721");
		orderDTO.setBuyerOpenid(openid);
		
		// 购物车
		List<OrderDetail> orDetails = new ArrayList<OrderDetail>();
		OrderDetail o1 = new OrderDetail();
		o1.setProductId("33344");
		o1.setProductQuantity(10);
		orDetails.add(o1);
		OrderDetail o2 = new OrderDetail();
		o2.setProductId("1223344");
		o2.setProductQuantity(1);
		orDetails.add(o2);
		orderDTO.setOrderDetailList(orDetails);
		
		OrderDTO result = orderServiceImpl.create(orderDTO);
		Assert.assertNotNull(result);
	}
	@Test
	public void findOneTest(){
		OrderDTO result = orderServiceImpl.findOne("1512830019206951796");
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findListTest(){
		PageRequest request = new PageRequest(0, 2);
		Page<OrderDTO> orPage = orderServiceImpl.findList(openid, request);
		Assert.assertNotNull(orPage);
	}
	
	@Test
	public void cancelTest(){
		OrderDTO orderDTO = orderServiceImpl.findOne("1512880629214485742");
		OrderDTO result = orderServiceImpl.cancel(orderDTO);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());
	}
	
	@Test
	public void finishTest(){
		OrderDTO orderDTO = orderServiceImpl.findOne("1512880629214485742");
		OrderDTO result = orderServiceImpl.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderDTO.getOrderStatus());
	}
	
	@Test
	public void paidTest(){
		OrderDTO orderDTO = orderServiceImpl.findOne("1512880629214485742");
		OrderDTO result = orderServiceImpl.paid(orderDTO);
		Assert.assertEquals(PlayStatusEnum.SUCCESS.getCode(), orderDTO.getPlayStatus());
	}
	
}
