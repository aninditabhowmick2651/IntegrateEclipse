package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.log.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageEvents.HomePageEvents;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;
import utils.Constants;
import utils.fetchWebElement;

public class BaseTest {
	public static WebDriver driver;
	ExtentSparkReporter spark=new ExtentSparkReporter("extent.html");
	public static ExtentReports extent=new ExtentReports();
	public ExtentTest  logger;




	@BeforeTest
	public void beforeTest()
	{
		extent.attachReporter(spark);
		spark.config().setDocumentTitle("Amazon product search: verifying result");
		spark.config().setTheme(Theme.DARK);
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browserName, Method MethodName)
	{
		logger=extent.createTest(MethodName.getName());
		setupDriver(browserName);
		driver.manage().window().maximize();
		
		driver.get(Constants.url);
		//fetchWebElement fEle= new fetchWebElement();
		//HomePageEvents homeEle=new HomePageEvents();
		//Assert.assertTrue(fEle.getElement("XPATH", HomePageElements.SigninButton).isDisplayed(), "Login Button is present");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	@AfterMethod
	public void afterMethod(ITestResult result )
	{
		//logger=extent.createTest(MethodName.getName());
		String path=captureSS(result);
		if(result.getStatus()== ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "Test is failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Failed because of", ExtentColor.RED));
			logger.fail(result.getThrowable());
			
			logger.info("ss").fail("SS od amazon current page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Testcase is skipped", ExtentColor.ORANGE));
			logger.info("ss").skip("SS of amazon current page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());;
		}
		else
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Testcase is passed", ExtentColor.GREEN));
			//logger.addScreenCaptureFromPath(attachingSSToReport(result));
			logger.info("ss").pass("SS of amazon current page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		//String filep=attachingSSToReport(result);
		//System.out.println("screenshot File path fetched from method:"+filep);
		
	}

	public void setupDriver(String str)
	{
		if(str.equalsIgnoreCase("Chrome"))
		{
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}
		else if(str.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

		}
		
		else if(str.equalsIgnoreCase("Headless"))
		{
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--headless=new");
			WebDriverManager.firefoxdriver().setup();
			driver=new ChromeDriver(opt);

		}
		else
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}

	}

	@AfterTest
	public void afterTest()
	{
		//remove old data in report and create new report
		extent.flush();
		//driver.quit();
	}
	
	public String captureSS(ITestResult result)
	{
		String filename=System.getProperty("user.dir")+File.separator+"Screenshot"+File.separator+result.getMethod().getMethodName();
		File f1=((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		File destfile=new File(filename+ ".png");
		try {
			FileUtils.copyFile(f1, destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return destfile.getAbsolutePath();
		String path=destfile.getAbsolutePath();
		//BaseTest.attachingSSToReport(path);
		System.out.println("screenshot File path:"+path);
		return path;
		
		
	}




}
