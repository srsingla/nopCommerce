package com.nopCommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public Properties configPropObj;
	public Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {
		
		logger= Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Configuration\\log4j.properties");

		configPropObj = new Properties();
		FileInputStream configFile = new FileInputStream(
				System.getProperty("user.dir") + "\\Configuration\\config.properties");
		
		configPropObj.load(configFile);
//		System.setProperty("webdriver.chrome.driver", configPropObj.getProperty("chromepath"));
//		// System.getproperty is used to get current directory in java class but in c
//		// ofig.properties it cannot be used because tht is not java class, dot is used
//		// in that file
//		driver = new ChromeDriver();
		
		
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configPropObj.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configPropObj.getProperty("firefoxpath"));
			driver= new FirefoxDriver();
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configPropObj.getProperty("iepath"));
			driver= new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
			

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"\\Screenshots\\"+tname+".png");
				FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	
	public String randomstring() {
		String generatedString1= RandomStringUtils.randomNumeric(4);
		return generatedString1;
	
	}
	
	public String randomNum() {
		String generatedString2= RandomStringUtils.randomNumeric(4);
		return generatedString2;
	}
}
