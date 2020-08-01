package com.harshalshewale.apiautomationtestingframework.util;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;

public class RestUtil {
	
	public static String totalApiTime;
	
	public static Map<String,String> DEFAULT_HEADER=new HashMap<String,String>();
	
	static {
		
		DEFAULT_HEADER.put("Content-Type", "application/json");
	}

	public static String makePostCall(String url,Map<String,String> headers,String body) {
		long startTime = System.currentTimeMillis();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request=RestAssured.given().log().all(true);
		DEFAULT_HEADER.putAll(headers);
		request.headers(DEFAULT_HEADER);
		request.body(body);
		Response response=request.post(url);
		ResponseBody<?> responsebody = response.getBody();
		long endTime   = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		totalApiTime=Long.toString(totalTime);
		return responsebody.asString();
		
	}
	
	public static String makeGetCall(String url,Map<String,String> headers,String body) {
		DEFAULT_HEADER.putAll(headers);
		
		return null;
			
	}
	
	public static String makeUpdateCall(String url,Map<String,String> headers,String body) {
		DEFAULT_HEADER.putAll(headers);
		return null;
		
	}
	
	public static String makeDeleteCall(String url,Map<String,String> headers,String body) {
		DEFAULT_HEADER.putAll(headers);
		return null;
		
	}
}
