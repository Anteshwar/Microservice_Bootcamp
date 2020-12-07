package com.bootcamp.services.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.services.product.model.Product;
import com.bootcamp.services.product.model.ProductDTO;
import com.bootcamp.services.product.service.ProductService;

/**
 * TODO LAB5-2.0 add a controller for product rest controller
 * 
 * @author amit
 *
 */
@RestController
@RefreshScope
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(path = "/products", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	// TODO LAB3-2.1 inject config property
	// TODO LAB3-2.2 change fail-fast to false in bootstrap.yaml and show how app
	// falls
	// back to a local source

	@Value("${service.description}")
	private String serviceDescription;

	// TODO LAB3-2.3 show binding of a list type
	@Value("${products.categories}")
	private String[] productCategories;
	
	@Autowired
	Environment environment;


	@GetMapping(path = "/products/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO LAB5-2.2.1.2 add for direct discovery client
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/v1/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductv1(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			ProductDTO pdto = productService.applyDiscountV1(p);
			return new ResponseEntity<ProductDTO>(pdto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO LAB6-1.3 add for load balancer client
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/v2/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductv2(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			ProductDTO pdto = productService.applyDiscountV2(p);
			return new ResponseEntity<ProductDTO>(pdto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO LAB7-1.2 add for direct call to discount service using host name
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/v3/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductv3(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			ProductDTO pdto = productService.applyDiscountV3(p);
			return new ResponseEntity<ProductDTO>(pdto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO LAB8-1.2 add for hystrix
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/v4/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductv4(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			ProductDTO pdto = productService.applyDiscountV4(p);
			return new ResponseEntity<ProductDTO>(pdto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * TODO LAB9-1.3 call via Feign
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/v5/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductv5(@PathVariable Integer id) {
		Product p = productService.getProductById(id);
		if (p != null) {
			ProductDTO pdto = productService.applyDiscountV5(p);
			return new ResponseEntity<ProductDTO>(pdto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * First show #2.3
	 * 
	 * TODO LAB3-2.4 show refresh using /actuator/refresh method Add this method to
	 * fetch the value after change. Also inject {@link Environment} to show how a
	 * refresh will not impact an initialized variable even when underlying
	 * environment changed.
	 * 
	 * Also explain how fallback (fail-fast) is not used when resfresh happens and
	 * ConfigServer is down.
	 */
	@RequestMapping(path = "/products-service-info")
	public Map<String, Object> getServiceInfo() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put("description-environmemt", environment.getProperty("service.description"));
		configMap.put("description-variable", serviceDescription);
		configMap.put("products", productCategories);
		return configMap;
	}

}
