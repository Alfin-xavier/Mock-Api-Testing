package com.atmecs.mock_api_testing.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.mock_api_testing.utilities.Logging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRecords 
{
	@Test
	public void getApiAuth() throws MalformedURLException
	{
		Logging log = new Logging();
		
		String requestUrl = "https://5facee902ec98b0016047956.mockapi.io/users" ;
		
		log.info("Building the request !!"+"\n");
		
		RequestSpecification request = RestAssured.given();
		
		log.info("Providing access to retrieve the records ang getting response !!"+"\n");
		
		Response response = request.get(new URL(requestUrl));;
		
		int statusCode = response.getStatusCode();
		
		String body = response.getBody().asString();
		
		log.info("Getting response body"+"\n");
		
		log.info("Response body: "+body);
		
		log.info("Getting status code");
		
		log.info("Verifying the status code !!");
		
		log.info("Status Code:"+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		log.info("Verified status code !!");
		
	}
}
