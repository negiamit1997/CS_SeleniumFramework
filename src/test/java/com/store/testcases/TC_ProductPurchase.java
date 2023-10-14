package com.store.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.store.pageobject.CartPage;
import com.store.pageobject.HomePage;
import com.store.pageobject.Login;
import com.store.pageobject.Productpage;

import junit.framework.Assert;

public class TC_ProductPurchase extends BaseTest{

	@Test()
	public void verifySearchProduct() throws IOException, InterruptedException {
		
		log.info("***********Starting search product**************************");
		Login loginPage = new Login(driver);
		loginPage.LoginClick();
		
		log.info("Navigated to login page");
		
		loginPage.login(login_email, login_pass);
		
		log.info("Login success");
		
		HomePage homePage = new HomePage(driver);
		homePage.searchProduct(product);
		
		log.info("Searching product");
		
		Productpage productPage = new Productpage(driver);
		String pageName = productPage.productPageName();
		String productname = productPage.productName();
		
		if(pageName.contains(product)) {
			
			if(productname.equalsIgnoreCase(product)) {
				productPage.addTocart();
				String success = productPage.successMessage();
				if(success.contains("Success")) {
					Assert.assertTrue(true);
				}else {
					Assert.assertFalse(true);
				}
				log.info("Product successfully added to cart");
			}
			
		}else {
			log.info("No data found");
			captureScreenshot(driver, "verifySearchProduct");
		}
		
		Thread.sleep(3000);
		homePage.logout();
		log.info("Logout Success");
		
	}
	
	@Test(dependsOnMethods={"verifySearchProduct"})
	public void BuyProduct() throws InterruptedException {
		log.info("***********Buy product**************************");

		Login loginPage = new Login(driver);
		loginPage.LoginClick();
		
		log.info("Navigated to login page");
		
		loginPage.login(login_email, login_pass);
		
		log.info("Login success");
		
		CartPage cartPage = new CartPage(driver);
		cartPage.goToCart();
		
		log.info("Navigate to cart page");
		
		if(cartPage.getProductName().contains(product)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
		
		log.info("Product verified");
		
		if(cartPage.getProductModel().contains(productModel)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(false);
		}
		
		log.info("Product model verified");
		
		cartPage.updatProductQuantity(productQuantity);
		
		
		if(cartPage.getSuccessText().contains(prodQuantityUpdateMessage)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		log.info("Product quantity successfully updated");
		
		if(cartPage.getTotalPrice().equals(productPrice)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		log.info("Product price verified successfully");
		
		cartPage.checkout();
		
		if(cartPage.productOutofStock().contains(prodOutofStock)){
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		
		log.info("***********Buy product ends**************************");

		
		
	}
	
}
