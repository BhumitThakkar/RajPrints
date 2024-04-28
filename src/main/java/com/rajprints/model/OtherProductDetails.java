package com.rajprints.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OtherProductDetails {
	
	public OtherProductDetails() {
	}
	public OtherProductDetails(Customer customer, String productType, String productName, Integer quantity, String printingTime, String notes) {
		super();
		this.customer = customer;
		this.productType = productType;
		this.productName = productName;
		this.quantity = quantity;
		this.printingTime = printingTime;
		this.notes = notes;
	}

	@NotNull(message = "Customer details cannot be null.")
	@Valid
	private Customer customer;
	
//	@NotNull(message = "Product Type cannot be null.")
//	@NotBlank(message = "Product Type cannot be empty.")
	@Size(min = 2, message = "Product Type must have at least 2 characters.")
	private String productType;
	
//	@NotNull(message = "Product Name cannot be null.")
//	@NotBlank(message = "Product Name cannot be empty.")
	@Size(min = 2, message = "Product Name must have at least 2 characters.")
	private String productName;
	
	@NotNull(message = "Quantity cannot be empty.")
	@Positive(message = "Quantity must be positive.")
	@Digits(fraction = 0, integer = 6, message = "Quantity must be number only upto 6 digits.")			// max integer, max fraction
	private Integer quantity;

	/*
	@NotNull(message = "Delivery Deadline cannot be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")							// yyyy-MM-dd somehow will get transfered into MM/dd/yyyy when it goes to html5 front end
	@Future(message = "Delivery Deadline must be in future.")
	private LocalDate deliveryDeadline;
	*/
	
//	@NotNull(message = "Printing Time cannot be null.")
//	@NotBlank(message = "Printing Time cannot be empty.")
	@Size(min = 2, message = "Printing Time must have at least 2 characters.")
	private String printingTime;
	
//	@NotNull(message = "Notes cannot be null.")
//	@NotBlank(message = "Notes cannot be empty.")
	@Size(min = 2, message = "Notes must have at least 2 characters.")
	private String notes;

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/*
	public LocalDate getDeliveryDeadline() {
		return deliveryDeadline;
	}
	public void setDeliveryDeadline(LocalDate deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}
	*/
	
	public String getPrintingTime() {
		return printingTime;
	}
	public void setPrintingTime(String printingTime) {
		this.printingTime = printingTime;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return "OtherProductDetails [customer=" + customer + ", productType=" + productType + ", productName=" + productName +
				", quantity=" + quantity + ", printingTime=" + printingTime + ", notes=" + notes + "]";
	}

}
