package com.home.test.pages;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.home.test.basetest.BasicTestCase;

public class SearchPageTest extends BasicTestCase {
	
	@Test(groups = {"search", "ebayAll"},priority = 0)//@Test(enabled=false, groups = {"search", "ebayAll"},priority = 0)
	public void testSearch() throws Exception {
		
		if(!homePage.isHomePageLoggedIn()){
		loginPage = homePage.SignIn();
		assertTrue(loginPage.IsPageOpen());
		homePage = loginPage.loginAs(admin);
		assertTrue(homePage.isHomePageLoggedIn(), "Cannot login!!");
		}
		
		searchPage = homePage.doSearch();
		searchPage.searchProduct("Motorola 525 unlocked");
		System.out.println("Test1");
		assertTrue(searchPage.isSearchDone());
		System.out.println("Test2");
		homePage.logout();
		System.out.println("Test3");
		assertTrue(homePage.isLoggedOut());
		System.out.println("Test4");
	}
}
