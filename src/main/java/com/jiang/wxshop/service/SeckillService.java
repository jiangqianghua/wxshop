package com.jiang.wxshop.service;

public interface SeckillService {

	/**
	 * 查询要秒杀的商品
	 * @param productId
	 * @return
	 */
	public String querySeckillProductInfo(String productId);
	
	/**
	 * 秒杀开始
	 * @param productId
	 * @return
	 */
	public void orderProductMockDiffUser(String productId);
}
