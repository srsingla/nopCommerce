package com.nopCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	

	@FindBy(xpath=" //input[@type='email']")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy (xpath="//input[@type='submit']")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy (linkText="Logout")
	@CacheLookup
	WebElement lnkLogout;
	
	
	public void setEmail(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
		
	}
	
	public void setPassword(String pass) {
		txtPassword.clear();
		txtPassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
		
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	
}


