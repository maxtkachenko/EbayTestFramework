package com.home.test.pages;

import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.home.automationdriver.Browsers;
import com.home.automationdriver.DriverFactory;
import com.home.test.basetest.BasicTestCase;

/**
 * 
 * @author Max Tkachenko.
 * HomePageTest is use to test Ebay.com.
 * Method testDeals checks if we already logged in and if not - performs login, then presses link Deals to open DealsPage.
 * Method testNotification closes subscribe pop-up and opens notification panel.
 *
 */

public class HomePageTest extends BasicTestCase {
	
	@Test(groups = {"home", "ebayAll"},priority = 0)
	public void testDeals() throws Exception{
		if(!homePage.isHomePageLoggedIn()){
			loginPage = homePage.SignIn();
		assertTrue(loginPage.IsPageOpen(), "Login Page isnt open!");
		homePage = loginPage.loginAs(admin);
		assertTrue(homePage.isHomePageLoggedIn(), "Cannot login!!");
		}
		if(DriverFactory.getBrowserName() == Browsers.CHROME){
			System.out.println("Method testDeals is not fully implemented for Chrome because of problem with Subscribe pop-up.");
		}
		else{
			homePage = homePage.openDealsPage();
			assertTrue(homePage.isDialsPageOpen());
		}
	}
	@Test( groups = {"home", "ebayAll"},priority = 1)
	public void testNotifications() throws Exception{
		try{if(homePage.isSubscribePopUp()){
			
			homePage.SubscribeCloseBtn.click();
		}}catch(Exception e){System.out.println("Fail Subscribe!" + e.getMessage());}
		
		ScreenRegion s = new DesktopScreenRegion();
		File buttonPathFile = new File("src\\main\\resources\\sikuli-images\\bell.png");                
        Target imageTarget = new ImageTarget(buttonPathFile);
        ScreenRegion r = s.wait(imageTarget, 6000);
        Mouse mouse = new DesktopMouse();
        mouse.click(r.getCenter());
       String textPost = driver.findElement(By.cssSelector("#ghn-h")).getText();
       
       System.out.println("textPost =" + textPost);
        Assert.assertEquals(textPost, "Notifications");
	}
}
