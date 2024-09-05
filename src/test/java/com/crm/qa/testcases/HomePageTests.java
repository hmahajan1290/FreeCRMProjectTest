package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.CreateNewContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTests extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	CreateNewContactPage createNewContactPage;
	
	public HomePageTests()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyClickingOnContactsRedirectsToContactsPage()
	{
		contactsPage = homePage.clickContacts();
		Assert.assertTrue(contactsPage.validateContactsLabel());
	}
	
	@Test
	public void verifyCreateNewCustomerPageOpensWhenPlusButtonIsClicked()
	{
		createNewContactPage = homePage.clickAddNewContactButton();
		Assert.assertTrue(createNewContactPage.validateCreateNewContactLabel());
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
