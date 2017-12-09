package com.jiang.wxshop.utils;

import java.util.List;

import com.jiang.wxshop.vo.ProductVo;
import com.jiang.wxshop.vo.ResultVo;

public class ResultVOUtil {

	public static ResultVo success(Object data){
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(0);
		resultVo.setMsg("msg");
		resultVo.setData(data);
		return resultVo ;
	}
	
	
	public static ResultVo success(){
		return success(null);
	}
	
	public static ResultVo error(Integer code , String msg){
		ResultVo resultVo = new ResultVo();
		resultVo.setCode(code);
		resultVo.setMsg(msg);
		resultVo.setData(null);
		return resultVo ;
	}
}
