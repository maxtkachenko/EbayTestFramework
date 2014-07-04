package com.home.test.basetest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.home.automationdriver.Driver;
import com.home.data.UserData;
import com.home.pages.HomePage;
import com.home.pages.LoginPage;
import com.home.pages.SearchPage;
import com.home.utils.ConfigProperties;

public class BasicTestCase  {
	
	protected static WebDriver driver;
	protected HomePage homePage = null;
	protected LoginPage loginPage = null;
	protected SearchPage searchPage = null;
	
	public UserData admin= new UserData (ConfigProperties.getProperty("user","ma-a662"),ConfigProperties.getProperty("password", "11111tkacha"));
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"browser"})
	protected WebDriver getWebDriver(String browser){//@Optional("firefox")
		if(driver == null){
			driver= Driver.getDriver(browser);//open browser
			driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("limit.wait")), TimeUnit.SECONDS);//how many second wait object
		}
		return driver;
	}
	@BeforeTest(alwaysRun = true)
	public void OpenPage() throws Exception {
		if(driver == null){
			driver = Driver.getDriver();
		}
		if(homePage==null){
		homePage = PageFactory.initElements(driver,
		HomePage.class);
		homePage.open();
		}
	}
	@AfterSuite( alwaysRun = true)//enabled = false,
	public void ShutDown() throws Exception{
		Driver.killDriverInstance();
	}
}
