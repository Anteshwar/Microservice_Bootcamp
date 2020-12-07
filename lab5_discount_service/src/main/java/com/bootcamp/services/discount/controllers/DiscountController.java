package com.bootcamp.services.discount.controllers;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.services.discount.auditor.AuditService;
import com.bootcamp.services.discount.model.DiscountRequest;
import com.bootcamp.services.discount.model.DiscountResponse;

@RestController
/**
 * TODO LABC-1.0 setup RefreshScope for dynamic update of configuraion
 */
@RefreshScope
public class DiscountController {

	@Autowired
	DiscountDataMap discountDataMap;

	// TODO LABB-1.2 add for publishing event
	@Autowired
	AuditService auditService;

	private static Logger logger = LoggerFactory.getLogger(DiscountController.class);

	@PostConstruct
	public void printCategories() {
		logger.info("Available discount categories: {}", discountDataMap.getCategoryDiscount());
	}

	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public DiscountResponse calculateDiscount(@RequestBody DiscountRequest request) {
		logger.info("Received Discount Request for " + request.toString());
		Integer _d_fixedCategoryDiscount = discountDataMap.getCategoryDiscount().get(request.getCategory());
		double fixedCategoryDiscount = _d_fixedCategoryDiscount != null ? _d_fixedCategoryDiscount.intValue() : 0;
		double onSpotDiscount = (new Random()).nextInt(15);
		double discountper = fixedCategoryDiscount + onSpotDiscount;
		double drp = Math.ceil(request.getMrp() - ((discountper / 100) * request.getMrp()));
		DiscountResponse response = new DiscountResponse(request.getCategory(), request.getMrp(), drp,
				fixedCategoryDiscount, onSpotDiscount);
		// TODO LABB-1.3 add for publishing event
		auditService.pubAuditEvent(response);
		return response;
	}
}
