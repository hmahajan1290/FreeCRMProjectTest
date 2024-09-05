package com.crm.qa.pages;

import com.crm.qa.base.TestBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase{
	
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[text()='Login']")
	WebElement loginBtn;
	
	@FindBy(css = "div.ui.negative.message>p")
	public WebElement invalidLoginErrorMessage;
	
	@FindBy(linkText = "Sign Up")
	WebElement signUp;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterCredentials(String emailId, String pwd)
	{
		sendKeys(email, emailId);
		sendKeys(password, pwd);
	}
	
	public void clickLoginButton()
	{
		click(loginBtn, "Login Button");
	}
	
	public SignUpPage clickSignUp()
	{
		click(signUp, "Sign Up link");
		
		return new SignUpPage();
	}
	
	public HomePage login(String emailId, String pwd)
	{
		enterCredentials(emailId, pwd);
		clickLoginButton();
		
		return new HomePage();
	}
}
