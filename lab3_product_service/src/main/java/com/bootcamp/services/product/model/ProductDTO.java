package com.bootcamp.services.product.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO LAB5-2.2.0.0 setup service model
 * add DTO for service interface and DiscountRequest & DiscountResponse
 * 
 * @author amit
 *
 */
public class ProductDTO {

	private int productId;
	private String productName;
	private String description;
	private String productCategory;
	private double mrp;
	private double drp;
	private double fixedCategoryDiscount;
	private double onSpotDiscount;

	private List<String> productTags = new ArrayList<String>();

	public ProductDTO(int productId, String productName, String description, String productCategory, double mrp, double drp,
			double fixedCategoryDiscount, double onSpotDiscount, List<String> productTags) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.productCategory = productCategory;
		this.mrp = mrp;
		this.drp = drp;
		this.fixedCategoryDiscount = fixedCategoryDiscount;
		this.onSpotDiscount = onSpotDiscount;
		this.productTags = productTags;
	}

	public ProductDTO() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
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

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public List<String> getProductTags() {
		return productTags;
	}

	public void setProductTags(List<String> tags) {
		this.productTags = tags;
	}

	public double getDrp() {
		return drp;
	}

	public void setDrp(double drp) {
		this.drp = drp;
	}

	public double getFixedCategoryDiscount() {
		return fixedCategoryDiscount;
	}

	public void setFixedCategoryDiscount(double fixedCategoryDiscount) {
		this.fixedCategoryDiscount = fixedCategoryDiscount;
	}

	public double getOnSpotDiscount() {
		return onSpotDiscount;
	}

	public void setOnSpotDiscount(double onSpotDiscount) {
		this.onSpotDiscount = onSpotDiscount;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", description=" + description + ", productCategory="
				+ productCategory + ", mrp=" + mrp + ", drp=" + drp + ", fixedCategoryDiscount=" + fixedCategoryDiscount
				+ ", onSpotDiscount=" + onSpotDiscount + ", productTags=" + productTags + "]";
	}

}
