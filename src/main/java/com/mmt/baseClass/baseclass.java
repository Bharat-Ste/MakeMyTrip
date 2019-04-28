package com.mmt.baseClass;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmt.utilities.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclass 
{
	
	
	public static WebDriver driver;
	public static Properties prop;
	public static String projectlocation = System.getProperty("user.dir");
	
	/**
	 *  @author bharat
	 *  @category , used For Load confir.Properties file
	 */
	
	public baseclass()      
	{
		//Load properties file
		try
		{
			prop = new Properties();
			FileInputStream inp = new FileInputStream(projectlocation+"/src/main/java/com/mmt/config/config.properties");
            prop.load(inp);
            
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void deleteBrowserCookies(int count)
	{
		for(int i=0;i<count;i++)
		{
			driver.manage().deleteAllCookies();
		}
		
	}
	
	/**
	 * @author bharat
	 * @category , Run Test on Specific Browser
	 */
	
	public static void browserInitization()
	{
		String browser = prop.getProperty("browser").toLowerCase();
		String incognito_broswer=prop.getProperty("browser_incognito").toLowerCase();
		
		
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			if(incognito_broswer.equalsIgnoreCase(incognito_broswer))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities capabilities= DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(capabilities);
				
			}
			else
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{

			
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 
			
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		if((prop.getProperty("maximize_browser").toLowerCase()).equalsIgnoreCase("yes"))
		{
			driver.manage().window().maximize();
		}

		driver.get(prop.getProperty("url"));
	
	}
	
	/**
	 * @author bharat
	 * @param locator ,  WebElement
	 * @param timeout ,  pass wait time
	 * @param timeout
	 */
	public static void explicitWait(WebDriver driver,WebElement locator,int timeout)
 	{
 		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
 		//locator.click();
 	} 
	
	
	/**
	 * @author bharat
	 * @param driver
	 * @param locator ,  WebElement
	 * @param timeout ,  pass wait time
	 * @param str     ,  pass the name of city
	 */
	public static void SendKeys(WebDriver driver,WebElement locator,int timeout,String str)
	{
 	
 		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));;
 		locator.click();
 		locator.sendKeys(str);
 	}
	
	/**
	 * @author bharat
	 * @category , Static wait for Four Seconds
	 */
   public static void waitFor_fourSec()
   {
	   try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   public static void waitFor_OneSec()
   {
	   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   /**
	 * @author bharat
	 * @category , Static wait for Two Seconds
	 */
   public static void waitFor_TwoSec()
   {
	   try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	

   
   /**
    * @author bharat
    * @param selector 
    * @param driver
    * @param prop
    * @return , WebElement
    * 
    * Used to get dynamic webElement by passing Selector
    */
	
	public static WebElement getElement(By selector,WebDriver driver, Properties prop)
	{
		WebElement element= driver.findElement(selector);
		if(prop.getProperty("highlightelement").toLowerCase().equalsIgnoreCase("yes"))
		{
			JavaScriptUtil.flash(element, driver);
		}
		return element;
	}
	
	
	/**
	 * @author bharat
	 * @param driver
	 * @param ele
	 * @param prop
	 * 
	 * Used to highlight webelement
	 */
	
	public static void highLightElement(WebDriver driver,WebElement ele, Properties prop)
	{
		//WebElement element= driver.findElement(selector);
		if(prop.getProperty("highlightelement").toLowerCase().equalsIgnoreCase("yes"))
		{
			JavaScriptUtil.flash(ele, driver);
		}
		
	}
	
	public static void press_escape()
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		waitFor_TwoSec();
	}
	


}
