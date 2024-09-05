package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class CreateNewContactPage extends TestBase{
	
	@FindBy(xpath="//span[@class='selectable ' and text()='Create New Contact']")
	public WebElement createNewContactLabel;
	
	@FindBy(name="first_name")
	WebElement firstNameField;
	
	@FindBy(name="last_name")
	WebElement lastNameField;
	
	@FindBy(css="button.ui.linkedin.button")
	WebElement saveButton;
	
	public CreateNewContactPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateCreateNewContactLabel()
	{
		hoverElement(createNewContactLabel);
		return createNewContactLabel.isDisplayed();
	}
	
	public void enterFirstName(String fname)
	{
		sendKeys(firstNameField, fname);
	}
	
	public void enterLastName(String lname)
	{
		sendKeys(lastNameField, lname);
	}
	
	public void clickSave()
	{
		click(saveButton, "Save");
	}
	
	public void createNewContact(String fname, String lname)
	{
		enterFirstName(fname);
		enterLastName(lname);
		clickSave();
	}
	
	public boolean validateContactIsCreated(String fullName)
	{
		WebElement fullNameLabel = driver.findElement(By.xpath("//span[@class='selectable ' and text()='" 
									+ fullName + "']"));
		return fullNameLabel.isDisplayed();
	}
}
