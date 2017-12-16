package com.jiang.wxshop.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jiang.wxshop.constant.CookieConstant;
import com.jiang.wxshop.constant.RedisConstant;
import com.jiang.wxshop.controller.SellerOrderController;
import com.jiang.wxshop.exception.SellerAuthorizeException;
import com.jiang.wxshop.utils.CookieUtil;
/**
 * aop方式验证用户信息
 * @author jiangqianghua
 *
 */
@Aspect
@Component
public class SellerAuthorizeAspect {
	private static Logger logger = LoggerFactory.getLogger(SellerAuthorizeAspect.class);
	@Autowired
	private StringRedisTemplate redisTemplate;
	/**
	 * 设置只对卖家订单商品类目操作做验证
	 */
	@Pointcut("execution(public * com.jiang.wxshop.controller.Seller*.*(..))"+
	"&& !execution(public * com.jiang.wxshop.controller.SellerUserController.*(..))")
	public void verify(){}	
	
	/**
	 * 设置在切入点之前做验证操作
	 */
	@Before("verify()")
	public void doVerify(){
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if(cookie == null){
			logger.warn("[登录校验] cookie查不到token");
			throw new SellerAuthorizeException();
		}
		// 查询redis
		String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
		if(StringUtils.isEmpty(tokenValue)){
			logger.warn("[登录校验] redis查不到token");
			throw new SellerAuthorizeException();
		}
	}
}
