package com.store.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Productpage {

	WebDriver driver;
	
	public Productpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="div[id='content'] h1")
	WebElement pageName;
	
	@FindBy(css="div[class='caption'] h4")
	WebElement prodName;
	
	@FindBy(css=".product-thumb .button-group button:first-child")
	WebElement addCartBtn;
	
	@FindBy(css="div[class*='alert-success']")
	WebElement successMessagelocator;
	
	public String productPageName() {
		String text = pageName.getText();
		return text;
	}
	
	public String productName() {
		String text = prodName.getText();
		return text;
	}
	
	public void addTocart() {
		addCartBtn.click();
	}
	
	public String successMessage() {
		String success = successMessagelocator.getText();
		return success;
	}
	
}
