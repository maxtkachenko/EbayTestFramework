package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.home.data.UserData;

public class LoginPage extends Page {
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@Override
	protected void type(WebElement webElement, String text) {
		super.type(webElement, text);
	}
	
	@FindBy(how = How.ID, using ="userid")
	public WebElement filedUsername;
	
	@FindBy(how = How.ID, using ="pass")
	public WebElement fieldPassword;
	
	@FindBy(how = How.ID, using = "sgnBt")
	public WebElement buttonSignIn;
	@Override
	public boolean IsPageOpen() {
		return isElementPresent(filedUsername);
	}
	public HomePage loginAs (UserData user){
		type(filedUsername, user.name);
		type(fieldPassword, user.password);
		buttonSignIn.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
	@Override
	public void open() {
	}
}
