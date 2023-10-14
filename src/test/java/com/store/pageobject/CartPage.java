package com.store.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='list-inline']/li[4]")
	WebElement cartBtn;
	
	@FindBy(css=".table-responsive tbody td:nth-child(2)")
	WebElement prodName;
	
	@FindBy(css=".table-responsive tbody td:nth-child(3)")
	WebElement prodModel;
	
	@FindBy(css=".table-responsive tbody td:nth-child(4) input")
	WebElement addQuantityField;
	
	@FindBy(css="div[class*='alert-success']")
	WebElement successLocator;
	
	@FindBy(css=".table-responsive tbody td:nth-child(4) button[data-original-title='Update']")
	WebElement updateBtn;
	
	@FindBy(css=".table-responsive tbody td:nth-child(6)")
	WebElement priceLocator;
	
	@FindBy(xpath="//a[text()='Checkout']")
	WebElement checkoutBtn;
	
	@FindBy(css="div[class*='alert-danger']")
	WebElement prodOutStockLocator;
	
	public void goToCart() {
		cartBtn.click();
	}
	
	public String getProductName() {
		String text = prodName.getText();
		return text;
	}
	
	public String getProductModel() {
		String text = prodModel.getText();
		return text;
	}
	
	public void updatProductQuantity(String productQuantity) {
		addQuantityField.clear();
		addQuantityField.sendKeys(productQuantity);
		updateBtn.click();
	}
	
	public String getSuccessText() {
		String text = successLocator.getText();
		return text;
	}
	
	public String getTotalPrice() {
		String text = priceLocator.getText();
		return text;
	}
	
	public void checkout() {
		checkoutBtn.click();
	}
	
	public String productOutofStock() {
		String text = prodOutStockLocator.getText();
		return text;
	}
	
}
