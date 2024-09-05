package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//span[@class='selectable ' and text()='Contacts']")
	public WebElement contacts;
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateContactsLabel()
	{
		hoverElement(contacts);
		return contacts.isDisplayed();
	}
	
	public boolean validateNewContactIsVisibleInTable(String fullName)
	{
		WebElement tableRecord = driver.findElement(By.xpath("//td//a[text()='" + 
									 fullName + "']"));
		return tableRecord.isDisplayed();
	}
}
