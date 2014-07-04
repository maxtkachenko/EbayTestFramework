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

import com.home.test.basetest.BasicTestCase;

public class HomePageTest extends BasicTestCase {
	
	@Test(groups = {"home", "ebayAll"},priority = 0)//enabled=false
	public void testDeals() throws Exception{
		if(!homePage.isHomePageLoggedIn()){
			loginPage = homePage.SignIn();
		assertTrue(loginPage.IsPageOpen(), "Login Page isnt open!");
		homePage = loginPage.loginAs(admin);
		assertTrue(homePage.isHomePageLoggedIn(), "Cannot login!!");
		}
		homePage = homePage.openDealsPage();
		assertTrue(homePage.isDialsPageOpen());
	}
	@Test( groups = {"home", "ebayAll"},priority = 1)//enabled=false, 
	public void testNotifications() throws Exception{
		try{if(homePage.isSubscribePopUp()){
			homePage.SubscribeCloseBtn.click();
		}}catch(Exception e){System.out.println(e.getMessage());}
		
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
