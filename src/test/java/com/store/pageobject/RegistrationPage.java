package com.store.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	public WebDriver driver;
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}
	
	@FindBy(id="input-firstname")
	WebElement firstname;
	
	@FindBy(id="input-lastname")
	WebElement  lastname;
	
	@FindBy(id="input-email")
	WebElement email;
	
	@FindBy(id="input-telephone")
	WebElement phone;
	
	@FindBy(id="input-password")
	WebElement pass;
	
	@FindBy(id="input-confirm")
	WebElement conPass;
	
	@FindBy(css=".radio-inline input[value='1']")
	WebElement newsLetter;
	
	@FindBy(css="input[name='agree']")
	WebElement privacyPolicy;
	
	@FindBy(css="input[value='Continue']")
	WebElement submit;
	
	@FindBy(tagName = "h1")
	WebElement success;
	
	@FindBy(xpath="//a[text()='Continue']")
	WebElement con;

	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement logout;
	
	public void entername(String fname, String lname) {
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
	}
	
	public void enterEmailPhone(String emailAddress, String mobile) {
		email.sendKeys(emailAddress);
		phone.sendKeys(mobile);
	}
	
	
	public void password(String password, String confirmPass) {
		pass.sendKeys(password);
		conPass.sendKeys(confirmPass);
	}
	
	public void privacyNewsLeter() {
		newsLetter.click();
		privacyPolicy.click();
	}
	
	public void signup() {
		submit.click();
	}

	
	public  String successText(){
		String text = success.getText();
		return text;
	}
	
	public void navigateToHome() {
		con.click();
	}
	
	public void logginOut() {
		logout.click();
	}
}
