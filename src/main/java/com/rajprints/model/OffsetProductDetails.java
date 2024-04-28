package com.rajprints.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OffsetProductDetails extends SignsProductDetails {
	public OffsetProductDetails() {
	}
	public OffsetProductDetails(Customer customer, String productType, String productName, Float height, Float width, String paperType, String coatingType,
			String folding, String perforation, String coatingSide, String printSide, String color, String binding, String roundedCorners, String holeDrilling,
			 Integer quantity, String printingTime, String notes) {
		super(customer, productType, productName, height, width, paperType, quantity, printingTime, notes);
		this.coatingType = coatingType;
		this.folding = folding;
		this.perforation = perforation;
		this.coatingSide = coatingSide;
		this.printSide = printSide;
		this.color = color;
		this.binding = binding;
		this.roundedCorners = roundedCorners;
		this.holeDrilling = holeDrilling;
	}

//	@NotNull(message = "Coating Type cannot be null.")
//	@NotBlank(message = "Coating Type cannot be empty.")
	@Size(min = 2, message = "Coating Type must have at least 2 characters.")
	private String coatingType;
	
//	@NotNull(message = "Folding cannot be null.")
//	@NotBlank(message = "Folding cannot be empty.")
	@Size(min = 2, message = "Folding must have at least 2 characters.")
	private String folding;
	
//	@NotNull(message = "Perforation cannot be null.")
//	@NotBlank(message = "Perforation cannot be empty.")
	@Size(min = 2, message = "Perforation must have at least 2 characters.")
	private String perforation;
	
//	@NotNull(message = "Coating Side cannot be null.")
//	@NotBlank(message = "Coating Side cannot be empty.")
	@Size(min = 2, message = "Coating Side must have at least 2 characters.")
	private String coatingSide;
	
//	@NotNull(message = "Print Side cannot be null.")
//	@NotBlank(message = "Print Side cannot be empty.")
	@Size(min = 2, message = "Print Side must have at least 2 characters.")
	private String printSide;
	
//	@NotNull(message = "Color cannot be null.")
//	@NotBlank(message = "Color cannot be empty.")
	@Size(min = 2, message = "Color must have at least 2 characters.")
	private String color;
	
	@NotNull(message = "Binding cannot be empty.")
//	@NotBlank(message = "Binding cannot be empty.")
	@Size(min = 2, message = "Binding must have at least 2 characters.")
	private String binding;				// Yes or No
	
	@NotNull(message = "Rounded Corners cannot be empty.")
//	@NotBlank(message = "Rounded Corners cannot be empty.")
	@Size(min = 2, message = "Rounded Corners must have at least 2 characters.")
	private String roundedCorners;		// Yes or No
	
	@NotNull(message = "Hole Drilling cannot be empty.")
//	@NotBlank(message = "Hole Drilling cannot be empty.")
	@Size(min = 2, message = "Hole Drilling must have at least 2 characters.")
	private String holeDrilling;		// Yes or No

	public String getCoatingType() {
		return coatingType;
	}
	public void setCoatingType(String coatingType) {
		this.coatingType = coatingType;
	}
	
	public String getFolding() {
		return folding;
	}
	public void setFolding(String folding) {
		this.folding = folding;
	}
	
	public String getPerforation() {
		return perforation;
	}
	public void setPerforation(String perforation) {
		this.perforation = perforation;
	}
	
	public String getCoatingSide() {
		return coatingSide;
	}
	public void setCoatingSide(String coatingSide) {
		this.coatingSide = coatingSide;
	}
	
	public String getPrintSide() {
		return printSide;
	}
	public void setPrintSide(String printSide) {
		this.printSide = printSide;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	
	public String getRoundedCorners() {
		return roundedCorners;
	}
	public void setRoundedCorners(String roundedCorners) {
		this.roundedCorners = roundedCorners;
	}
	
	public String getHoleDrilling() {
		return holeDrilling;
	}
	public void setHoleDrilling(String holeDrilling) {
		this.holeDrilling = holeDrilling;
	}
	
	@Override
	public String toString() {
		return "OffsetProductDetails [customer=" + getCustomer() + ", productType=" + getProductType() +
				", productName=" + getProductName() + ", height=" + getHeight() +
				", width=" + getWidth() + ", paperType=" + getPaperType() +
				", coatingType=" + coatingType + ", folding=" + folding +
				", perforation=" + perforation + ", coatingSide=" + coatingSide +
				", printSide=" + printSide + ", color=" + color +
				", binding=" + binding + ", roundedCorners=" + roundedCorners +
				", holeDrilling=" + holeDrilling + ", quantity=" + getQuantity() +
				", printingTime=" + getPrintingTime() + ", notes=" + getNotes() +
				"]";
	}
	
}