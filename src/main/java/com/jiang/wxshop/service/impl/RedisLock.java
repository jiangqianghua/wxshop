package com.jiang.wxshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisLock {

	@Autowired 
	StringRedisTemplate redisTemplate ;
	
	//解锁
	/**
	 * 
	 * @param key
	 * @param value 当前时间＋超时时间
	 * @return
	 */
	public boolean lock(String key, String value)
	{
		// setnx
		if(redisTemplate.opsForValue().setIfAbsent(key, value))
		{
			// 返回true,表示redis不存在，需要设置值，说明已经没锁
			return true;
		}
		//以下是防止死锁，在加锁后，程序执行一些数据库或则什么操作，导致程序抛出异常，不回执行解锁，这个时候
		//下一次再访问就发生死锁
		// 多个线程访问到这里，设置只有一个线程拿到锁
		String currentValue = redisTemplate.opsForValue().get(key);
		// 判断锁是否过期
		if(!StringUtils.isEmpty(currentValue) &&
				Long.parseLong(currentValue) < System.currentTimeMillis())
		{
			//如果过期，获取上一个锁的时间
			//getset
			String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
			if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue))
			{
				return true ;
			}
		}
		// 已经锁定
		return false;
	}
	
	/**
	 * 解锁
	 * @param key
	 * @param value
	 */
	public void unlock(String key,String value){
		try
		{
			String currentValue = redisTemplate.opsForValue().get(key);
			if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
				redisTemplate.opsForValue().getOperations().delete(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
