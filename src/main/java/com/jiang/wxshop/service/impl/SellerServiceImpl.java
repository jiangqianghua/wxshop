package com.jiang.wxshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.wxshop.dataobject.SellerInfo;
import com.jiang.wxshop.repository.SellerInfoRepository;
import com.jiang.wxshop.service.SellerService;
@Service
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerInfoRepository sellerInfoRepository ;
	@Override
	public SellerInfo findSellerInfoByOpenid(String openid) {
		return sellerInfoRepository.findByOpenid(openid);
	}

}
