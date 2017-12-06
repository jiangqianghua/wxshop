package com.jiang.wxshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiang.wxshop.dataobject.ProductCategory;
import com.jiang.wxshop.dataobject.ProductInfo;
import com.jiang.wxshop.service.CategroyService;
import com.jiang.wxshop.service.ProductService;
import com.jiang.wxshop.vo.ProductInfoVo;
import com.jiang.wxshop.vo.ProductVo;
import com.jiang.wxshop.vo.ResultVo;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	@Autowired
	private ProductService productService ;
	
	@Autowired
	private CategroyService categoryService ;
	//http:127.0.0.1:8085/sell/buyer/product/list
	/**
	{
	code: 0,
	msg: "msg",
	data: [
	{
		name: null,
		type: null,
		foods: [
		{
			id: null,
			name: null,
			price: null,
			description: null,
			icon: null
		}
		]
	}
	]
	}
	**/
	@GetMapping("list")
	public ResultVo list(){
		
		// 查询所有上架的商品
		List<ProductInfo> productInfoList = productService.findUpAll();
		// 查询类目
//		List<Integer> categoryTypeList = new ArrayList<Integer>();
//		
//		for(ProductInfo productInfo:productInfoList){
//			categoryTypeList.add(productInfo.getCategoryType());
//		}
		
		List<Integer> categoryTypeList = productInfoList.stream()
				.map(e -> e.getCategoryType())
				.collect(Collectors.toList());
		List<ProductCategory> productCategoryList = categoryService.findByCategroyTypeIn(categoryTypeList);
		// 数据拼接
		List<ProductVo> productVoList = new ArrayList<ProductVo>();
		for(ProductCategory productCategory:productCategoryList){
			ProductVo productVo = new ProductVo();
			productVo.setCategroyName(productCategory.getCategoryName());
			productVo.setCategoryType(productCategory.getCategoryType());
			
			List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
			for(ProductInfo productInfo:productInfoList){
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVo productInfoVo = new ProductInfoVo();
					// 拷贝属性值
					BeanUtils.copyProperties(productInfo, productInfoVo);
					productInfoVoList.add(productInfoVo);				}
			}
			productVo.setProductInfoVOList(productInfoVoList);
			productVoList.add(productVo);
		}
		
		ResultVo<List<ProductVo>> resultVo = new ResultVo<List<ProductVo>>();
		resultVo.setCode(0);
		resultVo.setMsg("msg");
		resultVo.setData(productVoList);
		return resultVo;
	}
}
