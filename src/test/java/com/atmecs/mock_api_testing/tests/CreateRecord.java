package com.atmecs.mock_api_testing.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.mock_api_testing.utilities.Logging;
import com.atmecs.mock_api_testing.utilities.UsersDataProvider;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateRecord 
{
	@Test(dataProvider = "createUser", dataProviderClass = UsersDataProvider.class )
	public void createRecord(Object requestBody) throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url ="https://5facee902ec98b0016047956.mockapi.io/users";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.when().body(requestBody.toString())
							.post(new URL(url)).then().extract().response();
		
		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		
		System.out.println("Status Code:"+ statusCode);
		System.out.println("Response Body:"+ responseBody);
		
		
		  Assert.assertEquals(statusCode, 201);
		  
		  JsonPath jsonPath = response.jsonPath();

		  String id = jsonPath.getString("id"); 
		  log.info("Id:"+id);
		  
		  String createdAt = jsonPath.getString("createdAt"); 
		  log.info("CreatedAt:"+createdAt);
			  		 
	}
}
