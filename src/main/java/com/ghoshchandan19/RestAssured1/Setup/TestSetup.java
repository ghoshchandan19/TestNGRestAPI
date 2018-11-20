package com.ghoshchandan19.RestAssured1.Setup;

import java.lang.reflect.Method;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ghoshchandan19.RestAssured1.TestUtils.ConfigProperties;
import com.ghoshchandan19.RestAssured1.TestUtils.ExcelReader;
import com.ghoshchandan19.RestAssured1.TestUtils.ExtentManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestSetup {
	
	public ExcelReader excelReader=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\simple.xlsx");
	public static ConfigProperties configProperty=null;
	protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> classLevelLogger = new ThreadLocal<ExtentTest>();
    protected static ThreadLocal<ExtentTest> testLevelLogger = new ThreadLocal<ExtentTest>();
    public static RequestSpecification requestSpecification;
    
    
	@BeforeSuite
	public void setUp()
	{
		ConfigFactory.setProperty("environment","dev");//hard coded environment
	    configProperty=ConfigFactory.create(ConfigProperties.class);
		RestAssured.baseURI=configProperty.getBaseURI();
		RestAssured.basePath=configProperty.getBasePath();
		extent=ExtentManager.GetExtent();
		
	}
	
	@BeforeTest
	public void beforeTest()
	{
		
	}
	
	@AfterTest
	public void afterTest()
	{
		
	}
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass()
	{
		 ExtentTest parent = extent.createTest(getClass().getSimpleName());//Will give the className
		 classLevelLogger.set(parent);
	}
	
	@AfterClass
	public void afterClass()
	
	{
		
	}
	
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		/*ExtentTest child = classLevelLogger.get().createNode(method.getName());
		testLevelLogger.set(child);*/
		
		//System.out.println("Execution of Method: "+method.getName() +"started");
		
		requestSpecification=setRequestSpecification(configProperty.getSecretKey());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult testCaseResult)
	
	{
		/*
		
		if(testCaseResult.getStatus()==ITestResult.FAILURE)
		{
			//testLevelLogger.get().log(Status.FAIL,"The TestCase-->"+testCaseResult.getMethod()+"got failed");
			testLogger().log(Status.FAIL, "The test case got failed");
		}
		
		if(testCaseResult.getStatus()==ITestResult.SKIP)
		{
			//testLevelLogger.get().log(Status.SKIP,"The TestCase-->"+testCaseResult.getMethod().getMethodName()+"got failed");
			testLogger().log(Status.SKIP, "The test case got SKIPPED");
		}
		else
		{
			//testLevelLogger.get().log(Status.PASS,"The TestCase-->"+testCaseResult.getMethod().getMethodName()+"got passed");
			testLogger().log(Status.PASS, "The test case got PASSED");
		}
		extent.flush();
		*/
		
	}
	
	@AfterSuite
    public void tearDown()
    {
	
    }
	public static ExtentTest testLogger()
	{
		return testLevelLogger.get();
	}
	
	public static RequestSpecification setRequestSpecification(String key)
	{
		return given().auth().basic(key,"");
	}
	
}
