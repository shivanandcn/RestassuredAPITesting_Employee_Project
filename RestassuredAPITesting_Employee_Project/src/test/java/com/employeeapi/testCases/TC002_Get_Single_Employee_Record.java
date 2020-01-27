package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("************************ Started TC002_Get_Single_Employee_Record **************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);

		Thread.sleep(3000);
		
	}
	
	@Test
	void checkResposeBody()
	{
		logger.info("******************* Checking Response Body ******************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==> "+responseBody);
		//** Need to enable later--> Assert.assertEquals(responseBody.contains(empID), true);
		
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

		Assert.assertEquals(contentType,"text/html; charset=UTF-8");
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
	void checkContentEncoding()
	{
		logger.info("******************* Checking Content Encoding ******************");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==> "+contentEncoding);

		Assert.assertEquals(contentEncoding,"gzip");
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
		logger.info("******************* Finished TC002_Get_Single_Employee_Record ******************");
		
	}
	

}
