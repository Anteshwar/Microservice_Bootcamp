package com.bootcamp.services.product.service;

import org.springframework.stereotype.Component;

import com.bootcamp.services.product.model.DiscountRequest;
import com.bootcamp.services.product.model.DiscountResponse;

@Component
public class DiscountServiceFallback implements DiscountServiceProxyInterface {

	@Override
	public DiscountResponse calculateDiscount(DiscountRequest request) {
		return new DiscountResponse(request.getCategory(), request.getMrp(), request.getMrp(), 0.0, 0.0);
	}

}
