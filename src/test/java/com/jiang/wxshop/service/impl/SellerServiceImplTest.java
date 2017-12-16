package com.jiang.wxshop.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.SellerInfo;
import com.jiang.wxshop.service.SellerService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
	@Autowired
	private SellerServiceImpl sellerService ;
	
	private String openid="150700";
	@Test
	public void findSellerInfoByOpenidTest(){
		SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
		Assert.assertEquals(sellerInfo.getOpenid(), openid);
	}
}
