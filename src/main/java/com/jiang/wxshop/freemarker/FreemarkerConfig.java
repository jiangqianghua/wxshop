package com.jiang.wxshop.freemarker;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {
	 @Autowired
	  private Configuration configuration;
	  @Autowired
	  private RoleDirectiveModel roleDirectiveModel;

	  @PostConstruct
	  public void setSharedVariable() throws TemplateModelException {
	    configuration.setSharedVariable("role", roleDirectiveModel);
	  }
}
