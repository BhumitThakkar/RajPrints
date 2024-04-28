package com.rajprints.helper;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	
	private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public static final String apiName = "RajPrints";
	
	// Load other.properties
	private static Properties props = new Properties();
	static {
		try {
			props.load(MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream("other.properties"));
			logger.info("=============other.properties | Start=============");
			logger.info("other.properties size="+props.size());
			logger.info("==================================================");
			List<String> keys = props.keySet().stream().map(x->x.toString()).collect(Collectors.toList());
			Collections.sort(keys);
			for (String key : keys) {
				logger.info(key + "=" + props.getProperty(key));
				if(props.getProperty(key) == null || props.getProperty(key).isBlank()) {
					throw new RuntimeException(key + " cannot be null or blank");
				}
			}
			logger.info("=============other.properties | End===============");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Load environment properties
	private static final Map<String, String> env = System.getenv();					// I also saw somewhere: System.getProperties()
	static {
//		the below block will print all env key value including system environment and custom user pass from environment		
		List<String> envKey = new ArrayList<String>(env.keySet());
		Collections.sort(envKey);
		logger.info("=============env properties | Start=============");
		logger.info("env properties size="+envKey.size());
		logger.info("================================================");
		for (String envName : envKey) {
			logger.info(envName + "=" + env.get(envName));
		}
		logger.info("=============env properties | End===============");
	}
	

	static {
		// Validation 1 : null or blank
		if (props.getProperty("adminEmail") == null || props.getProperty("adminEmail").isBlank()) {
			throw new RuntimeException("adminEmail cannot be null or blank");
		} else if (props.getProperty("adminFirstName") == null || props.getProperty("adminFirstName").isBlank()) {
			throw new RuntimeException("adminFirstName cannot be null or blank");
		} else if (props.getProperty("adminLastName") == null || props.getProperty("adminLastName").isBlank()) {
			throw new RuntimeException("adminLastName cannot be null or blank");
		} else if (props.getProperty("adminPassword") == null || props.getProperty("adminPassword").isBlank()) {
			throw new RuntimeException("adminPassword cannot be null or blank");
		} else if (props.getProperty("adminUsername") == null || props.getProperty("adminUsername").isBlank()) {
			throw new RuntimeException("adminUsername cannot be null or blank");
		} else if (props.getProperty("clientId") == null || props.getProperty("clientId").isBlank()) {
			throw new RuntimeException("clientId cannot be null or blank");
		} else if (props.getProperty("clientSecret") == null || props.getProperty("clientSecret").isBlank()) {
			throw new RuntimeException("clientSecret cannot be null or blank");
		} else if (props.getProperty("clientSecretFileName") == null || props.getProperty("clientSecretFileName").isBlank()) {
			throw new RuntimeException("clientSecretFileName cannot be null or blank");
		} else if (props.getProperty("commaSeperatedCoatingSide") == null || props.getProperty("commaSeperatedCoatingSide").isBlank()) {
			throw new RuntimeException("commaSeperatedCoatingSide cannot be null or blank");
		} else if (props.getProperty("commaSeperatedCoatingType") == null || props.getProperty("commaSeperatedCoatingType").isBlank()) {
			throw new RuntimeException("commaSeperatedCoatingType cannot be null or blank");
		} else if (props.getProperty("commaSeperatedColor") == null || props.getProperty("commaSeperatedColor").isBlank()) {
			throw new RuntimeException("commaSeperatedColor cannot be null or blank");
		} else if (props.getProperty("commaSeperatedFolding") == null || props.getProperty("commaSeperatedFolding").isBlank()) {
			throw new RuntimeException("commaSeperatedFolding cannot be null or blank");
		} else if (props.getProperty("commaSeperatedPaperType") == null || props.getProperty("commaSeperatedPaperType").isBlank()) {
			throw new RuntimeException("commaSeperatedPaperType cannot be null or blank");
		} else if (props.getProperty("commaSeperatedPerforation") == null || props.getProperty("commaSeperatedPerforation").isBlank()) {
			throw new RuntimeException("commaSeperatedPerforation cannot be null or blank");
		} else if (props.getProperty("commaSeperatedPrintSide") == null || props.getProperty("commaSeperatedPrintSide").isBlank()) {
			throw new RuntimeException("commaSeperatedPrintSide cannot be null or blank");
		} else if (props.getProperty("commaSeperatedPrintingTime") == null || props.getProperty("commaSeperatedPrintingTime").isBlank()) {
			throw new RuntimeException("commaSeperatedPrintingTime cannot be null or blank");
		} else if (props.getProperty("commaSeperatedProductTypes") == null || props.getProperty("commaSeperatedProductTypes").isBlank()) {
			throw new RuntimeException("commaSeperatedProductTypes cannot be null or blank");
		} else if (props.getProperty("commaSeperatedStates") == null || props.getProperty("commaSeperatedStates").isBlank()) {
			throw new RuntimeException("commaSeperatedStates cannot be null or blank");
		} else if (props.getProperty("commaSeperatedTerminateSendingEmailIfErrorMsgs") == null || props.getProperty("commaSeperatedTerminateSendingEmailIfErrorMsgs").isBlank()) {
			throw new RuntimeException("commaSeperatedTerminateSendingEmailIfErrorMsgs cannot be null or blank");
		} else if (props.getProperty("emailHost") == null || props.getProperty("emailHost").isBlank()) {
			throw new RuntimeException("emailHost cannot be null or blank");
		} else if (props.getProperty("emailPort") == null || props.getProperty("emailPort").isBlank()) {
			throw new RuntimeException("emailPort cannot be null or blank");
		} else if (props.getProperty("emailProtocol") == null || props.getProperty("emailProtocol").isBlank()) {
			throw new RuntimeException("emailProtocol cannot be null or blank");
		} else if (props.getProperty("emailWriteTimeOutInMS") == null || props.getProperty("emailWriteTimeOutInMS").isBlank()) {
			throw new RuntimeException("emailWriteTimeOutInMS cannot be null or blank");
		} else if (props.getProperty("fileUploadMessage") == null || props.getProperty("fileUploadMessage").isBlank()) {
			throw new RuntimeException("fileUploadMessage cannot be null or blank");
		} else if (props.getProperty("fromEmailId") == null || props.getProperty("fromEmailId").isBlank()) {
			throw new RuntimeException("fromEmailId cannot be null or blank");
		} else if (props.getProperty("fromEmailIdAuthKey") == null || props.getProperty("fromEmailIdAuthKey").isBlank()) {
			throw new RuntimeException("fromEmailIdAuthKey cannot be null or blank");
		} else if (props.getProperty("orgName") == null || props.getProperty("orgName").isBlank()) {
			throw new RuntimeException("orgName cannot be null or blank");
		} else if (props.getProperty("productNames_Offset") == null || props.getProperty("productNames_Offset").isBlank()) {
			throw new RuntimeException("productNames_Offset cannot be null or blank");
		} else if (props.getProperty("productNames_Other") == null || props.getProperty("productNames_Other").isBlank()) {
			throw new RuntimeException("productNames_Other cannot be null or blank");
		} else if (props.getProperty("productNames_Signs") == null || props.getProperty("productNames_Signs").isBlank()) {
			throw new RuntimeException("productNames_Signs cannot be null or blank");
		} else if (props.getProperty("refreshToken") == null || props.getProperty("refreshToken").isBlank()) {
			throw new RuntimeException("refreshToken cannot be null or blank");
		} else if (props.getProperty("refreshTokenBufferInSec") == null || props.getProperty("refreshTokenBufferInSec").isBlank()) {
			throw new RuntimeException("refreshTokenBufferInSec cannot be null or blank");
		} else if (props.getProperty("sleepInSecAfterEmailSent") == null || props.getProperty("sleepInSecAfterEmailSent").isBlank()) {
			throw new RuntimeException("sleepInSecAfterEmailSent cannot be null or blank");
		} else if (props.getProperty("terminateSendingEmailsOnAnyException") == null || props.getProperty("terminateSendingEmailsOnAnyException").isBlank()) {
			throw new RuntimeException("terminateSendingEmailsOnAnyException cannot be null or blank");
		}
		
		
		// Validation 2 : numbers
		if (!props.getProperty("emailPort").strip().matches("^\\d*$")) {											// must be checked before int parsing
			throw new RuntimeException("emailPort must be numeric string");
		} else if (!props.getProperty("emailWriteTimeOutInMS").strip().matches("^\\d*$")) {							// must be checked before int parsing
			throw new RuntimeException("emailWriteTimeOutInMS must be numeric string");
		} else if (!props.getProperty("sleepInSecAfterEmailSent").strip().matches("^\\d*$")) {					// must be checked before int parsing
			throw new RuntimeException("sleepInSecAfterEmailSent must be numeric string");
		}
	}
	
	public static final String adminEmail = props.getProperty("adminEmail").strip();
	public static final String adminFirstName = props.getProperty("adminFirstName").strip();
	public static final String adminLastName = props.getProperty("adminLastName").strip();
	public static final String adminPassword = props.getProperty("adminPassword").strip();
	public static final String adminUsername = props.getProperty("adminUsername").strip();
	private static final List<String> coatingSides = Arrays.asList(props.getProperty("commaSeperatedCoatingSide").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> coatingTypes = Arrays.asList(props.getProperty("commaSeperatedCoatingType").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> colors = Arrays.asList(props.getProperty("commaSeperatedColor").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> foldingsList = Arrays.asList(props.getProperty("commaSeperatedFolding").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> paperTypesList = Arrays.asList(props.getProperty("commaSeperatedPaperType").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> perforations = Arrays.asList(props.getProperty("commaSeperatedPerforation").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> printSides = Arrays.asList(props.getProperty("commaSeperatedPrintSide").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> printingTimes = Arrays.asList(props.getProperty("commaSeperatedPrintingTime").strip().split("(\\s)*,(\\s)*"));
	private static final List<String> states = Arrays.asList(props.getProperty("commaSeperatedStates").strip().split("(\\s)*,(\\s)*"));
	public static final List<String> terminateSendingEmailIfErrorMsgs = Arrays.asList(props.getProperty("commaSeperatedTerminateSendingEmailIfErrorMsgs").strip().split("(\\s)*,(\\s)*"));		// "Daily user sending quota exceeded, Authentication failed"
	public static final String emailHost = props.getProperty("emailHost").strip();									// "smtp.gmail.com"
	public static final int emailPort = Integer.parseInt(props.getProperty("emailPort").strip());					// 587
	public static final String emailProtocol = props.getProperty("emailProtocol").strip();							// "smtp"
	public static final String emailWriteTimeOutInMS = props.getProperty("emailWriteTimeOutInMS").strip();			// "60000"
	public static final String fromEmailId = props.getProperty("fromEmailId").strip();
	public static final String fromEmailIdAuthKey = props.getProperty("fromEmailIdAuthKey").strip();
	private static final String orgName = props.getProperty("orgName").strip();										// RajPrints
	private static final List<String> productTypes = Arrays.asList(props.getProperty("commaSeperatedProductTypes").strip().split("(\\s)*,(\\s)*"));
	private static final String productNames_OffsetArray[] = props.getProperty("productNames_Offset").strip().split("(\\s)*,(\\s)*");
	private static final List<String> productNames_OffsetList = Arrays.stream(productNames_OffsetArray).map(x -> {
		return x.contains("[")?x.substring(0, x.indexOf("[")).strip():x.strip();
	}).toList();
	private static final List<String> productSizes_OffsetList = Arrays.stream(productNames_OffsetArray).map(x -> {
		return x.contains("[")?x.substring(x.indexOf("[")+1, x.indexOf("]")).strip():"";
	}).toList();
	private static final String productNames_SignsArray[] = props.getProperty("productNames_Signs").strip().split("(\\s)*,(\\s)*");
	private static final List<String> productNames_SignsList = Arrays.stream(productNames_SignsArray).map(x -> {
		return x.contains("[")?x.substring(0, x.indexOf("[")).strip():x.strip();
	}).toList();
	private static final List<String> productSizes_SignsList = Arrays.stream(productNames_SignsArray).map(x -> {
		return x.contains("[")?x.substring(x.indexOf("[")+1, x.indexOf("]")).strip():"";
	}).toList();
	private static final String productNames_OtherArray[] = props.getProperty("productNames_Other").strip().split("(\\s)*,(\\s)*");
	private static final List<String> productNames_OtherList = Arrays.stream(productNames_OtherArray).map(x -> {
		return x.contains("[")?x.substring(0, x.indexOf("[")).strip():x.strip();
	}).toList();
	private static final List<String> productSizes_OtherList = Arrays.stream(productNames_OtherArray).map(x -> {
		return x.contains("[")?x.substring(x.indexOf("[")+1, x.indexOf("]")).strip():"";
	}).toList();
//	private static final List<String> productNames_SignsList = Arrays.asList(props.getProperty("productNames_Signs").strip().split("(\\s)*,(\\s)*"));
//	private static final List<String> productNames_OtherList = Arrays.asList(props.getProperty("productNames_Other").strip().split("(\\s)*,(\\s)*"));
	public static final int sleepInSecAfterEmailSent = Integer.parseInt(props.getProperty("sleepInSecAfterEmailSent").strip());					// "30" Seconds
	public static final boolean terminateSendingEmailsOnAnyException = Boolean.parseBoolean(props.getProperty("terminateSendingEmailsOnAnyException").strip());
	private static final String fileUploadMessage = props.getProperty("fileUploadMessage").strip();
	public static final String clientSecretFileName = props.getProperty("clientSecretFileName").strip();
	public static String clientId = props.getProperty("clientId").strip();
	public static String clientSecret = props.getProperty("clientSecret").strip();
	public static String refreshToken = props.getProperty("refreshToken").strip();
	public static final int refreshTokenBufferInSec = Integer.parseInt(props.getProperty("refreshTokenBufferInSec").strip());					// "30" Seconds
	static {
		// Validation 3 : exact values | KEEPING STRICT AS MUCH AS POSSIBLE
		if (!emailProtocol.equals("smtp")) {
			throw new RuntimeException("emailProtocol must be \"smtp\"");
		} else if (!emailHost.equals("smtp.gmail.com")) {
			throw new RuntimeException("emailHost must be \"smtp.gmail.com\"");
		} else if (emailPort != 587) {
			throw new RuntimeException("emailPort must be 587");
		} else if(sleepInSecAfterEmailSent < 5) {
			throw new RuntimeException("sleepInSecAfterEmailSent must not be less than 5");
		} else if (! props.getProperty("terminateSendingEmailsOnAnyException").strip().toUpperCase().matches("TRUE|FALSE")) {
			throw new RuntimeException("terminateSendingEmailsOnAnyException must be true or false");
		}
	}
	
	// Initialize Maps
	public static final Map<String, Object> homePageStringArrayMap = new LinkedHashMap<>();
	public static final Map<String, Object> frontEndCommonMap = new LinkedHashMap<>();
	private static final Map<String, List<String>> paperTypesMap = new LinkedHashMap<>(); 
	private static final Map<String, List<String>> foldingsMap = new LinkedHashMap<>();
	
	static {
//		paperTypes
		paperTypesList.forEach(x->{
			String[] key_value = x.strip().split("(\\s)*\\|(\\s)*");
			if(paperTypesMap.get(key_value[1]) == null){
				paperTypesMap.put(key_value[1], new ArrayList<>());
			}
			paperTypesMap.get(key_value[1]).add(key_value[0]);
		});
//		foldings
		foldingsList.forEach(x->{
			String[] key_value = x.strip().split("(\\s)*\\|(\\s)*");
			if(foldingsMap.get(key_value[1]) == null){
				foldingsMap.put(key_value[1], new ArrayList<>());
			}
			foldingsMap.get(key_value[1]).add(key_value[0]);
		});
	}

	static {
		homePageStringArrayMap.put("coatingSides", coatingSides);
		homePageStringArrayMap.put("coatingTypes", coatingTypes);
		homePageStringArrayMap.put("colors", colors);
		homePageStringArrayMap.put("foldingsMap", foldingsMap);
		homePageStringArrayMap.put("paperTypesMap", paperTypesMap);
		homePageStringArrayMap.put("perforations", perforations);
		homePageStringArrayMap.put("printSides", printSides);
		homePageStringArrayMap.put("printingTimes", printingTimes);
		homePageStringArrayMap.put("productTypes", productTypes);
		homePageStringArrayMap.put("productNames_OffsetList", productNames_OffsetList);
		homePageStringArrayMap.put("productSizes_OffsetList", productSizes_OffsetList);
		homePageStringArrayMap.put("productNames_SignsList", productNames_SignsList);
		homePageStringArrayMap.put("productSizes_SignsList", productSizes_SignsList);
		homePageStringArrayMap.put("productNames_OtherList", productNames_OtherList);
		homePageStringArrayMap.put("productSizes_OtherList", productSizes_OtherList);
		homePageStringArrayMap.put("fileUploadMessage", fileUploadMessage);
		homePageStringArrayMap.put("adminEmail", adminEmail);
		frontEndCommonMap.put("orgName", orgName);
		frontEndCommonMap.put("states", states);
	}

}

// .split("(,\\s)|(,)") ========> (,\s) should be tried before (,)
// .split("(\\s)*,(\\s)*"); ========> grab 0 to infinite before and after space surrounding ','