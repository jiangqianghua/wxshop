package com.jiang.wxshop.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.config.WxShopConfig;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.ResponStatusException;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.exception.SellerAuthorizeException;
import com.jiang.wxshop.utils.ResultVOUtil;
import com.jiang.wxshop.vo.ResultVo;

/**
 *拦截异常,返回信息给界面
 * @author jiangqianghua
 */
@ControllerAdvice
public class SellExceptionHandler {

	@Autowired
	private WxShopConfig wxshopConfig ;
	//指示拦截哪个异常
	@ExceptionHandler(value=SellerAuthorizeException.class)
	public ModelAndView handlerAuthorizeException(){
		// 重定向到登录地址
		//return new ModelAndView("redirect:http://127.0.0.1:8085/sell/login");
		//return new ModelAndView("redirect:http://www.baidu.com");
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("redirect:"+wxshopConfig.baseUrl+"/sell/seller/login");
	}
	
	/**
	 * 捕获SellException抛出的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value=SellException.class)
	@ResponseBody
	public ResultVo handlerSellerException(SellException e){
		return ResultVOUtil.error(e.getCode(),e.getMessage());
	}
	
	/**
	 * 以下是异常获取另外一种处理形式，返回http错误状态吗403
	 */
	@ExceptionHandler(value=ResponStatusException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void handlerResponStatusException(){
		
	}
	
	
}
