package com.store.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.model.Log;

public class HomePage {

	//1.create object of webdriver
	WebDriver driver;
	
	//constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//identify webelements
	@FindBy(xpath="//a[@title='My Account']")
	WebElement MyAccount;
	
	@FindBy(linkText="Login")
	WebElement Login;

	
	@FindBy(css="input[name='search']")
	WebElement search;
	
	@FindBy(xpath="//div[@id='search']/span")
	WebElement searchBtn;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logoutBtn;
	
	//identify action on webelement
	public void LoginClick() {
		MyAccount.click();
		
		Login.click();
	}
	
	public void searchProduct(String productName) {
		search.sendKeys(productName);
		searchBtn.click();
		}
	
	public void logout() {
		MyAccount.click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
		logoutBtn.click();
	}
	
}
