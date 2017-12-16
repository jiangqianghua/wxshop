package com.jiang.wxshop.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.constant.CookieConstant;
import com.jiang.wxshop.constant.RedisConstant;
import com.jiang.wxshop.dataobject.SellerInfo;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.service.SellerService;
import com.jiang.wxshop.utils.CookieUtil;
/**
 * 卖家登录登出操作
 * @author jiangqianghua
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

	@Autowired
	private  SellerService sellerService;
	@Autowired
	private StringRedisTemplate redisTemplate ;
	/**
	 * 登录操作 http://127.0.0.1:8085/sell/seller/login?openid=150700
	 * @param openid
	 * @param response
	 * @param map
	 * @return
	 */
	@GetMapping("/login") 
	public ModelAndView login(@RequestParam("openid") String openid,
					HttpServletResponse response,
					Map<String, Object> map){
		
		// openid 和数据库匹配
		SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
		if(sellerInfo == null){
			map.put("msg", ResultEnum.LOGIN_ERROR.getMessage());
			map.put("url", "order/list");
			return new ModelAndView("common/error",map);
		}
		//设置token到redis
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConstant.EXPIRE;
		
		// key是 token_uuid  value是openid  
		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid,expire,TimeUnit.SECONDS);
		//设置token到cookie
		CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
		//登录成功，链接地址跳转, 注意跳转用绝对地址
		return new ModelAndView("redirect:http://127.0.0.1:8085/sell/seller/order/list");
	}
	/**
	 * 登出操作  http://127.0.0.1:8085/sell/seller/logout
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
						HttpServletResponse response,
						Map<String, Object> map){
		Cookie cookie = CookieUtil.get(request, 	CookieConstant.TOKEN);
		if(cookie != null){
			// redis清除cookie
			redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
			// cookie清除,设置过期时间为0
			CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
		}
		map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
		map.put("url", "order/list");
		return new ModelAndView("common/success",map);
	}
}
