package com.employeeapi.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	//public String empID="51838"; //Hard-coded input for get details of single employee & update employee.
	public String empID="24"; //Hard-coded input for get details of single employee & update employee.
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger=Logger.getLogger("EmployeesRestAPI");//Added logger
		PropertyConfigurator.configure("Log4j.properties");//Added logger
		logger.setLevel(Level.DEBUG);
	}

}
