package com.bootcamp.services.discount.model;

public class DiscountRequest {
	private String category;
	private double mrp;

	public DiscountRequest(String category, double mrp) {
		super();
		this.category = category;
		this.mrp = mrp;
	}

	public DiscountRequest() {
		super();
	}

	@Override
	public String toString() {
		return "DiscountRequest [category=" + category + ", mrp=" + mrp + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
}
