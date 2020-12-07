package com.bootcamp.services.discount.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * TODO LAB5-1.1 add configuration processor starter
 * <p>
 * TODO LABC-1.1 setup RefreshScope for dynamic update of configuration and also
 * setup spring-cloud-bus starter in pom of this and configuration server
 * <p>
 * Also add properties related to cloud bus and mq to the
 * applcation.yaml/properties
 * 
 * @author amit
 */
@RefreshScope
@Configuration
@ConfigurationProperties
public class DiscountDataMap {

	private Map<String, Integer> categoryDiscount = new HashMap<>();

	public Map<String, Integer> getCategoryDiscount() {
		return categoryDiscount;
	}

	public void setCategoryDiscount(Map<String, Integer> categoryDiscount) {
		this.categoryDiscount = categoryDiscount;
	}
}
