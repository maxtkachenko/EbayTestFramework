package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchPage extends Page {

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.ID, using ="gh-ug")
	public WebElement fieldUserProfileMenu;
	
	@FindBy(how = How.LINK_TEXT, using ="Sign out")
	public WebElement linkSignOut;
	
	@FindBy(how = How.ID, using ="gh-ac")//.//*[@id='gh-ac']
	public WebElement SearchBox;
	
	@FindBy(how = How.ID, using ="gh-btn")//.//*[@id='gh-btn']
	public WebElement SearchButton;
	
	@FindBy(how = How.ID, using ="inlinefeedbackinitquestion")//.//*[@id='inlinefeedbackinitquestion']
	public WebElement SearchresultQuestion;
	
	@FindBy(how = How.ID, using = "loczip")
	public WebElement ShippingChoiseLink;
	
	public boolean IsPageOpen(){
		return isElementPresent(ShippingChoiseLink);
	}
	public boolean isSearchDone(){
		if(isElementPresent(SearchresultQuestion)){
			return true;
		}
		else{
			return false;
		}
	}
	public void searchProduct(String searchText){
			SearchBox.sendKeys(searchText);
			SearchButton.click();
		}
	@Override
	public void open() {
	}
}
