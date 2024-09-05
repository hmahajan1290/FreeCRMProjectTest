package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.crm.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static boolean testFailed = false;
	
	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream("/Users/tejshad/eclipse-workspace/FreeCRMProjectTest/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/Users/tejshad/eclipse-workspace/Browsers/chromedriver");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("url"));
	}
	
	public static void sendKeys(WebElement element, String value)
	{
		try 
		{
			element.clear();
			element.sendKeys(value);
			System.out.println("Sending '" + value + "' to element '" + element.toString() + "'");
		} 
		catch (Exception e) 
		{
			testFailed = true;
			e.printStackTrace();
		}
	}
	
	public static void click(WebElement element, String elementName)
	{
		try
		{
			element.click();
			System.out.println("Clicking " + elementName);
		}
		catch (Exception e) 
		{
			testFailed = true;
			e.printStackTrace();
		}
	}
	
	public static String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public static void hoverElement(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}
	
	public static void assertAreEqual(Object expected, Object actual, String message, boolean invert)
	{
		try 
		{
			Assert.assertEquals(expected, actual, message);
			System.out.println(getLocalTimeNow() + " - [ASSERT PASSED] " + messageSwitcher(message, invert));
		} 
		catch (Exception e) 
		{
			testFailed = true;
			System.out.println(getLocalTimeNow() + " - [ASSERT FAILED] " + message);
			e.printStackTrace();
		}
	}
	
	public static String messageSwitcher(String actualMessage, boolean positiveCase)
	{
		String outMessage = null;
		
		if(positiveCase)
		{
			if(actualMessage.contains("does not "))
				outMessage = actualMessage.replace("does not ", "does ");
			else if(actualMessage.contains("is not "))
				outMessage = actualMessage.replace("is not ", "is ");
		}
		else
		{
			if(actualMessage.contains("does "))
				outMessage = actualMessage.replace("does ", "does not ");
			else if(actualMessage.contains("is "))
				outMessage = actualMessage.replace("is ", "is not ");
		}
		
		return outMessage;
	}
	
	public static String getLocalTimeNow()
	{
		return java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(testFailed)
		{
			try 
			{
				TestUtil.takeScreenshotAtEndOfTest();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		driver.quit();
	}
}
