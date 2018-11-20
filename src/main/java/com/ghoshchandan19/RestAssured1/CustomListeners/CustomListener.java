package com.ghoshchandan19.RestAssured1.CustomListeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ghoshchandan19.RestAssured1.Setup.TestSetup;

public class CustomListener extends TestSetup implements ITestListener, ISuiteListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testLogger()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");


		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLogger().log(Status.FAIL, m);
		
		extent.flush();
	}

	public void onTestSkipped(ITestResult result) {
		testLogger().skip("This test case got skipped");
		extent.flush();
		
	}

	public void onTestStart(ITestResult result) {
		ExtentTest child = classLevelLogger.get().createNode(result.getName());
		testLevelLogger.set(child);
		
	}

	public void onTestSuccess(ITestResult arg0) {
		testLogger().pass("This test case got passed");
		extent.flush();
		
	}

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
