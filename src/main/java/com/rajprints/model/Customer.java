package com.rajprints.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {
	
	public Customer() {
	}
	
	public Customer(String firstName, String lastName, String street, String city, String state, Integer zip, Long phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

//	@NotNull(message = "First Name cannot be null.")
//	@NotBlank(message = "First Name cannot be empty.")
//	@Size(min = 2, message = "First Name must have at least 2 characters.")
	@Pattern(regexp = "^[A-Za-z]{2,}$", message = "First Name must have at least 2 characters. First Name must only be alphabetic. No space allowed.")	// allowed pattern
	private String firstName;
	
//	@NotNull(message = "Last Name cannot be null.")
//	@NotBlank(message = "Last Name cannot be empty.")
//	@Size(min = 2, message = "Last Name must have at least 2 characters.")
	@Pattern(regexp = "^[A-Za-z]{2,}$", message = "Last Name must have at least 2 characters. Last Name must only be alphabetic. No space allowed.")	// allowed pattern
	private String lastName;
	
//	@NotNull(message = "Street cannot be null.")
//	@NotBlank(message = "Street cannot be empty.")
	@Pattern(regexp = "^([1-9][0-9]*[A-Za-z]{0,3})( [A-Za-z0-9]+)+$", message = "Street must be min 2 word alphanumeric. Starting first character must be a number. Address may end in unit number if any. No extra space or special characters allowed.")	// allowed pattern
	private String street;

//	@NotNull(message = "City cannot be null.")
//	@NotBlank(message = "City cannot be empty.")
//	@Size(min = 2, message = "City must have at least 2 characters.")
	@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,1}$", message = "City must have at least 2 characters. City must be max 2 word alphabetic. No extra space or special characters allowed.")	// allowed pattern
	private String city;
	
//	@NotNull(message = "State cannot be null.")
//	@NotBlank(message = "State cannot be empty.")
	@Size(min = 2, message = "State must have at least 2 characters.")
	@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "State must have at least 2 characters. State must be max 3 word alphabetic. No extra space or special characters allowed.")	// allowed pattern
	private String state;
	
	@NotNull(message = "Zip Code cannot be empty.")
//	@Positive(message = "Zip Code cannot be negative or zero.")
	@Min(value = 10000, message = "Zip Code must be 5 digits only not starting with 0.")
//	@Max(value = 99999, message = "Zip Code cannot be more than 5 digits.")
	@Digits(fraction = 0, integer = 5, message = "Zip Code must be 5 digits only not starting with 0.")			// max integer, max fraction
	private Integer zip = null;					// 5 digits only
	
	@NotNull(message = "Phone Number cannot be empty.")
//	@Positive(message = "Phone Number cannot be negative or zero.")
	@Min(value = 1000000000L, message = "Phone Number must be 10 digits only not starting with 0.")
//	@Max(value = 9999999999L, message = "Phone Number cannot be more than 10 digits.")
	@Digits(fraction = 0, integer = 10, message = "Phone Number must be 10 digits only not starting with 0.")		// max integer, max fraction
	private Long phone = null;					// 10 digits only
	
//	@NotNull(message = "Email cannot be null.")
	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Email format is incorrect.")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}
}
