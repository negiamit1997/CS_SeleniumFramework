package com.store.testcases;

import org.testng.annotations.Test;

import com.store.pageobject.HomePage;
import com.store.pageobject.Productpage;

import junit.framework.Assert;

public class TC_GuestUserProductPurchase extends BaseTest{
	
	@Test
	public void verifyGuestUserPurchase() {
		HomePage homePage = new HomePage(driver);
		homePage.searchProduct(product);
		
		Productpage productPage = new Productpage(driver);
		productPage.addTocart();
		Assert.assertTrue(false);
		log.info("Guest user should not be able to add the product to cart.");
		
	}

}
