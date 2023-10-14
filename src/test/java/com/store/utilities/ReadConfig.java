package com.store.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	
	String path = System.getProperty("user.dir") + "\\Configuration\\config.properties";
	
	//constructor
	public ReadConfig(){
		try {
		prop  = new Properties();
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	//get base url
	public String getBaseURL(){
		
		String value = prop.getProperty("baseURL");
		
		if(value!=null) {
			return value;
		}else {
			throw new RuntimeException("URL not specified");
		}
	}
	
	
	//get browser name
public String getbrowser(){
		
		String value = prop.getProperty("browser");
		
		if(value!=null) {
			return value;
		}else {
			throw new RuntimeException("browser not specified");
		}
	}
	
public String getEmail() {
	String Email = prop.getProperty("email");
	return Email;
}
	

public String getPassword() {
	String pass = prop.getProperty("password");
	return pass;
}
	
public String getProduct() {
	String pro = prop.getProperty("product");
	return pro;
}

public String getProductModel() {
	String pro = prop.getProperty("productModel");
	return pro;
}

public String getProductQuantity() {
	String proQuantity = prop.getProperty("productQuantity");
	return proQuantity;
}

public String getProductQuantityUpdateMessage() {
	String proQuantityMessage = prop.getProperty("productQuantityUpdatedMessage");
	return proQuantityMessage;
}


public String getProductPrice() {
	String price=prop.getProperty("prodPrice");
	return price;
}

public String getproductCartSuccessMessage() {
	String text = prop.getProperty("productToCartMessage");
	return text;
}

public String getproductOutofStock() {
	String text = prop.getProperty("productOutofStockValidation");
	return text;
}
	
}
