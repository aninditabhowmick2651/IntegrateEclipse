package pageEvents;



import org.testng.Assert;

import base.BaseTest;
import pageObjects.LoginPageElements;
import utils.fetchWebElement;

public class LoginPageEvents extends BaseTest{
	fetchWebElement fEle= new fetchWebElement();
	public void fLoginPageIsLoaded()
	{
		Assert.assertTrue(fEle.getElements("XPATH", LoginPageElements.LoginButton).size()>0, "Login Button is present");
	}
	
	public void EnterCreds() throws InterruptedException
	{
		Thread.sleep(2000);
		//logger.info("Entering Email");
		fEle.getElement("XPATH",LoginPageElements.Email).sendKeys("abc@gmail.com");
		//logger.info("Entering Password");
		fEle.getElement("XPATH",LoginPageElements.Password).sendKeys("abc@gmail");
	}

}
