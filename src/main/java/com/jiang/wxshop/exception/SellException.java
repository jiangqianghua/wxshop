package com.jiang.wxshop.exception;

import com.jiang.wxshop.enums.ResultEnum;
/**
 * 异常类
 * @author jiangqianghua
 *
 */
public class SellException extends RuntimeException {
	
	private Integer code ; 
	
	public SellException(ResultEnum resultEnum){
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}
	
	public SellException(ResultEnum resultEnum,String message){
		super(message);
		this.code = resultEnum.getCode();
	}
	

}
