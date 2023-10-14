package com.store.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.store.pageobject.HomePage;
import com.store.pageobject.Login;
import com.store.pageobject.RegistrationPage;
import com.store.utilities.ReadExcelFile;

public class TC_MyAccountPage extends BaseTest{

	@Test(dataProvider="RegisterDataProvider", enabled=false)
	public void verifyRegistration(String fname, String lname, String mail, String mobile, String pass, String conpass) throws InterruptedException {
		
		
		HomePage HP = new HomePage(driver);
		HP.LoginClick();
		log.info("naviagating to login page");
		
		
		Login lpage = new Login(driver);
		
		lpage.newAccount();
		log.info("navigating to register page for creating new account");
		
		RegistrationPage RP = new RegistrationPage(driver);
		RP.entername(fname, lname);
		RP.enterEmailPhone(mail, mobile);
		RP.password(pass, conpass);
		RP.privacyNewsLeter();
		RP.signup();
		log.info("Filled all the registration details");
		String successMessage = RP.successText();
		System.out.println(successMessage);
		Assert.assertEquals(successMessage, "Your Account Has Been Created!");
		log.info("signup success");
		RP.navigateToHome();
		RP.logginOut();
		log.info("loggedOuT");
		
		HP.LoginClick();
		Thread.sleep(5000);
	}
	
	@Test(dataProvider="LoginDataProvider")
	public void verifyLogin(String userName, String userPass) throws IOException, InterruptedException {
		
		Login lpage = new Login(driver);
		
		lpage.LoginClick();
		System.out.println(userName);
		System.out.println(userPass);
		lpage.login(userName, userPass);
		log.info("Entered the login credentials");
		
		//String validation  = lpage.loginValidation();
		//log.info(validation);
		try {
			if(lpage.loginValidation().equalsIgnoreCase("Warning: No match for E-Mail Address and/or Password.") || lpage.loginValidation().equalsIgnoreCase("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.")) {
				captureScreenshot(driver,"verifyLogin");
				log.info("Screenshot captured");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		lpage.logout();
		
		String logoutSuccessMessage = lpage.logoutSuccessfully();
		Assert.assertEquals(logoutSuccessMessage, "Account Logout");
		
		lpage.continueToHome();
	}
	
	
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\Testdata\\Testdata.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "Login");
		System.out.println("-----------------------"+ttlRows);
		int ttlColumns = ReadExcelFile.getColCount(fileName, "Login");
	

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{
				
				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"Login", i,j);
			}

		}
		return data;
	}
	
	
	@DataProvider(name = "RegisterDataProvider")
	public String[][] RegisterDataProvider(){
		String fileName = System.getProperty("user.dir") + "\\Testdata\\Testdata.xlsx";
		int ttlRows = ReadExcelFile.getRowCount(fileName, "Register");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "Register");
		String data[][]=new String[ttlRows-1][ttlColumns];
		
		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{
				
				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"Register", i,j);
			}

		}
		return data;
	}
	
	
	
	
	
}
