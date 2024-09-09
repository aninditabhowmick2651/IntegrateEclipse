package pageEvents;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pageObjects.HomePageElements;

import utils.fetchWebElement;

public class HomePageEvents extends BaseTest{
	fetchWebElement fEle=new fetchWebElement();
	
	
	public void searchProduct() throws InterruptedException
	{
		Thread.sleep(2000);
		logger=extent.createTest("Entering value in search box and clicking on search icon");
		Assert.assertTrue(fEle.getElement("XPATH", HomePageElements.searchBox).isDisplayed(), "Homepage is loaded");
		String product="iphone";
		logger.info("Entering product as: "+product);
		fEle.getElement("XPATH", HomePageElements.searchBox).sendKeys(product);
		//logger.info("ss").fail("SS od amazon current page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
		
	}
	public void submit()
	{
		fEle.getElement("XPATH", HomePageElements.searchButton).submit();
	}
	
	public void verifyFistProduct() throws InterruptedException
	{
		Thread.sleep(2000);
		logger=extent.createTest("Verifying first product in search list");
		
		
		Assert.assertTrue(fEle.getElement("XPATH", HomePageElements.firstProdDisplayed).isDisplayed(), "First product is displayed is displayed as IPhone");
		String str=fEle.getElement("XPATH", HomePageElements.firstProdDisplayed).getText();
		String str1=str.substring(0, 12);
		System.out.println("Original value of first search result: "+str);
		if(str1.contains("Apple iPhone"))
		{
			logger.info("First product is displayed as expected: "+str1);
			System.out.println("First value of search result is as expected: " +str1);
		}
		else
		{
			logger.info("First product is not displayed as expected"+str1);
			System.out.println("First value of search result is as not expected: " +str1);
		}
	}
}
