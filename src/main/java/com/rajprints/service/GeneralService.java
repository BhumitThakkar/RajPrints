package com.rajprints.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rajprints.helper.Constants;
import com.rajprints.model.OffsetProductDetails;
import com.rajprints.model.OrderInquiry;
import com.rajprints.model.SignsProductDetails;

import jakarta.validation.Valid;

@Service
public class GeneralService {

	public static String prepareHTMLEmailBody(@Valid OrderInquiry orderInquiry, String quoteId) {
		String htmlEmailBody =
			    "<!DOCTYPE html>" +
			    "<html lang=\"en\">" +
			    "<head>" +
			    "    <meta charset=\"UTF-8\">" +
			    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
			    "    <title>Raj Prints - Order Details</title>" +
			    "</head>" +
			    "<body style=\"font-family: 'Arial', sans-serif; background-color: #f4f4f4; color: #333; margin: 0; padding: 20px;\">" +
			    "" +
			    "    <table style=\"width: 100%; border-collapse: collapse; margin-bottom: 20px;\">" +
			    "        <tr>" +
			    "            <td style=\"text-align: center; padding: 10px;\">" +
			    "                <h1 style=\"color: #008080;\">Raj Prints</h1>" +
			    "            </td>" +
			    "        </tr>" +
			    "    </table>" +
			    "" +
			    "    <h2 style=\"color: #008080;\">Quote Details</h2>" +
			    "<p><strong style=\"color: #006666;\">Quote Id:</strong> " + quoteId + "</p>" +
			    "<p><strong style=\"color: #006666;\">Product Type:</strong> " + orderInquiry.getProductDetails().getProductType() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Product Name:</strong> " + orderInquiry.getProductDetails().getProductName() + "</p>";
		if(orderInquiry.getProductDetails().getProductType().equals("Signs")) {
			htmlEmailBody = htmlEmailBody +
					"<p><strong style=\"color: #006666;\">Dimensions (width x height):</strong> " + ((SignsProductDetails)orderInquiry.getProductDetails()).getWidth() + " x " + ((SignsProductDetails)orderInquiry.getProductDetails()).getHeight() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Paper Type:</strong> " + ((SignsProductDetails)orderInquiry.getProductDetails()).getPaperType() + "</p>";

		}
		if(orderInquiry.getProductDetails().getProductType().equals("Offset")) {
			htmlEmailBody = htmlEmailBody +
				    "<p><strong style=\"color: #006666;\">Coating Type:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getCoatingType() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Folding:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getFolding() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Perforation:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getPerforation() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Coating Side:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getCoatingSide() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Print Side:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getPrintSide() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Color:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getColor() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Binding:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getBinding() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Rounded Corners:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getRoundedCorners() + "</p>" +
				    "<p><strong style=\"color: #006666;\">Hole Drilling:</strong> " + ((OffsetProductDetails)orderInquiry.getProductDetails()).getHoleDrilling() + "</p>";
		}
		htmlEmailBody = htmlEmailBody +
			    "<p><strong style=\"color: #006666;\">Quantity:</strong> " + orderInquiry.getProductDetails().getQuantity() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Printing Time:</strong> " + orderInquiry.getProductDetails().getPrintingTime() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Notes:</strong> " + orderInquiry.getProductDetails().getNotes() + "</p>" +
			    "" +
			    "<h2 style=\"color: #008080;\">Client Information</h2>" +
			    "<p><strong style=\"color: #006666;\">Name:</strong> " + orderInquiry.getProductDetails().getCustomer().getFirstName() + " " + orderInquiry.getProductDetails().getCustomer().getLastName() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Address:</strong> " + orderInquiry.getProductDetails().getCustomer().getStreet() + ", " + orderInquiry.getProductDetails().getCustomer().getCity() + ", " + orderInquiry.getProductDetails().getCustomer().getState() + " " + orderInquiry.getProductDetails().getCustomer().getZip() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Phone:</strong> " + orderInquiry.getProductDetails().getCustomer().getPhone() + "</p>" +
			    "<p><strong style=\"color: #006666;\">Email:</strong> " + orderInquiry.getProductDetails().getCustomer().getEmail() + "</p>" +
			    "" +
			    "<hr style=\"border: 1px solid #008080; margin: 20px 0;\">" +
			    "" +
			    "<p style=\"color: #666; font-size: 12px;\">If you have any questions, feel free to reply back to this email. <strong style=\"color: #008080;\">We will get back to you with your quote soon. "+Constants.homePageStringArrayMap.get("fileUploadMessage")+"</strong></p>" +
			    "" +
			    "</body>" +
			    "</html>";
			return htmlEmailBody;
	}

	@SuppressWarnings("unchecked")
	public static List<String> initialValidation(OrderInquiry orderInquiry) {
		List<String> otherErrors = new ArrayList<>();
		if( !((List<String>)Constants.homePageStringArrayMap.get("productTypes")).contains(orderInquiry.getProductDetails().getProductType())) {
			otherErrors.add("Invalid Product Type:" + orderInquiry.getProductDetails().getProductType() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("productNames_"+orderInquiry.getProductDetails().getProductType()+"List")).contains(orderInquiry.getProductDetails().getProductName())) {
			otherErrors.add("Invalid Product Name:" + orderInquiry.getProductDetails().getProductName() + " for Product Type:" + orderInquiry.getProductDetails().getProductType() + ".");
		}
		if( !(((HashMap<String, List<String>>)Constants.homePageStringArrayMap.get("paperTypesMap")).values().stream().flatMap(x -> x.stream()).collect(Collectors.toList()).contains(orderInquiry.getProductDetails().getPaperType()))) {
			otherErrors.add("Invalid Paper Type:" + orderInquiry.getProductDetails().getPaperType() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("coatingTypes")).contains(orderInquiry.getProductDetails().getCoatingType())) {
			otherErrors.add("Invalid Coating Type:" + orderInquiry.getProductDetails().getCoatingType() + ".");
		}
		if( !(((HashMap<String, List<String>>)Constants.homePageStringArrayMap.get("foldingsMap")).values().stream().flatMap(x -> x.stream()).collect(Collectors.toList()).contains(orderInquiry.getProductDetails().getFolding()))) {
			otherErrors.add("Invalid Folding:" + orderInquiry.getProductDetails().getFolding() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("perforations")).contains(orderInquiry.getProductDetails().getPerforation())) {
			otherErrors.add("Invalid Perforation:" + orderInquiry.getProductDetails().getPerforation() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("coatingSides")).contains(orderInquiry.getProductDetails().getCoatingSide())) {
			otherErrors.add("Invalid Coating Side:" + orderInquiry.getProductDetails().getCoatingSide() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("printSides")).contains(orderInquiry.getProductDetails().getPrintSide())) {
			otherErrors.add("Invalid Print Side:" + orderInquiry.getProductDetails().getPrintSide() + ".");
		}
		if( !((List<String>)Constants.homePageStringArrayMap.get("colors")).contains(orderInquiry.getProductDetails().getColor())) {
			otherErrors.add("Invalid Color:" + orderInquiry.getProductDetails().getColor() + ".");
		}
		if(otherErrors.size() > 0) {
			return otherErrors;
		} else {
			return null;
		}
	}

}