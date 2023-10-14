package com.store.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.store.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	ReadConfig rc = new ReadConfig();
	String URL =rc.getBaseURL();
	String browser = rc.getbrowser();
	String login_email = rc.getEmail();
	String login_pass = rc.getPassword();
	String product = rc.getProduct();
	String SuccessProductCart = rc.getproductCartSuccessMessage();
	String productModel = rc.getProductModel();
	String productQuantity = rc.getProductQuantity();
	String prodQuantityUpdateMessage = rc.getProductQuantityUpdateMessage();
	String productPrice=rc.getProductPrice();
	String prodOutofStock = rc.getproductOutofStock();
	
	public static WebDriver driver;
	public static Logger log;
	
	@BeforeClass
	public void setup() {
		
		switch(browser.toLowerCase()) {
		
		case "chrome":
			WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						break;
		case "firefox":
						driver = new FirefoxDriver();
						break;
		case "edge":
						driver = new EdgeDriver();
			
						break;
		default:
						driver = null;
						break;
		
		}
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//for logging
		log = LogManager.getLogger("ShoppingCart");
		
		//opening URL
		driver.get(URL);
		log.info("Url opened");
		
	}
	
	
	
	//capurescreenshort
	public void captureScreenshot(WebDriver driver, String testName) throws IOException {
		
		//convert webdriver object to TakesScreenshot interface
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		
		//call getScreenshotAs method to creat image file
		File Src = screenshot.getScreenshotAs(OutputType.FILE);
		
		File dest = new File(System.getProperty("user.dir") +"\\Screenshots\\"+testName+".png");
		
		//copy image file to destination
		FileUtils.copyFile(Src, dest);
	}
	
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	
}
