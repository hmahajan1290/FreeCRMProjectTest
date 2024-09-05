package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(className="user-display")
	public WebElement loggedUserName;
	
	@FindBy(css = "i.users.icon")
	public WebElement contactsIcon;
	
	@FindBy(xpath="//span[text()='Contacts']")
	public WebElement contacts;
	
	@FindBy(xpath = "//span[text()='Contacts']//parent::a//following-sibling::button[@class='ui mini basic icon button']")
	public WebElement addNewContactButton;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	public ContactsPage clickContacts()
	{
		hoverElement(contactsIcon);
		click(contacts, "Contacts");
		
		return new ContactsPage();
	}
	
	public CreateNewContactPage clickAddNewContactButton()
	{
		hoverElement(contactsIcon);
		click(addNewContactButton, "Add New Contact + button");
		
		return new CreateNewContactPage();
	}
}
