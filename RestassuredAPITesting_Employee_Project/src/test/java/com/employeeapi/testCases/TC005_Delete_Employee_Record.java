package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateEmployeeData() throws InterruptedException
	{
		logger.info("************************ Started TC005_Delete_Employee_Record **************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		//RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		
		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: "+responseBody);
		
		//First get the JasonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		//Capture the ID.
		String empID = jsonPathEvaluator.get("[1].id");
		//int empIDVal = Integer.parseInt(empID);
		System.out.println("ID selected for deletion is: "+empID);
		response = httpRequest.request(Method.DELETE,"/delete/"+empID); 
		
		//Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("StatusCode is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		Thread.sleep(3000);
		
	}
	
	@Test
	void checkResposeBody()
	{
		logger.info("******************* Checking Response Body ******************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==> "+responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
		
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("******************* Checking Response Body ******************");
		
		int statusCode = response.getStatusCode();
		logger.info("Status code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("******************* Checking Response Time ******************");
		
		long responseTime = response.getTime();
		logger.info("Response Time is ==> "+responseTime);

		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("******************* Checking Status Line ******************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);

		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("******************* Checking Conten Type ******************");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);

		Assert.assertEquals(contentType,"application/json;charset=utf-8");
	}
	
	@Test
	void checkServerType()
	{
		logger.info("******************* Checking Server Type ******************");
		
		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);

		Assert.assertEquals(serverType,"nginx/1.16.0");
	}
	
		
	@Test
	void checkContentLength()
	{
		logger.info("******************* Checking Content Length ******************");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==> "+contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");

		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("******************* Checking Cookies ******************");
		
		String cookies = response.getCookie("PHPSESSID");
		System.out.println("Cookies: "+cookies);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******************* Finished TC005_Delete_Employee_Record ******************");
		
	}

}
