package com.jiang.wxshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 秒杀接口
 * @author jiangqianghua
 *
 */

import com.jiang.wxshop.service.SeckillService;
//http://192.168.1.102:8085/sell/skill/query/123456
@RestController
@RequestMapping("/skill")
public class SeckillController {

	private static Logger logger = LoggerFactory.getLogger(SeckillController.class);
	@Autowired
	private SeckillService seckillService ;
	
	@GetMapping("/query/{productId}")
	public String query(@PathVariable String productId) throws Exception
	{
		return seckillService.querySeckillProductInfo(productId);
	}
	/**
	 * 秒杀接口
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/order/{productId}")
	public String skill(@PathVariable String productId) throws Exception
	{
		logger.info("@skill request,productId="+productId);
		seckillService.orderProductMockDiffUser(productId);
		return seckillService.querySeckillProductInfo(productId);
	}
	
}
