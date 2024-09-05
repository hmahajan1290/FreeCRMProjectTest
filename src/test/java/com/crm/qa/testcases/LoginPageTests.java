package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.SignUpPage;

public class LoginPageTests extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signUpPage;
	
	public LoginPageTests()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test
	public void verifyLoginPageTitle()
	{
		String title = getPageTitle();
		Assert.assertEquals(title, "Cogmento CRM", "Title does not match");
	}
	
	@Test
	public void verifyUserCanLoginWithCorrectCredentials()
	{
		try 
		{
			homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
			assertAreEqual(homePage.loggedUserName.getText(), "Harshad Mahajan", "Logged user is not same", true);
		}
		catch (Exception e) 
		{
			testFailed = true;
			e.printStackTrace();
		}
		
		//Assert.assertEquals(homePage.loggedUserName.getText(), "Harshad Mahajan", "Logged user is not same");
	}
	
	@Test
	public void verifyUserGetsErrorMessageWithWrongCreds()
	{
		loginPage.enterCredentials("Test1", "123456");
		loginPage.clickLoginButton();
		
		assertAreEqual(loginPage.invalidLoginErrorMessage.getText(), "Invalid login", "Invalid login error message text does not match", true);
		
		//Assert.assertEquals(loginPage.invalidLoginErrorMessage.getText(), "Invalid login", "Invalid login error message text does not match");
	}
	
	@Test
	public void verifyClickingOnSignUpNavigatesUserToRegisterPage()
	{
		signUpPage = loginPage.clickSignUp();
		
		assertAreEqual(signUpPage.signUpPageText.getText(), "Register", "User is not on Sign Up page", true);
		
		//Assert.assertEquals(signUpPage.signUpPageText.getText(), "Register", "User is not on Sign Up page");
	}
}
