package com.mmt.testPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mmt.baseClass.baseclass;
import com.mmt.pages.landingPage;

public class landingPageTest extends baseclass 
{
	landingPage landingpage;
	public Actions action;

	public landingPageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setup()
	{
		browserInitization();
		landingpage= new landingPage();
	}
	
	@Test(priority = 1, enabled=true,groups= {"Page Elements verification"})
	public void verifyMMT_Logo()
	{
		boolean status = landingpage.verifyMMT_logo();
		Assert.assertEquals(status, true);
		
	}
	
	@Test(priority=2, enabled=true,groups= {"Page Elements verification"})
	public void verify_chkflight()
	{
		boolean status = landingpage.verifyChkFlight_tabActive();
		Assert.assertEquals(status, true);
	}
	
	@Test (priority=3, enabled=true,groups= {"Page Elements verification"})
	public void verify_RoundTrip_radioBtn()
	{
		
		boolean status = landingpage.verify_radioBtn_RoundTrip();
		Assert.assertEquals(status, true);
		
	}
	
	@Test(priority=4, enabled=true,groups= {"Page Flight rates verification"})
	public void Print_FlightRates_For_OneStop_NonStop_And_Total_flights()
	{
			landingpage.click_ChkFlight_tabActive();
			landingpage.click_radioBtn_RoundTrip();
			landingpage.click_fromCity();
			press_escape();
			landingpage.click_toCity();
			press_escape();
			landingpage.departure_dropDownClick();
			String Selected_startDate = landingpage.getSelected_StartDateText();
			waitFor_OneSec();
			
			WebElement returnDAte= driver.findElement(By.xpath("//span[text()='RETURN']"));
			landingpage.Click_webElement(returnDAte);
			String returnDate= landingpage.get_ReturnBookingDate(Selected_startDate,Integer.parseInt(prop.getProperty("return_FlightBooking_date"))); // Add 7 days in Departure date
			String returndate_ele= "//div[@class='DayPicker-Day' and @aria-label='"+returnDate+"']";
			WebElement returndate_Element = driver.findElement(By.xpath(returndate_ele));
			waitFor_OneSec();
			returndate_Element.click();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_OneSec();
			landingpage.click_btn_search();
			//deleteBrowserCookies(3);
			System.out.println("********~~~~~~~~~~~~~~~~~~~~~~********");
			System.out.println("Total number of Departure Flights "+prop.getProperty("flight_bookedFrom")+  " are :: " + landingpage.departure_FlightCount()         + " | and Return Flights from "+prop.getProperty("flight_bookedTo")+ " are :: " +  landingpage.Return_FlightCount());
			//deleteBrowserCookies(3);
			System.out.println("Non-Stop Departure Flights        "+prop.getProperty("flight_bookedFrom")+  " are :: " + landingpage.NonStop_Departure_FlightsCount()+ " | and Return Flights from "+prop.getProperty("flight_bookedTo")+ " are :: " +  landingpage.NonStop_return_FlightsCount());
			//deleteBrowserCookies(3);
			System.out.println("One-Stop Departure Flights        "+prop.getProperty("flight_bookedFrom")+  " are :: " + landingpage.OneStop_Departure_FlightsCount()+ " | and Return Flights from "+prop.getProperty("flight_bookedTo")+ " are :: " +  landingpage.OneStop_return_FlightsCount());
			System.out.println("********~~~~~~~~~~~~~~~~~~~~~~********");
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			//***********************************************//
	}
	@Test(priority=5, enabled=true,groups= {"Page Flight rates verification"})
	public void Validate_Prices_OneStop_flight()
	{
		    
			landingpage.click_ChkFlight_tabActive();
			landingpage.click_radioBtn_RoundTrip();
			landingpage.click_fromCity(); //Click and select city for departure flight
			press_escape();
			landingpage.click_toCity();   //Click and select city for Return flight	
			press_escape();
			landingpage.departure_dropDownClick();
			String Selected_startDate = landingpage.getSelected_StartDateText();
			waitFor_TwoSec();
			
			WebElement returnDAte= driver.findElement(By.xpath("//span[text()='RETURN']"));
			landingpage.Click_webElement(returnDAte);
			//String returnDate= landingpage.get_ReturnBookingDate(Selected_startDate,7); //Integer.parseInt(prop.getProperty("return_FlightBooking_date")))
			String returnDate= landingpage.get_ReturnBookingDate(Selected_startDate,Integer.parseInt(prop.getProperty("return_FlightBooking_date"))); // Add 7 days in Departure date
			String returndate_ele= "//div[@class='DayPicker-Day' and @aria-label='"+returnDate+"']";
			System.out.println("returndate_ele xpath : " + returndate_ele);
			WebElement returndate_Element = driver.findElement(By.xpath(returndate_ele));
			waitFor_TwoSec();
			
			landingpage.Click_webElement(returndate_Element);
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			landingpage.click_btn_search();
			landingpage.validate_FlightsRates_WithFooter_Prices();	
			//***********************************************//
	}
	@Test(priority=6, enabled=true,groups= {"Page Flight rates verification"})
	public void Validate_Prices_For_Top10_Flights()
	{
		landingpage.click_ChkFlight_tabActive();
		landingpage.click_radioBtn_RoundTrip();
		landingpage.click_fromCity();
		press_escape();
		landingpage.click_toCity();
		press_escape();
		landingpage.departure_dropDownClick();
		String Selected_startDate = landingpage.getSelected_StartDateText();
		waitFor_OneSec();
		
		WebElement returnDAte= driver.findElement(By.xpath("//span[text()='RETURN']"));
		landingpage.Click_webElement(returnDAte);
		String returnDate= landingpage.get_ReturnBookingDate(Selected_startDate,Integer.parseInt(prop.getProperty("return_FlightBooking_date"))); // Add 7 days in Departure date
		String returndate_ele= "//div[@class='DayPicker-Day' and @aria-label='"+returnDate+"']";
		WebElement returndate_Element = driver.findElement(By.xpath(returndate_ele));
		waitFor_OneSec();
		returndate_Element.click();
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
		waitFor_OneSec();
		landingpage.click_btn_search();
		waitFor_OneSec();
		landingpage.varifyFlightDeatils(10);    //  Check 10 flights rates and compare with footer Total flight rates
		//***********************************************//
	}
	
	
	@AfterMethod
	public void exitsetup()
	{
		driver.quit();
	}
	
	
}
