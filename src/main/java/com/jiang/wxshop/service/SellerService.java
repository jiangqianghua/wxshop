package com.jiang.wxshop.service;

import com.jiang.wxshop.dataobject.SellerInfo;

public interface SellerService {

	/**
	 * 根据openid查找卖家信息
	 * @param openid
	 * @return
	 */
	SellerInfo findSellerInfoByOpenid(String openid);
	
}
