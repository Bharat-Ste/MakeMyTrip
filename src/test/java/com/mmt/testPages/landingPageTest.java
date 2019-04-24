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
	
	@Test(priority = 1, enabled=false)
	public void verifyMMT_Logo()
	{
		boolean status = landingpage.verifyMMT_logo();
		Assert.assertEquals(status, true);
		
	}
	
	@Test(priority=2, enabled=false)
	public void verify_chkflight()
	{
		boolean status = landingpage.verifyChkFlight_tabActive();
		Assert.assertEquals(status, true);
	}
	
	@Test (priority=3, enabled=false)
	public void verify_RoundTrip_radioBtn()
	{
		
		boolean status = landingpage.verify_radioBtn_RoundTrip();
		Assert.assertEquals(status, true);
		
	}
	
	@Test(priority=4, enabled=true)
	public void bookFlightTicket()
	{
		
		landingpage.click_ChkFlight_tabActive();
			waitFor_TwoSec();
		landingpage.click_radioBtn_RoundTrip();
			waitFor_fourSec();
		
		//Click on From city input text
			landingpage.click_fromCity();
			String fromCity=prop.getProperty("flight_bookedFrom").toLowerCase();
			landingpage.selectCity_FromCity(fromCity);
			landingpage.selectCity(fromCity);
			
		//***********************************************//	
			press_escape();
		//***********************************************//	

			landingpage.click_toCity();
			String ToCity=prop.getProperty("flight_bookedTo").toLowerCase();
//			System.out.println("ToCity : " +ToCity );
			landingpage.selectCity_toCity(ToCity);
			landingpage.selectCity(ToCity);
			
		//***********************************************//	
			press_escape();
		//***********************************************//	
			
			landingpage.departure_dropDownClick();
			String Selected_startDate = landingpage.getSelected_StartDateText();
	
			String returnDate= landingpage.get_ReturnBookingDate(Selected_startDate,7);
			String returndate_ele= "//div[@class='DayPicker-Day' and @aria-label='"+returnDate+"']";
			 
			
			WebElement returndate_Element = driver.findElement(By.xpath(returndate_ele));
			returndate_Element.click();
			landingpage.click_btn_search();
			
			
			System.out.println("********~~~~~~~~~~~~~~~~~~~~~~********");
			System.out.println("Total number of Departure Flights "+fromCity+  " are :: " + landingpage.departure_FlightCount()         + " | and Return Flights from "+ToCity+ " are :: " +  landingpage.Return_FlightCount());          
			System.out.println("Non-Stop Departure Flights        "+fromCity+  " are :: " + landingpage.NonStop_Departure_FlightsCount()+ " | and Return Flights from "+ToCity+ " are :: " +  landingpage.NonStop_return_FlightsCount());
			System.out.println("One-Stop Departure Flights        "+fromCity+  " are :: " + landingpage.OneStop_Departure_FlightsCount()+ " | and Return Flights from "+ToCity+ " are :: " +  landingpage.OneStop_return_FlightsCount());
			System.out.println("********~~~~~~~~~~~~~~~~~~~~~~********");

			landingpage.uncheck_OneStop_RadioBtn();
			
			waitFor_fourSec();
			
			//landingpage.varifyFlightDeatils(10);
			landingpage.validate_FlightsRates_WithFooter_Prices();

	}
	@Test(priority=5, enabled=false)
	public void dateSelect()
	{
		
		landingpage.click_ChkFlight_tabActive();
		waitFor_TwoSec();
		landingpage.click_radioBtn_RoundTrip();
		waitFor_fourSec();
		landingpage.click_btn_search();
		
		landingpage.varifyFlightDeatils(10);
		
	}
	
	
	@AfterMethod
	public void exitsetup()
	{
		driver.quit();
	}
	
	
}
