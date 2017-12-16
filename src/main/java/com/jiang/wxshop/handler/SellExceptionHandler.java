package com.jiang.wxshop.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.exception.SellerAuthorizeException;

/**
 *拦截异常
 * @author jiangqianghua
 *
 */
@ControllerAdvice
public class SellExceptionHandler {

	//指示拦截哪个异常
	@ExceptionHandler(value=SellerAuthorizeException.class)
	public ModelAndView handlerAuthorizeException(){
		// 重定向到登录地址
		//return new ModelAndView("redirect:http://127.0.0.1:8085/sell/login");
		return new ModelAndView("redirect:http://www.baidu.com");
	}
}
