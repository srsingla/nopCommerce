package com.nopCommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopCommerce.pageObjects.LoginPage;
import com.nopCommerce.testBase.BaseClass;
import com.nopCommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider= "LoginData")
	public void loginTest(String user, String pwd) throws IOException, InterruptedException {
		logger.info("**********TC_LoginTestDDT_002 started***************");
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp= new LoginPage(driver);
		logger.info("************Providing credentials****************");
		lp.setEmail(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		
		logger.info("***********validating loginDDT**************");
		String exp_title="Dashboard / nopCommerce administration";
		String actual_title= driver.getTitle();
		if(exp_title.equals(actual_title))
		{
			logger.info("*********LoginDDT Test passed********");
			lp.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			logger.warn("*********LoginDDT Test failed********");
//			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		logger.info("*****TC_LoginTestDDT_002 finished***********");
		}
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path= System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
		int rowcount= XLUtils.getRowCount(path, "Sheet1");
		int colcount= XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][]= new String[rowcount][colcount] ;
		
		for(int i=1; i<=rowcount; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
			
		}
		return logindata;
		
	}

}
