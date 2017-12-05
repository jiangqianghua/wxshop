package com.jiang.wxshop.vo;
/**
 * http请求返回给最外层对象
 * @author jiangqianghua
 *
 */
public class ResultVo<T> {

	private Integer code ; 
	
	private String msg ; 
	
	private T data ;
}
