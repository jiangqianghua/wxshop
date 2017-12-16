package com.jiang.wxshop.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.dataobject.ProductCategory;
import com.jiang.wxshop.dataobject.ProductInfo;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.form.ProductForm;
import com.jiang.wxshop.service.CategroyService;
import com.jiang.wxshop.service.ProductService;
import com.jiang.wxshop.utils.KeyUtils;

@RestController
@RequestMapping("/seller/product")
public class SellerProductController {
	private static Logger logger = LoggerFactory.getLogger(SellerProductController.class);
	@Autowired
	private ProductService productService ;
	
	@Autowired
	private CategroyService categroyService ;
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value="page",defaultValue="1") Integer page,
							@RequestParam(value="size",defaultValue="10") Integer size,
							Map<String,Object> map){
		PageRequest request = new PageRequest(page-1,size);
		Page<ProductInfo> productInfoPage = productService.findAll(request);
		map.put("productInfoPage", productInfoPage);
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("product/list",map) ;
	}
	/**
	 * 上架
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("on_sale")
	public ModelAndView onSale(@RequestParam("productId") String productId,
							Map<String,Object> map){
		try{
			productService.onSale(productId);
		}catch(SellException e){
			logger.error("[卖家端上架商品] 发生异常{}",e.getMessage());
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/prodcut/list");
			return new ModelAndView("common/error",map);
		}
		map.put("msg", ResultEnum.PROCUDE_ONSALE_SUCCESS.getMessage());
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success",map);
	}
	
	/**
	 * 下架
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("off_sale")
	public ModelAndView offSale(@RequestParam("productId") String productId,
							Map<String,Object> map){
		try{
			productService.offSale(productId);
		}catch(SellException e){
			logger.error("[卖家端下架商品] 发生异常{}",e.getMessage());
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/prodcut/list");
			return new ModelAndView("common/error",map);
		}
		map.put("msg", ResultEnum.PROCUDE_OFFSALE_SUCCESS.getMessage());
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success",map);
	}
	/**
	 * 新增和修改
	 * @param productId
	 * @param map
	 * @return
	 */
	@GetMapping("index")
	public ModelAndView index(@RequestParam(value="productId",required=false) String productId,
			Map<String,Object> map){
		if(!StringUtils.isEmpty(productId)){
			ProductInfo productInfo = productService.findOne(productId);
			map.put("productInfo", productInfo);
		}
		
		List<ProductCategory>  catList = categroyService.findAll();
		map.put("categoryList", catList);
		return new ModelAndView("product/index",map);
	}
	
	/**
	 * 保存和更新
	 * @param form
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@PostMapping("save")
	public ModelAndView save(@Valid ProductForm productForm,
							BindingResult bindingResult,
							Map<String,Object> map){
		if(bindingResult.hasErrors()){
			logger.error("[保存商品]参数不正确 productForm={}",productForm.toString());
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error",map);
		}
		
		try{
			ProductInfo productInfo = new ProductInfo();
			if(!StringUtils.isEmpty(productForm.getProductId())){
				productService.findOne(productForm.getProductId());
				BeanUtils.copyProperties(productForm, productInfo);
			}
			else
			{
				BeanUtils.copyProperties(productForm, productInfo);
				productInfo.setProductId(KeyUtils.genUniqueKey());
			}
			productService.save(productInfo);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error",map);
		}
		map.put("msg",  ResultEnum.PROCUDE_SAVE_SUCCESS.getMessage());
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success",map);
		
	}
}
