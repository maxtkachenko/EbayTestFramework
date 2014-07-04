package com.home.test.pages;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.home.data.UserData;
import com.home.test.basetest.BasicTestCase;
import com.home.utils.ConfigProperties;

public class LoginTest extends BasicTestCase{
	
@Test(groups = {"login", "ebayAll"},priority = 0 )
public void testLogin() throws Exception {
	if(homePage.isLoggedOut()){
		loginPage = homePage.SignIn();
		assertTrue(loginPage.IsPageOpen());
		UserData nadmin = new  UserData(ConfigProperties.getProperty("user1"),ConfigProperties.getProperty("password1"));
		
		homePage = loginPage.loginAs(nadmin);
		if(!homePage.isHomePageLoggedIn()){
			homePage = loginPage.loginAs(admin);
			assertTrue(homePage.isHomePageLoggedIn(), "Cannot login!!");
		}
	}
}

@Test(groups = {"login", "ebayAll"},priority = 1)//enabled=false
public void LogOut() throws Exception{
	if(homePage ==null){return;}
		homePage.logout();
		assertTrue(homePage.isLoggedOut());
	}
}


