package com.mmt.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptExecutorConcept {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "/Users/NaveenKhunteta/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.get("https://classic.crmpro.com/index.html");

//		WebElement forgotMyPwd = driver.findElement(By.linkText("Forgot my password"));
//		WebElement email = driver.findElement(By.id("username"));
//		WebElement password = driver.findElement(By.id("password"));

		// JavaScriptUtil.clickElementByJS(forgotMyPwd, driver);

		String title = JavaScriptUtil.getTitleByJS(driver);
		System.out.println(title);

		// JavaScriptUtil.refreshBrowserByJS(driver);

		// JavaScriptUtil.generateAlert(driver, "this is javascript");

		// JavaScriptUtil.drawBorder(forgotMyPwd, driver);

//		JavaScriptUtil.flash(email, driver);
//		email.sendKeys("test@gmail.com");
//		JavaScriptUtil.flash(password, driver);
//		password.sendKeys("test@123");
//		JavaScriptUtil.flash(forgotMyPwd, driver);
//		forgotMyPwd.click();
//		JavaScriptUtil.sendKeysUsingJS(driver, "username", "test@gmail.com");
//		JavaScriptUtil.sendKeysUsingJS(driver, "password", "test@gmail.com");

	//	System.out.println(JavaScriptUtil.getBrowserInfo(driver));
		
		//System.out.println(JavaScriptUtil.getPageInnerText(driver));
		
		WebElement pwd = driver.findElement(By.linkText("Forgot Password?"));
		
		//JavaScriptUtil.scrollIntoView(pwd, driver);
		
		JavaScriptUtil.scrollPageDown(driver);
		
	}

}
