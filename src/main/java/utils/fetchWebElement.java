package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class fetchWebElement {
	public WebElement getElement(String identifiertype, String identifierValue)
	{
		switch(identifiertype)
		{
		case "XPATH":
			return BaseTest.driver.findElement(By.xpath(identifierValue));
		case "CssSelector":
			return BaseTest.driver.findElement(By.cssSelector(identifierValue));
			
		default:
		return null;
	}
	}
	
	public List<WebElement> getElements(String identifiertype, String identifierValue)
	{
		switch(identifiertype)
		{
		case "XPATH":
			return BaseTest.driver.findElements(By.xpath(identifierValue));
		case "CssSelector":
			return BaseTest.driver.findElements(By.cssSelector(identifierValue));
			
		default:
		return null;
	}
	}

}
