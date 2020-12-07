package com.bootcamp.services.product.model;

public class DiscountResponse {
	private String category;
	private double mrp;
	private double drp;
	private double fixedCategoryDiscount;
	private double onSpotDiscount;

	public DiscountResponse() {
		super();
	}

	public DiscountResponse(String category, double mrp, double drp, double fixedCategoryDiscount,
			double onSpotDiscount) {
		super();
		this.category = category;
		this.mrp = mrp;
		this.drp = drp;
		this.fixedCategoryDiscount = fixedCategoryDiscount;
		this.onSpotDiscount = onSpotDiscount;
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
		return "DiscountResponse [category=" + category + ", mrp=" + mrp + ", drp=" + drp + ", fixedCategoryDiscount="
				+ fixedCategoryDiscount + ", onSpotDiscount=" + onSpotDiscount + "]";
	}
}
