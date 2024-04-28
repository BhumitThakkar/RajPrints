package com.rajprints.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SignsProductDetails extends OtherProductDetails {

	public SignsProductDetails() {
	}
	public SignsProductDetails(Customer customer, String productType, String productName, Float height, Float width, String paperType,
			Integer quantity, String printingTime, String notes) {
		super(customer, productType, productName, quantity, printingTime, notes);
		this.height = height;
		this.width = width;
		this.paperType = paperType;
	}

	@NotNull(message = "Height cannot be empty.")
	@Positive(message = "Height must be positive.")
	@Digits(fraction = 2, integer = 3, message = "Height must be digits only. Max 2 decimal digits allowed.")			// max integer, max fraction
	private Float height;
	
	@NotNull(message = "Width cannot be empty.")
	@Positive(message = "Width must be positive.")
	@Digits(fraction = 2, integer = 3, message = "Width must be digits only. Max 2 decimal digits allowed.")				// max integer, max fraction
	private Float width;
	
//	@NotNull(message = "Paper Type cannot be null.")
//	@NotBlank(message = "Paper Type cannot be empty.")
	@Size(min = 2, message = "Paper Type must have at least 2 characters.")
	private String paperType;

	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}

	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	@Override
	public String toString() {
		return "SignsProductDetails [customer=" + getCustomer() + ", productType=" + getProductType() + ", productName=" + getProductName() +
				", height=" + height + ", width=" + width + ", paperType=" + paperType + ", quantity=" + getQuantity()
				+ ", printingTime=" + getPrintingTime() + ", notes=" + getNotes() + "]";
	}	
	
}
