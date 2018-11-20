package com.ghoshchandan19.RestAssured1.StripeAPI;

import java.util.Hashtable;

import com.ghoshchandan19.RestAssured1.Setup.TestSetup;

import io.restassured.response.Response;

public class CustomerAPI extends TestSetup {
	
	public static Response sendPostRequestWithValidData(Hashtable<String,String> data)
	{
		Response response=requestSpecification.formParam("email", data.get("email"))
		.formParam("description", "Test Description").post(data.get("endpoint")).then().extract().response();
		
		return response;
	}
	
	/*
	public void setDeleteMethodWithValidData(Hastable<String,String> data)
	{
		
	}
	*/

}
