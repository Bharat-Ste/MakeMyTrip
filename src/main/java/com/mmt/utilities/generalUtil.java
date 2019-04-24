package com.mmt.utilities;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class generalUtil 
{
	//DropDown
	
	//RadioButton
	
	//threadSleep
	
	public static long PAGE_LOAD_TIMEOUT = 500 ;
	public static long IMPLICIT_Wait = 100 ;
	public static int explicitWait_time=30;
	
	 public static void explicitWait(WebDriver driver,WebElement locator,int timeout)
	 	{
	 		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
	 		//locator.click();
	 	}
	

}
