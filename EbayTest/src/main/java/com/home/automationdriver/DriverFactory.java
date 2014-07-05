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

public class DriverFactory {
	
	private DriverFactory(){};
	
	private static EventFiringWebDriver eventDriver;
	private static WebDriver driver;
	private static Browsers brtype;
	
	
	public static WebDriver getDriver(String driverKey){
		
		brtype = Browsers.get(driverKey);
		if (eventDriver == null) {
			
		switch (brtype) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case IE:
			String ieBinary = "src/main/resources/drivers/IEDriverServer.exe";// pass to Internet Explorer driver
			System.setProperty("webdriver.ie.driver", ieBinary);
			driver = new InternetExplorerDriver();
			break;
		case CHROME:
			String chromeBinary = "src/main/resources/drivers/chromedriver.exe";// pass to Chrome driver
			System.setProperty("webdriver.chrome.driver", chromeBinary);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("-test-type");// disabled warning notice about wrong flag
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
		eventDriver = new EventFiringWebDriver(driver);// wrapping a WebDriver
		eventDriver.register(new WebDriverListener());//Connect to Listener
		}
		return eventDriver;
	}
	
	public static WebDriver getDriver(){
		return eventDriver;
	}
	
	public static Browsers getBrowserName(){
		return brtype;
	}
	
	public static void killDriverInstance() throws Exception {//quit and closing  driver instances
		if(driver!= null){
		driver.quit();
		driver = null;
		eventDriver.quit();
		eventDriver = null;
		}
	}

}
