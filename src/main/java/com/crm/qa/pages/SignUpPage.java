package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class SignUpPage extends TestBase{
	
	@FindBy(css = "div.ui.raised.segment>h2.ui.blue.header")
	public WebElement signUpPageText;
	
	public SignUpPage()
	{
		PageFactory.initElements(driver, this);
	}

}
