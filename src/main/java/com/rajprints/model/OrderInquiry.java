package com.rajprints.model;

import jakarta.validation.Valid;

public class OrderInquiry {
	
	@Valid
	private OffsetProductDetails productDetails = new OffsetProductDetails();

	public OffsetProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(OffsetProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	
	@Override
	public String toString() {
		return "OrderInquiry [productDetails=" + productDetails + "]";
	}
	
}
