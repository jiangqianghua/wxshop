package com.jiang.wxshop.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiang.wxshop.dataobject.SellerInfo;
import com.jiang.wxshop.utils.KeyUtils;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

	@Autowired
	SellerInfoRepository sellerInfoRepository;
	private String openid="150700";
	@Test
	public void save()
	{
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtils.genUniqueKey());
		sellerInfo.setOpenid(openid);
		sellerInfo.setUsername("jiangqianghua");
		sellerInfo.setPassword("150700jiang");
		SellerInfo reInfo = sellerInfoRepository.save(sellerInfo);
		Assert.assertNotNull(reInfo);
	}
	
	@Test
	public void findByOpenidTest(){
		SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
		Assert.assertEquals(sellerInfo.getOpenid(), openid);
	}
}

