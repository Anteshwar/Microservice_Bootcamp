package com.bootcamp.services.product.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bootcamp.services.product.model.DiscountRequest;
import com.bootcamp.services.product.model.DiscountResponse;

/**
 * TODO LAB8-1.1 add for Frign
 * @author amit
 *
 */
@FeignClient(name = "discount-service", fallback = DiscountServiceFallback.class)
public interface DiscountServiceProxyInterface {

	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public DiscountResponse calculateDiscount(DiscountRequest request);

}
