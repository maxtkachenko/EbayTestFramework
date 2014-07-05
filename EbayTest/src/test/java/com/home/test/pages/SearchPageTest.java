package com.home.test.pages;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.home.test.basetest.BasicTestCase;

/**
 * 
 * @author Max Tkachenko.
 * SearchPageTest is use to test Ebay.com.
 * Method testLogin performs login and search product on the SearchPage. 
 * After that method  performs logout of users profile.
 *
 */
public class SearchPageTest extends BasicTestCase {
	
	@Test(groups = {"search", "ebayAll"},priority = 0)
	public void testSearch() throws Exception {
		
		if(!homePage.isHomePageLoggedIn()){
		loginPage = homePage.SignIn();
		assertTrue(loginPage.IsPageOpen());
		homePage = loginPage.loginAs(admin);
		assertTrue(homePage.isHomePageLoggedIn(), "Cannot login!!");
		}
		searchPage = homePage.doSearch();
		searchPage.searchProduct("Motorola 525 unlocked");
		assertTrue(searchPage.isSearchDone());
		homePage.logout();
		assertTrue(homePage.isLoggedOut());
	}
}
