package com.jiang.wxshop.json;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

// 访问jsonp跨域格式http://127.0.0.1:8085/sell/buyer/product/list?callback=callback
// 支持ajax请求跨域设置
@ControllerAdvice(basePackages = "com.jiang.wxshop.controller")
public class JsonpAdvice extends  AbstractJsonpResponseBodyAdvice{
	public JsonpAdvice() {  
		
        super("callback","jsonp");  
    }  
}
