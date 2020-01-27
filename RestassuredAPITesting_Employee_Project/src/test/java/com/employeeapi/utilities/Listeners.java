package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/myReport.html");
		//htmlReporter = new ExtentHtmlReporter("C:\\Shiva\\JavaPractice\\RestassuredAPITesting_Employee_Project\\Reports\\myReport.html");
		
		htmlReporter.config().setDocumentTitle("automation Report");
		htmlReporter.config().setReportName("Rest API Testing Report");
		
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Employee Project");
		extent.setSystemInfo("Host name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "ShreeGanesh");
		
	}

	
	public void onTestSuccess(ITestResult result)
	{
		
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is " +result.getName());
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Cases Failed is " +result.getName());
		test.log(Status.FAIL, "Test Cases Failed is " +result.getThrowable());
		
	}
	
	public void onTestSkippede(ITestResult result)
	{
		
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Cases Skipped is " +result.getName());
		
	}
	
	public void onFinish(ITestResult result)
	{
		
		extent.flush();
		
	}
	
}
