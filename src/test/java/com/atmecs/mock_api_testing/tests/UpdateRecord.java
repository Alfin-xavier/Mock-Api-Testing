package com.atmecs.mock_api_testing.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.mock_api_testing.utilities.Logging;
import com.atmecs.mock_api_testing.utilities.UsersDataProvider;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateRecord
{
	@Test(dataProvider = "createUser", dataProviderClass = UsersDataProvider.class )
	public void updateUser(Object requestBody) throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url = "https://5facee902ec98b0016047956.mockapi.io/users/3";

		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");

		RequestSpecification request = RestAssured.given().headers(headers);

		Response response = request.when().body(requestBody.toString())
							.put(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();

		log.info("Status Code:" + statusCode);
		log.info("Response Body:" + responseBody);

		log.info("Verifying status code");
		Assert.assertEquals(statusCode, 200);

		JsonPath jsonPath = response.jsonPath();

		String name = jsonPath.getString("name");
		System.out.println("Name:" + name);
		
		JSONObject jsonObject = (JSONObject) requestBody;
		Assert.assertEquals(name, jsonObject.get("name").toString());

		String updatedAt = jsonPath.getString("updatedAt");
		System.out.println("CreatedAt:" + updatedAt);

	}
}
