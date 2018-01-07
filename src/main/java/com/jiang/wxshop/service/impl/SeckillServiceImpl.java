package com.jiang.wxshop.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.service.SeckillService;
import com.jiang.wxshop.utils.KeyUtils;
/**
 * 模拟秒杀
 * @author jiangqianghua
 *
 */
@Service
public class SeckillServiceImpl implements SeckillService{

	// 解锁超时时间 10s
	private static final int TIMEOUT = 10*1000;
	@Autowired
	private RedisLock redisLock ;
	
	static Map<String, Integer> products;
	static Map<String, Integer> stock;
	static Map<String, String> orders;
	
	static 
	{
		products = new HashMap<String, Integer>();
		stock = new HashMap<>();
		orders = new HashMap<>();
		products.put("123456", 10000);
		stock.put("123456", 10000);
	}
	
	private String queryMap(String productId){
		return "国庆活动，皮蛋粥特价，限量份"+
				products.get(productId)+
				"还剩:"+stock.get(productId)+" 份"+
				"该商品成功下单用户数目:"+
				orders.size()+"人";
	}
	
	
	@Override
	public String querySeckillProductInfo(String productId) {
		
		return this.queryMap(productId);
	}

	//synchronized 导致执行慢,无法细粒度控制，只能单机
	@Override
	public /**synchronized**/ void orderProductMockDiffUser(String productId) {
	
		// 加锁
		long time = System.currentTimeMillis() + TIMEOUT ;
		if(!redisLock.lock(productId, String.valueOf(time))){
			throw new SellException(ResultEnum.SECKILL_LOCK_ERROR);
		}
		int stockNum = stock.get(productId);
		if(stockNum == 0){
			throw new SellException(ResultEnum.SECKILL_OVER);
		}else
		{
			//2 模拟下单
			orders.put(KeyUtils.genUniqueKey(), productId);
			//3 减库存
			stockNum = stockNum - 1; 
			try
			{
				Thread.sleep(100);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			stock.put(productId, stockNum);
		}
		//解锁
		redisLock.unlock(productId, String.valueOf(time));
	}

}
