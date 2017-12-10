package com.jiang.wxshop.utils;

import java.util.Random;

public class KeyUtils {
	/**
	 * 生成唯一主键
	 * @return
	 */
	public static synchronized String genUniqueKey(){
		Random random = new Random();
		Integer number = random.nextInt(900000)+100000;
		return System.currentTimeMillis()+String.valueOf(number);
	}

}
