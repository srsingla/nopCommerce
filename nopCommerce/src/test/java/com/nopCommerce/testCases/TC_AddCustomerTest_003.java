package com.nopCommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.pageObjects.AddCustomerPage;
import com.nopCommerce.pageObjects.LoginPage;
import com.nopCommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp= new LoginPage(driver);
		
		lp.setEmail(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);
		
		AddCustomerPage addCust= new AddCustomerPage(driver);
		addCust.clickOnCustomersMenu();
		addCust.clickOnCustomersMenuItem();
		addCust.clickOnAddnew();
		logger.info("**********Providing Customer details***********");
		
		String email= randomstring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("seema");
		
		addCust.setLastName("singla");
		addCust.setGender("Female");
		addCust.setDob("01/21/1988");
		addCust.setCompanyName("busyQA");
		addCust.setCustomerRoles("Guest");
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setAdminContent("This is for testing.........");
		addCust.clickOnSave();
		Thread.sleep(5000);
		// validation

				String msg = driver.findElement(By.tagName("body")).getText();

				if (msg.contains("The new customer has been added successfully")) {
					logger.info("***************  Customer added succesfully *********** ");
					Assert.assertTrue(true);

				} else {
					logger.error("*************** Customer Not added succesfully *********** ");
					captureScreen(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}

				logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
				
			}
	}
	


