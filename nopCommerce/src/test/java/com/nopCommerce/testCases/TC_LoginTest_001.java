package com.nopCommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopCommerce.pageObjects.LoginPage;
import com.nopCommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		logger.info("**********TC_LoginTest_001 started***************");
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp= new LoginPage(driver);
		logger.info("************Providing credentials****************");
		lp.setEmail(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("***********validating login**************");
		String exp_title="Dashboard / nopCommerce administration112";
		String actual_title= driver.getTitle();
		if(exp_title.equals(actual_title))
		{
			logger.info("*********Login Test passed********");
			Assert.assertTrue(true);
		}
		else
		{
			logger.warn("*********Login Test failed********");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		logger.info("Login test Completed");
		}
	
	
}
