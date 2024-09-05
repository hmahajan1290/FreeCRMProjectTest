package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.CreateNewContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class CreateNewContactPageTests extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	CreateNewContactPage createNewContactPage;
	ContactsPage contactsPage;
	
	String testName;
	String sheetName = "Contacts";
	
	public CreateNewContactPageTests()
	{
		super();
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception
	{
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		createNewContactPage = homePage.clickAddNewContactButton();
		Assert.assertTrue(createNewContactPage.validateCreateNewContactLabel());

		testName = method.getName();
	}
	
	@DataProvider
	public Object[][] getContactsData()
	{
		Object contactsData[][] = TestUtil.getTestData(sheetName);
		return contactsData;
	}
	
	@Test
	public void verifyNewContactIsCreatedByJustAddingMandatoryFields()
	{
		String fname = testName.substring(6, 11) + System.currentTimeMillis();
		String lname = testName.substring(11, 16) + System.currentTimeMillis();
		
		createNewContactPage.createNewContact(fname, lname);
		createNewContactPage.validateContactIsCreated(fname + " " + lname);
		contactsPage = homePage.clickContacts();
		contactsPage.validateNewContactIsVisibleInTable(fname + " " + lname);
	}
	
	@Test(dataProvider = "getContactsData")
	public void verifyDataCanBeAddedFromExcelSheet(String firstName, String lastName)
	{
		createNewContactPage.createNewContact(firstName, lastName);
		createNewContactPage.validateContactIsCreated(firstName + " " + lastName);
		contactsPage = homePage.clickContacts();
		contactsPage.validateNewContactIsVisibleInTable(firstName + " " + lastName);
	}
}
