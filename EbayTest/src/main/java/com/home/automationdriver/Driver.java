package com.home.automationdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.home.utils.WebDriverListener;
import com.opera.core.systems.OperaDriver;

public class Driver {
	
	private Driver(){};
	
	private static EventFiringWebDriver eventDriver;
	private static WebDriver driver;
	
	public static WebDriver getDriver(String driverKey){
		
		Browsers brtype = Browsers.get(driverKey);
		if (eventDriver == null) {
		switch (brtype) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case IE:
			String ieBinary = "src/main/resources/drivers/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieBinary);
			driver = new InternetExplorerDriver();
			break;
		case CHROME:
			String chromeBinary = "src/main/resources/drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeBinary);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("-test-type");
		    //options.addArguments("-disable-javascript");
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			
			break;
		case OPERA:
			driver = new OperaDriver();
			break;	
			
		default:
			driver = new FirefoxDriver();
			break;
			
		}
		eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(new WebDriverListener());
		}
		return eventDriver;
		//return driver;
	}
	
	public static WebDriver getDriver(){
		return eventDriver;
		//return driver;
	}
	public static void killDriverInstance() throws Exception {
		if(driver!= null){
		driver.quit();
		driver = null;
		eventDriver.quit();
		eventDriver = null;
		}
	}

}
