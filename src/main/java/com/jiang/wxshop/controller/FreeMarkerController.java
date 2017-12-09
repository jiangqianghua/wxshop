package com.jiang.wxshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.wxshop.dataobject.ProductInfo;

/**
 * 测试构建html的引擎模版
 * @author jiangqianghua
 *
 */
@Controller
public class FreeMarkerController {
	//http://127.0.0.1:8085/sell/welcome
	 @RequestMapping("/welcome")
	 public String hello(Map<String, Object> map) {
	     map.put("name", "FreeMarker");
	     return "welcome";   
	     //spring.freemarker.suffix=.html
	     //加载welcome.html,如果没有配置，则加载welcom.ftl
	 }
	 
	 @RequestMapping("/list")
	 public String list(Map<String,Object> map){
		 
		 List<ProductInfo> list = new ArrayList<ProductInfo>();
		 for(int i = 0 ; i < 5; i++){
			 ProductInfo item = new ProductInfo();
			 item.setProductName("hudan" + i);
			 list.add(item);
		 }
		 map.put("productlist", list);
		 map.put("name", "FreeMarker");
		 return "welcome" ;
	 }
	 
	 @RequestMapping("/map")
	 public String map(Map<String,Object> map){
		 Map<String,Object> map1 = new HashMap<String,Object>();
		 map1.put("name", "hu dan");
		 map1.put("gender", "welman");
		 map1.put("age", "27");
		 map.put("mapinfo", map1);
		 return "map";
	 }
	 
	 @RequestMapping("/role")
	 public String role(Map<String,Object> map){
		 return "role";
	 }
	
	 public class User{
		 private String name ;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		 
		 
	 }
	 
	 
}
