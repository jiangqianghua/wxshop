package com.jiang.wxshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jiang.wxshop.dataobject.SellerInfo;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String>  {
	SellerInfo findByOpenid(String openid);
}
