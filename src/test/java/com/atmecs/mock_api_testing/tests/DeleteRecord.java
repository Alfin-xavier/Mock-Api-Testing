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

public class DeleteRecord 
{
	@Test
	public void deleteRecord() throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url = "https://5facee902ec98b0016047956.mockapi.io/users/2";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.delete(new URL(url)).then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		log.info("Getting status code: "+ statusCode);
		
		log.info("Verifying status code !!");
		Assert.assertEquals(statusCode, 200);
		
		log.info("Verified status code !!"+"\n");
		
		
	}
}
