package com.rajprints.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.rajprints.helper.Constants;
import com.rajprints.model.OrderInquiry;
import com.rajprints.service.GeneralService;
import com.rajprints.service.GmailEmailService;
import com.rajprints.service.ValidateHelper;

@Controller
@RequestMapping("/")
@RequestScope
public class GeneralController {
	
	private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	GeneralService generalService;
		
	@GetMapping("home")
	public ModelAndView returnHomePage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("homePageStringArrayMap", Constants.homePageStringArrayMap);
		mv.addObject("frontEndCommonMap", Constants.frontEndCommonMap);
		mv.addObject("orderInquiry", new OrderInquiry());
		logger.info("Calling home page...");
		mv.setViewName("home");
		return mv;
	}

	@PostMapping("quote")
	public ModelAndView saveOrderInquiry(@Valid OrderInquiry orderInquiry, Errors errors) throws Exception { 
		// Errors should be after @Valid, @Valid is inside orderInquiry because the object had to change its class. @Valid is intentionally not applied here instead ValidateHelper.objectValidation method is written for the same.
		
		logger.info("orderInquiry="+ orderInquiry);
		ModelAndView mv = new ModelAndView();
		List<String> otherErrors = GeneralService.initialValidation(orderInquiry);
		if(otherErrors == null) {
			errors = ValidateHelper.objectValidation(orderInquiry.getProductDetails(), errors);							// handles object validation errors
			if(errors.hasErrors()) {
				for (ObjectError e : errors.getAllErrors()) {
					logger.error("ERR: " + e.getDefaultMessage());
				}
				mv.addObject("orderInquiry", orderInquiry);
			} else {
				String quoteId = "RP-" + new Random().nextInt(111111, 1000000);			// 6 digit random number greater than or equal to 111111 & less than 1000000
				String htmlEmailBody = GeneralService.prepareHTMLEmailBody(orderInquiry, quoteId);
				GmailEmailService.sendHTMLEmailWithoutAttachment("RajPrints Quote - " + quoteId, htmlEmailBody, orderInquiry.getProductDetails().getCustomer().getEmail(), true);
				mv.addObject("successMsg", "Email sent successfully "+orderInquiry.getProductDetails().getCustomer().getEmail()+" with quote id: " + quoteId);
				mv.addObject("orderInquiry", new OrderInquiry());
			}
		} else {
			mv.addObject("otherErrors", otherErrors);
			mv.addObject("orderInquiry", orderInquiry);
		}
		mv.addObject("homePageStringArrayMap", Constants.homePageStringArrayMap);
		mv.addObject("frontEndCommonMap", Constants.frontEndCommonMap);
		mv.setViewName("home");
		return mv;
	}
	
	@GetMapping("redirect")
	public String redirectEndpoint(HttpServletRequest request, @RequestParam() Map<String,String> requestParams) {
		logger.info("requestURI="+request.getRequestURL().toString());
		logger.info("requestParams="+requestParams);
		logger.info("Redirecting to home page...");
		return "redirect:/home";
	}
	
	@GetMapping("*")
	public String redirectToHomePage(HttpServletRequest request) {
		logger.info("requestURI="+request.getRequestURL().toString());
		logger.info("Redirecting to home page...");
		return "redirect:/home";
	}

}