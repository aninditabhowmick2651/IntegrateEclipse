package qa.tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;

import utils.fetchWebElement;

public class NewTest extends BaseTest{
	fetchWebElement fetchEle=new fetchWebElement();
	HomePageEvents homeEle=new HomePageEvents();
	//LoginPageEvents loginEle=new LoginPageEvents();
	
	
  @Test
  public void Test1() throws InterruptedException {
	  logger.info("Amazon.com is loading");
	  
	  homeEle.searchProduct();
	  homeEle.submit();
	  homeEle.verifyFistProduct();
	  
	  
  }
}
