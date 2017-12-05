package com.jiang.wxshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiang.wxshop.vo.ResultVo;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	//http:127.0.0.1:8085/sell/buyer/product/list
	@GetMapping("list")
	public ResultVo list(){
		ResultVo resultVo = new ResultVo();
		return resultVo;
	}
}
