package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.home.utils.ConfigProperties;

public class HomePage extends Page {
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	@FindBy(how = How.CLASS_NAME, using ="gh-ua")
	public WebElement fieldUserProfileMenu;
	
	@FindBy(how = How.XPATH, using =".//*[@id='gh-uo']/a")
	public WebElement linkSignOut;
	
	@FindBy(how = How.ID, using ="gh-ac")
	public WebElement SearchBox;
	
	@FindBy(how = How.ID, using ="gh-btn")
	public WebElement SearchButton;
	
	@FindBy(how = How.XPATH, using =".//*[contains(@class,'gh-ug-guest')]/a")
	public WebElement linkSignIn;
	
	@FindBy(how = How.CLASS_NAME, using ="rt")
	public WebElement MyFeed;
	
	@FindBy(how = How.XPATH, using =".//*[@id='gh-p-1']/*[1]")
	public WebElement DailyDeals;
	
	@FindBy(how = How.ID, using ="hypLogo")
	public WebElement DealsLogo;
	
	@FindBy(how = How.CLASS_NAME, using ="email")
	public WebElement EmailLogo;
	
	@FindBy(how = How.CLASS_NAME, using ="btn-blue")
	public WebElement WriteMailBtn;
	
	@FindBy(how = How.ID, using ="subs-close")
	public WebElement SubscribeCloseBtn;
	
	public boolean isLoggedOut(){
		if(isElementPresent(linkSignIn)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isSubscribePopUp(){
		if(isElementPresent(SubscribeCloseBtn)){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isHomePageLoggedIn(){
		
		return isElementPresent(fieldUserProfileMenu);  
	}
	
	public boolean isDialsPageOpen(){
		return isElementPresent(DealsLogo);  
	}
	
	public SearchPage doSearch(){
		return PageFactory.initElements(driver, SearchPage.class);
	}
	
	public LoginPage SignIn(){
		linkSignIn.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public HomePage openDealsPage(){
		DailyDeals.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public HomePage logout(){
		driver.findElement(By.id("gh-ug")).click();
		linkSignOut = driver.findElement(By.linkText("Sign out"));
		linkSignOut.click();
		return PageFactory.initElements(driver,
				HomePage.class);
	}
	
	@Override
	public void open() {
		driver.get(ConfigProperties.getProperty("base.url"));
	}
	
	@Override
	public boolean IsPageOpen() {
		return isElementPresent(SearchButton);
	}
	
}
