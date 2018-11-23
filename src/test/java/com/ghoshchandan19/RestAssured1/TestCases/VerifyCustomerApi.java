package com.ghoshchandan19.RestAssured1.TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghoshchandan19.RestAssured1.POJO.Customers;
import com.ghoshchandan19.RestAssured1.Setup.TestSetup;
import com.ghoshchandan19.RestAssured1.StripeAPI.CustomerAPI;
import com.ghoshchandan19.RestAssured1.TestUtils.DataProviderClass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class VerifyCustomerApi extends TestSetup {

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp")
	public void verifyCreateCustomer(Hashtable<String, String> data)
			throws JsonParseException, JsonMappingException, IOException {
		// Response response = requestSpecification.formParam("email",
		// data.get("email"))
		// .formParam("description", "Test
		// Description").post(data.get("endpoint")).then().extract().response();
		testLogger().assignAuthor("Chandan Ghosh");
		Response response = CustomerAPI.sendPostRequestWithValidData(data);
		testLogger().log(Status.INFO,response.asString());
		// System.out.println(response.asString());
		// Assert.assertEquals(response.statusCode(),data.get("statuscode"));
		ObjectMapper mapper = new ObjectMapper();
		Customers customer = mapper.readValue(response.asString(), Customers.class);
		System.out.println("Email in Response" + customer.getEmail());
		Assert.assertEquals(customer.getEmail(), data.get("email"));

	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp")
	public void verifydeleteCustomer(Hashtable<String, String> data) {
		Response response = requestSpecification.delete(data.get("endpoint")).then().extract().response();
		JsonPath delResponse = response.jsonPath();
		//Assert.assertEquals(delResponse.get("deleted"),"false");
		Assert.assertFalse(delResponse.get("deleted"));
	}

	/*
	 * @Test(dataProviderClass=DataProviderClass.class,dataProvider="dp") public
	 * void createCustomerWithValidKey(Hashtable<String,String> data) {
	 * testLogger().assignAuthor("Chandan Ghosh"); testLogger().assignCategory(
	 * "Regression Test Cases"); testLogger().log(Status.INFO,
	 * "Entered Username :"+data.get("username")); testLogger().log(Status.INFO,
	 * "Entered Username :"+data.get("password"));
	 * //System.out.println("TestCase1");
	 * //System.out.println(data.get("username"));
	 * //System.out.println(data.get("password")); Assert.fail(); }
	 * 
	 * @Test public void customerWithInValidAuth() {
	 * System.out.println("TestCase2"); }
	 * 
	 * @Test public void customerWithValidEmail() {
	 * System.out.println("TestCase3"); }
	 */

}
