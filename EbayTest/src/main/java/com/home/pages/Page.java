package com.home.pages;

import org.openqa.selenium.*;


public abstract class Page {
	
	protected WebDriver driver;
	
	public Page(WebDriver driver){
		this.driver = driver;
	}

	protected void type(WebElement webElement, String text){
		webElement.clear();
		webElement.sendKeys(text);
	}
	
	public abstract void open();
	
	public abstract boolean IsPageOpen();
	
	public boolean isElementPresent(WebElement element){
		try{
			element.isDisplayed();
			return true;
		}catch (NoSuchElementException e){
			return false;
		}catch(Exception ex){
			System.out.println("================================="+ ex.getMessage());
			return false;
		}
	}
	
}
