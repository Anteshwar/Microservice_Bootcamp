package com.bootcamp.services.product.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * TODO LAB4-2.1 add JPA entity, create product database in mysql
 * Also add the db configuration to git configuration 
 * Explain data model
 * 
 * @author amit
 *
 */
@Entity(name = "PRODUCTS")
public class Product {
	@Id
	private Integer productId;
	private String productName;
	private String description;
	private String productCategory;
	private Double mrp;
	@OneToMany(mappedBy = "productId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProductTag> productTags = new ArrayList<>();

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public List<ProductTag> getProductTags() {
		return productTags;
	}

	public void setProductTags(List<ProductTag> productTags) {
		this.productTags = productTags;
	}

}
