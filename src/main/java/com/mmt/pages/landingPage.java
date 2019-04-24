package com.mmt.pages;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmt.baseClass.baseclass;
import com.mmt.utilities.JavaScriptUtil;



public class landingPage extends baseclass
{

	
	// PageFectory
	
	
	@FindBy(xpath="//input[@id='fromCity']") WebElement fromCity;
	@FindBy(xpath="//input[@placeholder='From']") WebElement inputTxt_fromCity;
	@FindBy(xpath="//input[@id='toCity']") WebElement toCity;
	@FindBy(xpath="//input[@placeholder='To']") WebElement inputTxt_toCity;
	@FindBy(xpath="//span[contains(text(),'DEPARTURE')]") WebElement dropdown_DEPARTURE;
	@FindBy(xpath="//span[contains(text(),'RETURN')]") WebElement dropdown_RETURN;
	@FindBy(xpath="//*[@id=\"SW\"]/div[1]/div[1]/a/img") WebElement mmtLogo;
	@FindBy(xpath="//span[@class='chNavIcon appendBottom2 chSprite chFlights active']") WebElement checkFlights; 
	@FindBy(xpath="//li[text()='Round Trip']") WebElement radioBtn_RoundTrip;
	@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']/li") public List<WebElement> fromCity_Suggestionlist;

	@FindBy(xpath="//div[@class='DayPicker-Day DayPicker-Day--start DayPicker-Day--selected']") WebElement selected_startDate;
	@FindBy(xpath="//div[@class='DayPicker-Day DayPicker-Day--end DayPicker-Day--selected']") WebElement selected_endDate;
	@FindBy(xpath="//span[text()='DEPARTURE']") WebElement departure_dropdown;
	@FindBy(xpath="//input[@id='return']") WebElement returnDate_dropdwon;
	
	@FindBy(xpath="//a[contains(text(),'Search')]") WebElement btn_search;
	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//input") public List<WebElement> departure_flights;
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//input") public List<WebElement> Return_flights;
	@FindBy(xpath="//p[contains(text(),'Departure Flight')]") WebElement txtDeparture_Flight;
	@FindBy(xpath="//p[contains(text(),'Return Flight')]") WebElement txtReturn_Flight;
	@FindBy(xpath="//span[@class='checkbox-group list1 append_bottom5 fli-filter-items']//label[@for='filter_stop0']") WebElement Non_Stop;
	@FindBy(xpath="//span[@class='checkbox-group list1 append_bottom5 fli-filter-items']//label[@for='filter_stop1']") WebElement One_Stop;

	@FindBy(xpath="//div[@class='splitVw-sctn pull-left']//p[@class='actual-price']") List<WebElement> departure_flights_rates;
	@FindBy(xpath="//div[@class='splitVw-sctn pull-right']//p[@class='actual-price']") List<WebElement> return_flights_rates;
	
	@FindBy(xpath="//div[@class='splitVw-footer-left ']//p[@class='actual-price']")WebElement  footer_departureFlight_rate;
	@FindBy(xpath="//div[@class='splitVw-footer-right ']//p[@class='actual-price']")WebElement footer_ReturnFlight_rate;
	
	@FindBy(xpath="//span[@class='splitVw-total-fare']//span") WebElement Footer_discounted_RoundTrip_price;
	@FindBy(xpath="//span[@class='splitVw-total-fare']//span") WebElement Footer_total_RoundTrip_Fare;
	
	@FindBy(xpath="")WebElement dep_raio_btn;
	
	
	
	
	
	public landingPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void scrollLooping(int loopCount)
	{
		try
		{
			for(int i=0;i<loopCount;i++)
			{
				JavaScriptUtil.scrollPageDown(driver);
			}
		}
		catch(Exception e)
		{
			System.out.println(" scroll looping catchblock()  ");
		}
		
		
	}
	
	
	
	public void TOP10_Departure_flightPrice(int numberOFTop10Flights)
	{
		driver.navigate().refresh();
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
		highLightElement(driver,txtDeparture_Flight,prop);
		int dptFlight_count = departure_flights.size();
		
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait1 = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
		
//		System.out.println("departure_flights_rates : " + departure_flights_rates.size());
//		System.out.println("return_flights_rates    : " + return_flights_rates.size());
		
		for(int i=0;i<numberOFTop10Flights;i++)
		{
			System.out.println(i+1 +". Departure rate : " + departure_flights_rates.get(i).getText() + " Return Rate : " + return_flights_rates.get(i).getText());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
	// Footer Prices
		
		System.out.println("Departure Round Trip Prices is :"  + footer_departureFlight_rate.getText());
		System.out.println("Actual    Round Trip Price without Deicount is :" + footer_ReturnFlight_rate.getText());
		System.out.println("Footeer Total Fare      : " + Footer_total_RoundTrip_Fare.getText());
		try 
		{
			System.out.println("Footer Price discounted : " + Footer_discounted_RoundTrip_price.getText());	
		}
		catch(Exception e)
		{
			System.out.println("Footer Dicounted Price not Found !!");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
    	
	}
	
	public void Click_ON_RadioBtn(String locator)
	{
		System.out.println("Click_ON_RadioBtn() , radio button xpath is : " + locator);
		WebElement ele=driver.findElement(By.xpath(locator));
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
		highLightElement(driver,ele,prop);
		ele.click();
		
	}
	
	public void Click_onWebElement_explicitWait(WebElement ele)
	{
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
		highLightElement(driver,ele,prop);
		ele.click();
	}
	
	public String get_Flight_Rates(String locator)
	{
		System.out.println("rget_Flight_Rates xpath is : " + locator);
		WebElement ele=driver.findElement(By.xpath(locator));
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
		highLightElement(driver,ele,prop);
		return ele.getText();
		
	}
	
	public String get_footer_DepratureRate()
	{
		
		return "aa";
	}
	public String get_footer_ReturnRate()
	{
		
		return "aa";
	}
	
	public void varifyFlightDeatils(int noOfFlights)
	{
		
		System.out.println("varifyFlightDeatils");
		
		List<WebElement> departure_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']//div[@class='fli-list splitVw-listing']"));
		System.out.println(" size : " + departure_flights_List.size());
		
		List<WebElement> returns_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-right']//div[@class='fli-list splitVw-listing']"));
		System.out.println(" size : " + returns_flights_List.size());
		
		
		
		for(int i = 0 ; i<noOfFlights ; i++)
		{
			System.out.println(i+ " departure flights are " + departure_flights_List.get(i).getText());
			System.out.println("-------");
			System.out.println(i+ " Return flights are " +returns_flights_List.get(i).getText());
			System.out.println("-------");
			
			driver.navigate().refresh();
			waitFor_TwoSec();
			
			departure_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']//div[@class='fli-list splitVw-listing']"));
			returns_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-right']//div[@class='fli-list splitVw-listing']"));
			waitFor_OneSec();
			Click_onWebElement_explicitWait(departure_flights_List.get(i));
			Click_onWebElement_explicitWait(returns_flights_List.get(i));
			
//			departure_flights_List.get(i).click();
//			returns_flights_List.get(i).click();
			
		}
		
		
		
		
	/*
		 
//		 String dept_radioBtn_preFix = "(//div[@class='splitVw-sctn pull-left']//span[@class='splitVw-inner'])[";
		 String dept_radioBtn_preFix = "(//div[@class='splitVw-sctn pull-left']//span[@class='airlineInfo-sctn'])[";
		 String dept_radioBtn_postFix = "]";
		
		 
		 //String ret_radioBtn_preFix = "(//div[@class='splitVw-sctn pull-right']//span[@class='splitVw-inner'])[";
		 String ret_radioBtn_preFix = "(//div[@class='splitVw-sctn pull-left']//span[@class='airlineInfo-sctn'])[";
		 String ret_radioBtn_postFix = "]";
		 
		 //Dynamic path for Departure & Return flight is
		 String depRate_prefix="(//div[@class='splitVw-sctn pull-left']//p[@class='actual-price'])[";
		 String depRate_postfix="]";
		 
		 String returnRate_prefix="(//div[@class='splitVw-sctn pull-right']//p[@class='actual-price'])[";
		 String returnRate_postfix="]";
		

		 
		 for(int i=1;i<=noOfFlights;i++)
		 {
			 
			 
			 waitFor_TwoSec();
			 // Dynamic radio buttons
			 String dept_radioBtn_finalXpath=dept_radioBtn_preFix+i+dept_radioBtn_postFix;
			 String return_radioBtn_finalXpath=ret_radioBtn_preFix+i+ret_radioBtn_postFix;

			 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			 waitFor_TwoSec();
			 
			 System.out.println(i+" Departure radio button xpath : " + dept_radioBtn_finalXpath);
			 System.out.println(i+" return    radio button xpath : " + return_radioBtn_finalXpath);
			// Click on Radio buttons
			 
			 Click_ON_RadioBtn(dept_radioBtn_finalXpath);
			 
			 Click_ON_RadioBtn(return_radioBtn_finalXpath);
			 
			 waitFor_TwoSec();
			 
			 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			 //Dynamic Flight rates of departure and return flights
			 String Departure_rate=depRate_prefix+i+depRate_postfix;
			 String Return_rate=returnRate_prefix+i+returnRate_postfix;
			 
			 waitFor_OneSec();
			 
			 System.out.println(i + " departure rate : " + get_Flight_Rates(Departure_rate));
			 System.out.println(i + " departure rate : " + get_Flight_Rates(Return_rate));

			waitFor_TwoSec();
			    
			String footer_dep_rate=footer_departureFlight_rate.getText();
			String footer_ret_rate	=footer_ReturnFlight_rate.getText();
			String footer_total_fare=Footer_total_RoundTrip_Fare.getText();
			System.out.println(i+". footer_dep_rate" +footer_dep_rate); 
			System.out.println(i+". footer_ret_rate" +footer_ret_rate); 
			System.out.println(i+". footer_total_fare" +footer_total_fare); 
			 
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			driver.navigate().refresh();
		 }

		//validate_FlightsRates_WithFooter_Prices();
		*/
		
	}
	
	/**
	 * 
	 * @param numberOFTop10Flights
	 * This method is used to Validate Departure flight Rate and return Flight rate, with Total Fare as well
	 */
	public void validate_FlightsRates_WithFooter_Prices()
	{
		/*
		 * ********~~~~~~~~~~~~~~~~~~~~~~********
			Total number of Departure Flights delhi are :: 74 | and Return Flights from bangalore are :: 73
			********~~~~~~~~~~~~~~~~~~~~~~********
			Non-Stop Departure Flights delhi are :: 28 | and Return Flights from bangalore are :: 36
			********~~~~~~~~~~~~~~~~~~~~~~********
			One-Stop Departure Flights  delhi are :: 40 | and Return Flights from bangalore are :: 33
			********~~~~~~~~~~~~~~~~~~~~~~********
			1. Departure rate : Rs 7,532 Return Rate : Rs 5,660
			2. Departure rate : Rs 10,635 Return Rate : Rs 5,660
			3. Departure rate : Rs 11,008 Return Rate : Rs 6,020
			4. Departure rate : Rs 11,575 Return Rate : Rs 6,155
			5. Departure rate : Rs 11,673 Return Rate : Rs 6,662
			6. Departure rate : Rs 12,021 Return Rate : Rs 6,840
			7. Departure rate : Rs 12,519 Return Rate : Rs 6,840
			8. Departure rate : Rs 12,519 Return Rate : Rs 7,102
			9. Departure rate : Rs 13,149 Return Rate : Rs 7,102
			10. Departure rate : Rs 13,374 Return Rate : Rs 7,102
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			Departure Round Trip Prices is :Rs 7,532
			Actual    Round Trip Price without Deicount is :Rs 5,660
			Footeer Total Fare      : Rs 13,192
			Footer Price discounted : Rs 13,192
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
//driver.navigate().refresh();
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
		highLightElement(driver,txtDeparture_Flight,prop);
		int dptFlight_count = departure_flights.size();
		
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait1 = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
		
//		System.out.println("departure_flights_rates : " + departure_flights_rates.size());
//		System.out.println("return_flights_rates    : " + return_flights_rates.size());
		
		
		
		for(int i=0;i<1;i++)
		{
			String dep_rate="";
			String ret_rate="";
			String footer_dep_rate="";
			String footer_ret_rate="";
			String footer_total_fare="";
			int dep_rate_interger=0;
			int ret_rate_interger=0;
			int footer_dep_rate_interger=0;
			int footer_ret_rate_interger=0;
			int footer_total_fare_interger=0;
			int calulated_Total_fare=0;
			
			System.out.println(i+1 +". Departure rate : " + departure_flights_rates.get(i).getText() + " Return Rate : " + return_flights_rates.get(i).getText());
			dep_rate= departure_flights_rates.get(i).getText();
			ret_rate= return_flights_rates.get(i).getText();
			waitFor_OneSec();
			footer_dep_rate=footer_departureFlight_rate.getText();
			footer_ret_rate	=footer_ReturnFlight_rate.getText();
			footer_total_fare=Footer_total_RoundTrip_Fare.getText();
			
			// Converts Stings to Integer		
			dep_rate_interger = StringToInteger_conversion(dep_rate); //Rs 7,532 TO 7532
			ret_rate_interger = StringToInteger_conversion(ret_rate); //Rs 7,532 TO 7532
			footer_dep_rate_interger = StringToInteger_conversion(footer_dep_rate); //Rs 7,532 TO 7532
			footer_ret_rate_interger = StringToInteger_conversion(footer_ret_rate); //Rs 7,532 TO 7532
			footer_total_fare_interger = StringToInteger_conversion(footer_total_fare); //Rs 7,532 TO 7532
			
			calulated_Total_fare=dep_rate_interger+ret_rate_interger;
			
		    try 
		    {
		    	dep_rate.equalsIgnoreCase(footer_dep_rate);
		    	System.out.println("departure_flight_rate : "+dep_rate+" equals to footer_dep_rate : " +footer_dep_rate);
		    	if(footer_total_fare_interger==calulated_Total_fare)
		    	{
		    		System.out.println(
		    				"departure_flight_rate : "+dep_rate
		    				+" equals to footer_dep_rate : " +footer_dep_rate
		    				+" Footer Mentioned Fare : " + footer_total_fare_interger + " is Equal to Sum of Departure & Return fare : " +calulated_Total_fare
		    				);
		    	}
		    	else
		    	{
		    		System.out.println(
		    				"departure_flight_rate : "+dep_rate
		    				+" equals to footer_dep_rate : " +footer_dep_rate
		    				+" Footer Mentioned Fare" + footer_total_fare_interger + " is NOT Equal to Sum of Departure & Return fare : " +calulated_Total_fare
		    				);
		    	}
		    	
		    }
		    catch(Exception e)
		    {
		    	System.out.println("departure_flight_rate : "+dep_rate+" not equals to footer_dep_rate : " +footer_dep_rate);
		    }
		    try 
		    {
		    	ret_rate.equalsIgnoreCase(footer_ret_rate);
		    	System.out.println("return_flight_rate " + ret_rate +" equals to footer_return_flight_rate :" + footer_ret_rate);
		    	
		    }
		    catch(Exception e1)
		    {
		    	System.out.println("return_flight_rate " + ret_rate +" not equals to footer_return_flight_rate :" + footer_ret_rate);
		    }
			
			
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
	// Footer Prices
		
		System.out.println("Departure Round Trip Prices is :"  + footer_departureFlight_rate.getText());
		System.out.println("Actual    Round Trip Price without Deicount is :" + footer_ReturnFlight_rate.getText());
		System.out.println("Footeer Total Fare      : " + Footer_total_RoundTrip_Fare.getText());
		try 
		{
			System.out.println("Footer Price discounted : " + Footer_discounted_RoundTrip_price.getText());	
		}
		catch(Exception e)
		{
			System.out.println("Footer Dicounted Price not Found !!");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
    	
	}
	
	/**
	 * 
	 * @param str
	 * @return   , It will return a integer value from string
	 * 
	 */
	
	public int StringToInteger_conversion(String str)
	{
		String st= str.trim();
		String b[]= st.split(" ");
		int i = Integer.parseInt(b[1].replaceAll(",", ""));
		System.out.println(i);
		return i;
	}
	
	public void validate_TOP10_Return_flightPrice()
	{
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
    	
	}
	
	
	 public int departure_FlightCount()
	    {
	    	
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
			highLightElement(driver,txtDeparture_Flight,prop);
			waitFor_OneSec();
		        
			scrollLooping(20);
			
		    waitFor_fourSec();
			int dptFlight_count = departure_flights.size();
	    	return dptFlight_count;
	    	
	    }
	    
	    public int Return_FlightCount()
	    {
	    	explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
			highLightElement(driver,txtReturn_Flight,prop);
			waitFor_OneSec();
			int ReturnFlight_count = Return_flights.size();
	    	return ReturnFlight_count;
	    	
	    }
	public int NonStop_Departure_FlightsCount()
	{
		driver.navigate().refresh();
		waitFor_TwoSec();
		
		explicitWait(driver,Non_Stop,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,Non_Stop,prop);
		Non_Stop.click();
		//return Non_Stop.isDisplayed();
        waitFor_OneSec();
       
        scrollLooping(20);
        
        waitFor_fourSec();
		return departure_flights.size();
	}
	public int NonStop_return_FlightsCount()
	{	
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
    	return ReturnFlight_count;
	}
	
	public int OneStop_Departure_FlightsCount()
	{
		driver.navigate().refresh();
		waitFor_TwoSec();
		
		explicitWait(driver,One_Stop,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,One_Stop,prop);
		One_Stop.click();
		waitFor_OneSec();
	  
		scrollLooping(20);
		
	    waitFor_fourSec();
		return departure_flights.size();
	}
	public int OneStop_return_FlightsCount()
	{
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
    	return ReturnFlight_count;
	}
	
	public void uncheck_OneStop_RadioBtn()
	{
		driver.navigate().refresh();
		waitFor_TwoSec();
		
		explicitWait(driver,One_Stop,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,One_Stop,prop);
		One_Stop.click();
		waitFor_OneSec();
	}
	
	
	
	
	
	
   
	
	public boolean verifyMMT_logo()
	{
		explicitWait(driver,mmtLogo,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,mmtLogo,prop);
		return mmtLogo.isDisplayed();
			
	}
	
	public boolean verifyChkFlight_tabActive()
	{
		explicitWait(driver,checkFlights,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,checkFlights,prop);
		return mmtLogo.isDisplayed();
			
	}
	public void click_ChkFlight_tabActive()
	{
		explicitWait(driver,checkFlights,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,checkFlights,prop);
		checkFlights.click();
		
			
	}
	public void Click_ChkFlight_tabActive()
	{
		explicitWait(driver,checkFlights,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,checkFlights,prop);
		checkFlights.click();
		
			
	}
	public boolean verify_radioBtn_RoundTrip()
	{
		explicitWait(driver,radioBtn_RoundTrip,Integer.parseInt(prop.getProperty("explicitWait")));		
		highLightElement(driver,radioBtn_RoundTrip,prop);
		return radioBtn_RoundTrip.isDisplayed();
		
		
	}
	
	public void click_radioBtn_RoundTrip()
	{
		explicitWait(driver,radioBtn_RoundTrip,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,radioBtn_RoundTrip,prop);
		radioBtn_RoundTrip.click();
		
	}
	

	public void click_fromCity()
	{
		explicitWait(driver,fromCity,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,fromCity,prop);
		fromCity.click();
		waitFor_TwoSec();
		
		
		
		
	}
	
	/**
	 * 
	 * @param str , Pass the cityName where you want to book flight
	 */
	
	public void selectCity_FromCity(String str)
	{
		explicitWait(driver,inputTxt_fromCity,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,inputTxt_fromCity,prop);
		inputTxt_fromCity.sendKeys(str);
		
	}
	
	
	/**
	 * 
	 */
	
	public void click_toCity()
	{
		explicitWait(driver,toCity,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,toCity,prop);
		toCity.click();
	
	}
	
	/**
	 * 
	 * @param str , Pass the City name for booking flights
	 */
	public void selectCity_toCity(String str)
	{
	
        explicitWait(driver,inputTxt_toCity,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,inputTxt_toCity,prop);
		inputTxt_toCity.sendKeys(str);
		waitFor_TwoSec();

	}
	
	/**
	 * 
	 * @return, get list of webElements
	 */
	public List<WebElement> suggestions_dropdownListOptions()
	{
		System.out.println("Inside suggestions_dropdownListOptions() method :: ");
		List<WebElement> li = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
		return li;
		
	}
	
	
	/**
	 * 
	 * @param city
	 */
	
	public void selectCity(String city)
	{
	
		
		waitFor_TwoSec();
		List<WebElement> options = suggestions_dropdownListOptions();
//		System.out.println("From City Options : " + options.size());
		
		for(WebElement li:options)
		{

			String list_val = null;
			list_val = li.getText().toLowerCase(); //"Java8 makes Java more powerful"; 
//			System.out.println("list_val : " + list_val);
//			System.out.println("check city value : " +  city);
			boolean isFound = list_val.contains(city); // true
//			System.out.println("isFound found ? : " + isFound);
			
			if(isFound==true)
			{
//				System.out.println("Found value in position : " + li);
				li.click();
				waitFor_fourSec();
				break;
			}
			
		}
	}
	
	
	
	public void departure_dropDownClick()
	{
		
		explicitWait(driver,departure_dropdown,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,departure_dropdown,prop);
		departure_dropdown.click();
		waitFor_TwoSec();
		driver.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--start DayPicker-Day--selected']")).click();

	}
	
	public void return_dropDownClick()
	{
		
		explicitWait(driver,returnDate_dropdwon,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,returnDate_dropdwon,prop);
		returnDate_dropdwon.click();
		waitFor_TwoSec();

	}
	
	public String getSelected_StartDateText()
	{
		explicitWait(driver,selected_startDate,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,selected_startDate,prop);
		selected_startDate.getAttribute("aria-label");
		waitFor_TwoSec();
		return selected_startDate.getAttribute("aria-label");
	}
	public String getSelected_EndDateText()
	{
		explicitWait(driver,selected_endDate,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,selected_endDate,prop);
		selected_endDate.getAttribute("aria-label");
		waitFor_TwoSec();
		return selected_endDate.getAttribute("aria-label");
	}
	
	
	public String get_ReturnBookingDate(String startDate,int bookingGap)
	{
		String sd= startDate;   // day:mm:dd:yyyy
		int gap = bookingGap;
	// Selected Start day in calendar
		String Arr_sd[]=sd.split(" ");
		String sd_month_var=Arr_sd[1];
		int sd_date_var= Integer.parseInt(Arr_sd[2]);
		int sd_year_var= Integer.parseInt(Arr_sd[3]);
		//		System.out.println("Arr_sd month_var : " + sd_month_var);   // Apr 
		//		System.out.println("Arr_sd date_var  : " + sd_date_var);    // 24 
		//		System.out.println("Arr_sd date_var  : " + sd_year_var); 
		String Val= getMonthDays(sd_month_var,sd_year_var);
		String Arr[] = Val.split("_");
		int days_inSpecific_month= Integer.parseInt(Arr[0]);
		String current_month_num = Arr[1];
		String next_month_Is = Arr[2];
		String next_month_num_Is = Arr[3];
		int new_returnDate = sd_date_var+bookingGap;
		//System.out.println(" Return flight date is : " + new_returnDate);
		
		if(days_inSpecific_month<=new_returnDate) //  30 <= 31
		{
			int extra_days = new_returnDate - days_inSpecific_month;  //31-30=1
			String extra_days_st="";
			String input_date="";
			if(extra_days<=9)
			{
				extra_days_st="0"+String.valueOf(extra_days);
			    input_date= extra_days_st+"/" +next_month_num_Is+"/"+sd_year_var;
			}
			else
			{
				 extra_days_st=String.valueOf(extra_days);
				 input_date= extra_days+"/" +next_month_num_Is+"/"+sd_year_var;
			}
			String dayName= dayName(input_date);
			//String input_date="01/08/2012";
			String finalDate_for_Return_is = dayName.substring(0, 3) +" "+next_month_Is +" "+ extra_days_st + " " + sd_year_var ;  //1_May  Required : Wed May 01 2019
			return finalDate_for_Return_is; // Wed May 01 2019
			
		}
		else
		{
			//System.out.println(" Return flight date is : " + new_returnDate);
			//System.out.println(" For the month of "  + sd_month_var + " , days are : "  + days_inSpecific_month);
			String new_rt="";
			String input_date="";
			if(new_returnDate<=9)
			{
				new_rt="0"+String.valueOf(new_returnDate);
				input_date= new_rt+"/" +sd_month_var+"/"+sd_year_var; //"01/08/2012"
			}
			else
			{
				 new_rt=String.valueOf(new_returnDate);
				 input_date= String.valueOf(new_returnDate)+"/" +sd_month_var+"/"+sd_year_var; //"01/08/2012"
			}
			
			String dayName= dayName(input_date);
			
			String finalDate_for_Return_is =  dayName.substring(0, 3)+" "+sd_month_var+" "+new_rt + " "+ sd_year_var ; // //1_May  Required : Wed May 01 2019
			// 
			return finalDate_for_Return_is;
		}
		
		

		
	}
	
	public String getMonthDays(String monthName, int year)
	{
		String monthname=monthName.toLowerCase();
		
	System.out.println("monthName :: " + monthname);
		
		if(monthname.equalsIgnoreCase("Jan"))
		{
			return "31_01_Feb_02";
		}
		else if(monthname.equalsIgnoreCase("Feb"))
		{
			if(year%2==0)
			{
				return "29_02_Mar_03";
			}
			else
			{
				return "28_02_Mar_03";
			}
		}
		else if(monthname.equalsIgnoreCase("Mar"))
		{
			return "31_03_Apr_04";
		}
		else if(monthname.equalsIgnoreCase("Apr"))
		{
			return "30_04_May_05";
		}
		else if(monthname.equalsIgnoreCase("May"))
		{
			return "30_05_Jun_06";
		}
		else if(monthname.equalsIgnoreCase("Jun"))
		{
			return "31_06_Jul_07";
		}
		else if(monthname.equalsIgnoreCase("Jul"))
		{
			return "31_07_Aug_08";
		}
		else if(monthname.equalsIgnoreCase("Aug"))
		{
			return "30_08_Sep_09";
		}
		else if(monthname.equalsIgnoreCase("Sep"))
		{
			return "30_09_Oct_10";
		}
		else if(monthname.equalsIgnoreCase("Oct"))
		{
			return "30_10_Nov_11";
		}
		else if(monthname.equalsIgnoreCase("Nov"))
		{
			return "31_11_Dec_12";
		}
		else if(monthname.equalsIgnoreCase("Dec"))
		{
			return "31_12_Jan_01";
		}
		else
		{
			System.out.println("Month Not found !!");
			return "NA_JNA";
		}
		
		
		
		
		
	}
	
	public String dayName(String date)
	{
	      
		
		  //String input_date="01/08/2012";
		  String input_date=date;
		  SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		  Date dt1 = null;
		  
		  try 
		  {
			  dt1 = format1.parse(input_date);
		  } 
		  catch (ParseException e) 
		  {
		
			e.printStackTrace();
		  }
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		  return finalDay;
	}


   public void click_btn_search()
   {
	    explicitWait(driver,btn_search,Integer.parseInt(prop.getProperty("explicitWait")));
		highLightElement(driver,btn_search,prop);
		btn_search.click();
		waitFor_TwoSec();
   }
	
	
	

}
