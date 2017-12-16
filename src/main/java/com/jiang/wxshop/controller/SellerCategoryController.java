package com.jiang.wxshop.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.wxshop.dataobject.ProductCategory;
import com.jiang.wxshop.enums.ResultEnum;
import com.jiang.wxshop.exception.SellException;
import com.jiang.wxshop.form.CategoryForm;
import com.jiang.wxshop.service.CategroyService;

@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {
	
	@Autowired
	private CategroyService categroyService;
	/**
	 * 展示类目
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(Map<String, Object> map){
		List<ProductCategory> prCategories = categroyService.findAll();
		map.put("producrCategory", prCategories);
		return new ModelAndView("category/list",map);
	}
	
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value="categoryId",required=false) Integer categoryId,
								Map<String,Object> map){
		if(categoryId != null){
			ProductCategory productCategory = categroyService.findOne(categoryId);
			map.put("productCategory", productCategory);
		}
		return new ModelAndView("/category/index",map);
	}
	
	
	@PostMapping("/save")
	public ModelAndView save(@Valid CategoryForm form,
			BindingResult bindingResult
			,Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error",map);
		}
		
		ProductCategory productCategory = new ProductCategory();
		try{
			if(form.getCategoryId() != null){
				productCategory = categroyService.findOne(form.getCategoryId());
			}
			BeanUtils.copyProperties(form, productCategory);
			
			categroyService.save(productCategory);
		}catch(SellException e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error",map);
		}
		
		map.put("msg",  ResultEnum.CATEGORY_SAVE_SUCCESS.getMessage());
		map.put("url", "/sell/seller/category/list");
		return new ModelAndView("common/success",map);
	}
}
