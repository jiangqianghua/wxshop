package com.jiang.wxshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.filter.ThresholdFilter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	private static Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
        logger.info("微信订餐项目已经启动...");
    }
}
