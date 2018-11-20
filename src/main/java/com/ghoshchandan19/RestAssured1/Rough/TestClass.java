package com.ghoshchandan19.RestAssured1.Rough;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ghoshchandan19.RestAssured1.TestUtils.ConfigProperties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestClass {
	
	
	public static ConfigProperties configProperty;
	@BeforeMethod
	public void beforeMethod(Method method)
	
	{
		System.out.println("Method name "+method.getName());
		ConfigFactory.setProperty("environment","dev");//hard coded environment
	    configProperty=ConfigFactory.create(ConfigProperties.class);
		System.out.println(configProperty.getBaseURI());
		System.out.println(configProperty.getBasePath());
		RestAssured.baseURI=configProperty.getBaseURI();
		RestAssured.basePath=configProperty.getBasePath();
	}
	
	
	
	
	
	@Test
	public void readBaseURI()
	{
		Response response=given().auth().basic("sk_test_XPbhuUljR0FL1uTrfqagOIcP","")
				 .get("customers/cus_DdTb5mEN82JUmn").then().extract().response();
				 
		 
		//.formParam("email","chandan@yahoo.com").formParam("description","test")
		//.post("/customers").then().extract().response();

System.out.println(response.asString());
System.out.println(response.getStatusCode());

		
	
    }
    
	
	
	
	
	
}
		
		





	
	

