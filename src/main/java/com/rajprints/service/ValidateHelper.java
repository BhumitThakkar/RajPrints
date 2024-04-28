package com.rajprints.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rajprints.model.OffsetProductDetails;
import com.rajprints.model.OtherProductDetails;
import com.rajprints.model.SignsProductDetails;

@Service
public class ValidateHelper {

	private static Validator validator;

    @Autowired
    public ValidateHelper(Validator validator) {
    	ValidateHelper.validator = validator;
    }

	public static Errors objectValidation(OffsetProductDetails productDetails, Errors orderInquiryErrors) {
		Errors childErrors = null;
		if(productDetails.getProductType().equals("Offset")) {
			childErrors = springValidAfterObjectReset(productDetails);
		} else if(productDetails.getProductType().equals("Signs")) {
			SignsProductDetails signsProductDetails = new SignsProductDetails();
			BeanUtils.copyProperties(productDetails, signsProductDetails);
			childErrors = springValidAfterObjectReset(signsProductDetails);
		} else if(productDetails.getProductType().equals("Other")) {
			OtherProductDetails otherProductDetails = new OtherProductDetails();
			BeanUtils.copyProperties(productDetails, otherProductDetails);
			childErrors = springValidAfterObjectReset(otherProductDetails);
		} else {
			// this will never be executed because GeneralService.initialValidation method is already called and would have thrown error if product type incorrect.
		}
		return childErrors;
	}

	private static Errors springValidAfterObjectReset(OtherProductDetails productDetails) {
		Errors childErrors = new BeanPropertyBindingResult(productDetails, "productDetails");
		ValidationUtils.invokeValidator(validator, productDetails, childErrors);
		return childErrors;
	}

}
