// do not uncomment, customScript is used to ensure if this js file is loaded or not in home.html
var customScript = "custom.js loaded-->printed from custom.js file";
console.log(customScript);

// upon error on submit form
window.onload = function() {
	/*
	// Get today's date
	let today = new Date();
	// Get tomorrow's date
	let tomorrow = new Date(today);
	tomorrow.setDate(today.getDate() + 5);
	// Format the date in "yyyy-mm-dd" format
	let formattedTomorrow = `${tomorrow.getFullYear()}-${(tomorrow.getMonth() + 1).toString().padStart(2, '0')}-${tomorrow.getDate().toString().padStart(2, '0')}`;
	document.getElementById("deliveryDeadline").setAttribute("min", formattedTomorrow);
  	*/
  	if(document.getElementById("productDetails.productType").value != ""){
		var productType = document.getElementById("productDetails.productType").value
		changeBorderColorOfProductType(productType);
		resetHiddenFields(productType);
	} else {
		selectProductType("Offset");		// somehow it needs productDetails.productType selected or Errors object is not populated and null pointer error is thrown.
	}
};

function selectProductType(productType) {
	document.getElementById("productDetails.productType").value = productType;					// setting to hidden field
	changeBorderColorOfProductType(productType);
	resetHiddenFields(productType);
	resetProductNameList();
	resetHeightAndWidth();
}

function selectProductSize(){
	alert("working");
}

function selectProductSize(	productNames_OffsetList, productNames_SignsList, productNames_OtherList,
							productSizes_OffsetList, productSizes_SignsList, productSizes_OtherList
							) {
	var productName_List;
	var productSize_List;
	var productSize_Index;
	if(document.getElementById("productDetails.productType").value == "Offset") {
		productName_List = productNames_OffsetList.split(", ");
		productSize_List = productSizes_OffsetList.split(", ");		
	}
	else if(document.getElementById("productDetails.productType").value == "Signs") {
		productName_List = productNames_SignsList.split(", ");
		productSize_List = productSizes_SignsList.split(", ");
	}
	else if(document.getElementById("productDetails.productType").value == "Other") {
		productName_List = productNames_OtherList.split(", ");
		productSize_List = productSizes_OtherList.split(", ");
	} else {
		alert("error, please contact owner");
	}
	productSize_Index = productName_List.indexOf(document.getElementById("productDetails.productName").value);
	var size = productSize_List[productSize_Index];
	if(size.includes("h")){
		document.getElementById("productDetails.height").value = size.substring(0, size.indexOf("h"));
		document.getElementById("productDetails.width").value = size.substring(size.indexOf("x") + 1, size.indexOf("w"));
	} else {
		resetHeightAndWidth();
	}
}

function changeBorderColorOfProductType(productType) {
	/*to change border color*/
	const productTypes = document.getElementsByClassName("productType");
	for (var i = 0; i < productTypes.length; i++) {
	  productTypes[i].setAttribute("class", "productType border p-2 mt-2 d-flex border-secondary justify-content-center");
	}
	document.getElementById("productType_"+productType).setAttribute("class", "productType border-bottom border-top border-3 p-2 mt-2 d-flex justify-content-center border-primary rounded");
}
function resetHiddenFields(productType) {
	const productNames = document.getElementsByClassName("productName");
	for (var i = 0; i < productNames.length; i++) {
	  productNames[i].setAttribute("hidden", "hidden");
	}
	const specificTypeProductNames = document.getElementsByClassName("productType_"+productType);
	for (var i = 0; i < specificTypeProductNames.length; i++) {
	  specificTypeProductNames[i].removeAttribute("hidden");
	}
	if(productType == 'Offset'){
		document.getElementById("hideForOtherProductType").removeAttribute("hidden");
		document.getElementById("hideForSignsAndOtherProductType").removeAttribute("hidden");
	}
	if(productType == 'Signs'){
		document.getElementById("hideForOtherProductType").removeAttribute("hidden");
		document.getElementById("hideForSignsAndOtherProductType").setAttribute("hidden", "hidden");
	}
	if(productType == 'Other'){
		document.getElementById("hideForOtherProductType").setAttribute("hidden", "hidden");
		document.getElementById("hideForSignsAndOtherProductType").setAttribute("hidden", "hidden");
	}
}

function resetProductNameList() {
	document.getElementById("productDetails.productName").selectedIndex = 0;
}

function resetHeightAndWidth() {
	document.getElementById("productDetails.height").value = "";
	document.getElementById("productDetails.width").value = "";
}