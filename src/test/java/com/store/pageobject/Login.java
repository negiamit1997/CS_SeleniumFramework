package com.store.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="input-email")
	WebElement email;
	
	@FindBy(id="input-password")
	WebElement pass;
	
	@FindBy(css="input[type='submit']")
	WebElement loginBtn;
	
	@FindBy(linkText="Continue")
	WebElement newAccount;
	
	@FindBy(xpath="//a[text()='Continue']")
	WebElement con;
	
	@FindBy(css="div[class*='alert']")
	WebElement Validation;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement logoutBtn;
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement MyAccount;
	
	@FindBy(linkText="Login")
	WebElement Login;
	
	@FindBy(css="div[id='content'] h1")
	WebElement LogoutSuccess;
	
	public void LoginClick() {
		MyAccount.click();
		Login.click();
	}
	
	public void login(String emailadd, String password) {
		email.sendKeys(emailadd);
		pass.sendKeys(password);
		loginBtn.click();
	}
	
	
	//identitfy action on webelement
		public void newAccount() {
			newAccount.click();
		}

		
		public String loginValidation() {
			String text = Validation.getText();
			return text;
		}
		
		public void logout(){
			logoutBtn.click();
		}
		
		public String logoutSuccessfully() {
			String text = LogoutSuccess.getText();
			return text;
		}
		
		public void continueToHome() {
			con.click();
		}
		
		
		
		
}
