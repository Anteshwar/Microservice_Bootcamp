package com.bootcamp.services.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.services.product.dao.ProductRepository;
import com.bootcamp.services.product.model.DiscountRequest;
import com.bootcamp.services.product.model.DiscountResponse;
import com.bootcamp.services.product.model.Product;
import com.bootcamp.services.product.model.ProductDTO;
import com.bootcamp.services.product.model.ProductTag;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * TODO LAB5-2.1 add a service and discount service client
 * 
 * @author amit
 *
 */
@Component
public class ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository repo;

	// TODO LAB5-2.2.1.0 add for direct discovery client
	@Autowired
	DiscoveryClient discoveryClient;

	// TODO LAB6-1.1 add for load balancer client
	@Autowired
	LoadBalancerClient lbClient;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	// TODO LAB9-1.1 add for call via Feign, also add open-feign starter and enable
	// hystrix for feign in properties file
	@Autowired
	DiscountServiceProxyInterface discountProxy;

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public Product getProductById(Integer id) {
		Optional<Product> op = repo.findById(id);
		if (op.isPresent())
			return op.get();
		else
			return null;
	}

	/**
	 * TODO LAB5-2.2.1.1 call discount service using RestTemplate
	 * 
	 * @param p
	 * @return
	 */
	public ProductDTO applyDiscountV1(Product p) {
		DiscountRequest drequest = createDiscountRequest(p);
		List<ServiceInstance> instances = discoveryClient.getInstances("discount-service");
		for (ServiceInstance instance : instances) {
			logger.info("Resolved via Eureka - host: {}, port: {}", instance.getHost(), instance.getPort());
		}
		ServiceInstance instance = instances.get(0);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/calculate";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<DiscountRequest> dre = new HttpEntity<DiscountRequest>(drequest);

		ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST, dre,
				DiscountResponse.class);
		return ceateProductResponseDTO(dResponseEntity.getBody(), p);

	}

	/**
	 * TODO LAB6-1.2 call discount service using RestTemplate
	 * 
	 * @param p
	 * @return
	 */
	public ProductDTO applyDiscountV2(Product p) {
		ServiceInstance instance = lbClient.choose("discount-service");
		logger.info("Resolved via Ribbon - host: {}, port: {}", instance.getHost(), instance.getPort());
		DiscountRequest drequest = createDiscountRequest(p);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/calculate";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<DiscountRequest> dre = new HttpEntity<DiscountRequest>(drequest);

		ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST, dre,
				DiscountResponse.class);
		return ceateProductResponseDTO(dResponseEntity.getBody(), p);

	}

	/**
	 * TODO LAB7-1.1 call direct
	 * 
	 * @param p
	 * @return
	 */
	public ProductDTO applyDiscountV3(Product p) {
		DiscountRequest drequest = createDiscountRequest(p);
		String url = "http://discount-service/calculate";
		logger.info("Calling direct using: {}", url);
		HttpEntity<DiscountRequest> dre = new HttpEntity<DiscountRequest>(drequest);

		ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST, dre,
				DiscountResponse.class);
		return ceateProductResponseDTO(dResponseEntity.getBody(), p);

	}

	/**
	 * TODO LAB8-1.1 add hystrix and hystrix-dashboard started add Hystrix fall back
	 * method
	 * 
	 * show Hystrix Stream
	 * 
	 * @param p
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "discountFallback", commandKey = "V4-CircitBreaker")
	public ProductDTO applyDiscountV4(Product p) {

		DiscountRequest drequest = createDiscountRequest(p);
		String url = "http://discount-service/calculate";
		HttpEntity<DiscountRequest> dre = new HttpEntity<DiscountRequest>(drequest);

		ResponseEntity<DiscountResponse> dResponseEntity = restTemplate.exchange(url, HttpMethod.POST, dre,
				DiscountResponse.class);
		return ceateProductResponseDTO(dResponseEntity.getBody(), p);

	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public ProductDTO discountFallback(Product p) {
		DiscountResponse discountResponse = new DiscountResponse(p.getProductCategory(), p.getMrp(), p.getMrp(), 0.0,
				0.0);
		return ceateProductResponseDTO(discountResponse, p);
	}

	/**
	 * TODO LAB9-1.2 call via Feign
	 * 
	 * @param p
	 * @return
	 */
	public ProductDTO applyDiscountV5(Product p) {
		logger.info("Applying Discount for :" + p);
		DiscountRequest drequest = createDiscountRequest(p);
		createDiscountRequest(p);
		return ceateProductResponseDTO(discountProxy.calculateDiscount(drequest), p);
	}

	private ProductDTO ceateProductResponseDTO(DiscountResponse discountResponse, Product p) {
		ProductDTO pdto = new ProductDTO();
		pdto.setProductCategory(p.getProductCategory());
		pdto.setDrp(discountResponse.getDrp());
		pdto.setFixedCategoryDiscount(discountResponse.getFixedCategoryDiscount());
		pdto.setOnSpotDiscount(discountResponse.getOnSpotDiscount());
		pdto.setProductId(p.getProductId());
		pdto.setMrp(p.getMrp());
		pdto.setProductName(p.getProductName());
		pdto.setDescription(p.getDescription());
		List<String> prodictTags = new ArrayList<>();
		for (ProductTag productTag : p.getProductTags()) {
			prodictTags.add(productTag.getTag());
		}
		pdto.setProductTags(prodictTags);
		return pdto;
	}

	private DiscountRequest createDiscountRequest(Product p) {
		return new DiscountRequest(p.getProductCategory(), p.getMrp());
	}
}
