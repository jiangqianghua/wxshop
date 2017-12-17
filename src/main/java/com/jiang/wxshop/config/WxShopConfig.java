package com.jiang.wxshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 项目参数配置，参数值配置在yml里面
 * @author jiangqianghua
 *
 */
@ConfigurationProperties(prefix = "wxshop")
@Component
public class WxShopConfig {

	/**
	 * 点餐系统的url
	 */
	public String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	
}
