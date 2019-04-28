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
	
	@FindBy(xpath="//*[@id=\"webklipper-publisher-widget-container-notification-close-div\"]")WebElement advertisment;
	@FindBy(xpath="//*[@id=\"webklipper-publisher-widget-container-notification-container\"]//iframe") WebElement iframe;
	
	
	
	
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
	
	public void close_advertisement()
	{
		
		
		System.out.println("close_advertisement()");
		try 
		{
			Thread.sleep(1000);
			driver.switchTo().frame(iframe);
			Click_webElement(advertisment);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public void TOP10_Departure_flightPrice(int numberOFTop10Flights)
	{
		System.out.println("inside TOP10_Departure_flightPrice().");
		driver.navigate().refresh();
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
		highLightElement(driver,txtDeparture_Flight,prop);
		int dptFlight_count = departure_flights.size();
		
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait1 = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
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
	
	/**
	 * @author bharat
	 * @param locator
	 * 
	 * Click on provied locator, if selenium click is failed then use Java script Executor for click the element again.
	 */
	public void Click_locator(String locator)
	{
		waitFor_OneSec();
		WebElement ele=driver.findElement(By.xpath(locator));
		try
		{
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			WebElement wait = new WebDriverWait(driver,Integer.parseInt(prop.getProperty("explicitWait"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
			highLightElement(driver,ele,prop);
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_OneSec();
			ele.click();
			
		}
		catch(Exception e)
		{
			try
			{
				
				JavaScriptUtil.clickElementByJS(ele,driver);
				System.out.println("JavaScriptExecutor click performed");
			}
			catch(Exception ex)
			{
				System.out.println("Exception, Try to click by using JavaScriptExecutor");
			}
			
		}
		
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
		
	}
	
	/**
	 * @author bharat
	 * @param ele
	 * @
	 * Click Operation, if selenium click is failed then use Java script Executor for click the element again.
	 */
	public void Click_webElement(WebElement ele)
	{
		waitFor_OneSec();
		try
		{
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			WebElement wait = new WebDriverWait(driver,Integer.parseInt(prop.getProperty("explicitWait"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
			highLightElement(driver,ele,prop);
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_OneSec();
			ele.click();
			
		}
		catch(Exception e)
		{
			try
			{
				JavaScriptUtil.clickElementByJS(ele,driver);
				System.out.println("JavaScriptExecutor click performed");
			}
			catch(Exception ex)
			{
				System.out.println("Exception, Try to click by using JavaScriptExecutor");
			}
			
		}
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	}
	

	


	public void click_radioBtn_RoundTrip()
	{
		Click_webElement(radioBtn_RoundTrip);
	}
	
	public void Click_ON_RadioBtn(String locator)
	{
		Click_locator(locator);
	}
	
	public void click_ChkFlight_tabActive()
	{
		Click_webElement(checkFlights);
	}
	public void Click_ChkFlight_tabActive()
	{
		Click_webElement(checkFlights);
	}
	public void click_btn_search()
	{
		Click_webElement(btn_search);			
	}

	public void departure_dropDownClick()
	{
		Click_webElement(departure_dropdown);
	}
	
	public void return_dropDownClick()
	{
		Click_webElement(returnDate_dropdwon);

	}
	public void click_fromCity()
	{
		Click_webElement(fromCity);
		String fromCity=prop.getProperty("flight_bookedFrom").toLowerCase();
		selectCity_FromCity(fromCity);
		selectCity(fromCity);
	}
	
	public void click_toCity()
	{
		Click_webElement(toCity);
		waitFor_OneSec();
		String ToCity=prop.getProperty("flight_bookedTo").toLowerCase();
		selectCity_toCity(ToCity);
		selectCity(ToCity);
	
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
	 * @author bharat
	 * @param locator
	 * @return
	 * 
	 * This methods will return the Rate of flight
	 */
	
	public String get_Flight_Rates(String locator)
	{
		System.out.println("rget_Flight_Rates xpath is : " + locator);
		WebElement ele=driver.findElement(By.xpath(locator));
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
		highLightElement(driver,ele,prop);
		return ele.getText();
		
	}
	
	
	/**
	 * 
	 * @param str
	 * @return
	 * 
	 * This method will split passed string, then it will convert string value to Integer
	 */
	public static int StringToInteger_conversion(String str)
	{
		String st= str.trim();
		String b[]= st.split(" ");
		int i = Integer.parseInt(str.replaceAll(",", ""));
		System.out.println(i);
		return i;
	}
	
	/**
	 * @author bharat
	 * @param str
	 * @return
	 * 
	 * This method remove comma from Provided string and convert it to Integer
	 */
	public static int StringToInteger_conversion1(String str)//7,345
	{
		String st= str.trim();
		int i = Integer.parseInt(str.replaceAll(",", ""));
		System.out.println(i);
		return i;
	}

	/**
	 * @author bharat
	 * @param str
	 * @return
	 * 
	 * This method parse a paragraph of string and find the Flight rate, and convert it to Interger value
	 */
	public static int getPrice(String str)
	{
		//System.out.println(" Array of String, Extract Last Amount value : /n " +  str );
		System.out.println("");
		System.out.println("");
		String[] sp=str.split(" ");
		int int_price=0;
		for(int i=0;i<sp.length;i++)
		{
			if(sp[i].contains("Rs"))
			{
				String val1=sp[i+1];
				//System.out.println(" Array Length of String, after spliting is  : " + sp.length + "  and last array value is : " + val);
				int_price= parse_alphaNumeric_value(val1);
				//System.out.println("Converted LAST Array Value = "+ val +" TO Integer is  : " + int_price);
				break;
			}
		}
		return int_price;
	}
	
	/**
	 * 
	 * @param st
	 * @return
	 * 
	 * This method will return Numeric value only (i.e st='Rs 8,074 Zero' , return value is = 8074 
	 * 
	 */
	public static int parse_alphaNumeric_value(String st)
	{
		
		StringBuffer bf= new StringBuffer(st);
		//StringBuffer bf= new StringBuffer("Rs 8,074 Zero");
		for(int i=0;i<bf.length();i++)
		{
			
			if(bf.charAt(i)<48 || bf.charAt(i)>57)
			{
				
				bf.deleteCharAt(i);
				i--;
			}
			
		}
		//System.out.println("removed alfa numeric values : " + bf.toString());
		int val = Integer.parseInt(bf.toString());
		
		return val;

	}
	
	/**
	 * @author bharat
	 * @param noOfFlights
	 * 
	 * This Methods will validate the Flight rates with footer mentioned flight rates, with mentioned above listed prices.
	 */
	public void varifyFlightDeatils(int noOfFlights)
	{
			scrollLooping(3);
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_fourSec();
			List<WebElement> departure_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']//div[@class='fli-list splitVw-listing']"));
				//System.out.println(" Departure flights count is : " + departure_flights_List.size());
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			List<WebElement> returns_flights_List = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-right']//div[@class='fli-list splitVw-listing']"));
			//System.out.println(" Return flights count is  : " + returns_flights_List.size());
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));	
		for(int i = 0 ; i< noOfFlights ; i++)
		{
			System.out.println("------------------------------------------");
			System.out.println((i+1) + ". Flight data Verification ______");
			System.out.println("------------------------------------------");
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			int Dep_price_is= getPrice(departure_flights_List.get(i).getText());//7183
			int Ret_price_is= getPrice(returns_flights_List.get(i).getText());
			int sum_of_Amt_Dep_and_Return_is = Dep_price_is + Ret_price_is;
			waitFor_TwoSec();
			Click_webElement(departure_flights_List.get(i));
			Click_webElement(returns_flights_List.get(i));
			String footer_dep_rat=footer_departureFlight_rate.getText();
			String footer_ret_rate	=footer_ReturnFlight_rate.getText();
			String footer_total_fare=Footer_total_RoundTrip_Fare.getText();
			int footer_dep_rate_int= parse_alphaNumeric_value(footer_dep_rat);
			int footer_ret_rate_int= parse_alphaNumeric_value(footer_ret_rate);
			int footer_total_fare1=  parse_alphaNumeric_value(footer_total_fare);
			validate_Data(Dep_price_is,footer_dep_rate_int,"Departure ");
			validate_Data(Ret_price_is,footer_ret_rate_int,"Return ");
			validate_Data(sum_of_Amt_Dep_and_Return_is,footer_total_fare1,"Sum of Departure & Return");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
		}

	}
	
	/**
	 * @author bharat
	 * @param dep_rate
	 * @param foorer_dep
	 * @param srt
	 * 
	 * This method Compare the prices of flights.
	 */
	public static void validate_Data(int dep_rate,int foorer_dep,String srt)
	{
		try
		{
			if(dep_rate==foorer_dep)
			{
				System.out.println(srt + " Flight rate = "+dep_rate+" , EQUAL'S to Footer Mentioned rate = " +foorer_dep);
			}
			else
			{
				System.out.println(srt+" Flight rate = "+dep_rate+" , NOT EQUAL'S to Footer Mentioned rate = " +foorer_dep);
			}

		}
		catch(Exception ex)
		{
			System.out.println("Excption occuring in comparing "+dep_rate+" Flight mentioned with "+foorer_dep+" flight rate mentioned.ex1");
		}
	}
	
	/**
	 * @author bharat
	 * @param numberOFTop10Flights
	 * 
	 * This method is used to Validate Departure flight Rate and return Flight rate, with Total Fare as well
	 */
	public void validate_FlightsRates_WithFooter_Prices()
	{
		WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
		highLightElement(driver,txtDeparture_Flight,prop);
		int dptFlight_count = departure_flights.size();
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait1 = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		int ReturnFlight_count = Return_flights.size();
		for(int i=0;i<1;i++)
		{	
						waitFor_OneSec();
						// Converts Stings to Integer		
						int dep_rate          = parse_alphaNumeric_value(departure_flights_rates.get(i).getText()); //Rs 7,532 TO 7532
						int ret_rate          = parse_alphaNumeric_value(return_flights_rates.get(i).getText()); 	
						int footer_dep_rate   = parse_alphaNumeric_value(footer_departureFlight_rate.getText()); 	
						int footer_ret_rate   = parse_alphaNumeric_value(footer_ReturnFlight_rate.getText()); 	    
						int footer_total_fare = parse_alphaNumeric_value(Footer_total_RoundTrip_Fare.getText());    
						int calulated_Total_fare = dep_rate + ret_rate; // Calculate Sum from list mentioned rate
						validate_Data(dep_rate,footer_dep_rate,"Departure ");
						validate_Data(ret_rate,footer_ret_rate,"Return ");
						validate_Data(calulated_Total_fare,footer_total_fare,"Sum of Departure & Return");
		}
		try 
		{
			System.out.println("Flight discounted Price : " + Footer_discounted_RoundTrip_price.getText());	
		}
		catch(Exception e)
		{
			
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}
	
	
	
	public void validate_TOP10_Return_flightPrice()
	{
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
		explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
		highLightElement(driver,txtReturn_Flight,prop);
		waitFor_OneSec();
		deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
		int ReturnFlight_count = Return_flights.size();
    	
	}
	
	
	 public int departure_FlightCount()
	    {
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtDeparture_Flight));
			highLightElement(driver,txtDeparture_Flight,prop);
			waitFor_OneSec();  
			scrollLooping(20);
		    waitFor_fourSec();
			int dptFlight_count = departure_flights.size();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	return dptFlight_count;
	    	
	    }
	    
	    public int Return_FlightCount()
	    {
	    	deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
			highLightElement(driver,txtReturn_Flight,prop);
			waitFor_OneSec();
			int ReturnFlight_count = Return_flights.size();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	return ReturnFlight_count;
	    	
	    }
	    
	    public int NonStop_Departure_FlightsCount()
	    {
	    	deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	driver.navigate().refresh();
	    	deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	waitFor_TwoSec();
		
	    	explicitWait(driver,Non_Stop,Integer.parseInt(prop.getProperty("explicitWait")));
	    	highLightElement(driver,Non_Stop,prop);
	    	Non_Stop.click();
	    	//return Non_Stop.isDisplayed();
	    	waitFor_OneSec();
       
	    	scrollLooping(20);
        
	    	deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	return departure_flights.size();
	    }
		public int NonStop_return_FlightsCount()
		{	
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
			highLightElement(driver,txtReturn_Flight,prop);
			waitFor_OneSec();
			int ReturnFlight_count = Return_flights.size();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
	    	return ReturnFlight_count;
		}
	
		public int OneStop_Departure_FlightsCount()
		{
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			driver.navigate().refresh();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_TwoSec();
			
			explicitWait(driver,One_Stop,Integer.parseInt(prop.getProperty("explicitWait")));
			highLightElement(driver,One_Stop,prop);
			One_Stop.click();
			waitFor_OneSec();
		  
			scrollLooping(20);
			
		    waitFor_fourSec();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			return departure_flights.size();
		}
		public int OneStop_return_FlightsCount()
		{
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			explicitWait(driver,txtReturn_Flight,Integer.parseInt(prop.getProperty("explicitWait")));
	    	WebElement wait = new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(txtReturn_Flight));
			highLightElement(driver,txtReturn_Flight,prop);
			waitFor_OneSec();
			int ReturnFlight_count = Return_flights.size();
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
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
	

		
		public boolean verificationElement_Exists_oR_Not(WebElement ele)
		{
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			WebElement wait = new WebDriverWait(driver,Integer.parseInt(prop.getProperty("explicitWait"))).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(ele));
			highLightElement(driver,ele,prop);
			deleteBrowserCookies((Integer.parseInt(prop.getProperty("browserDeleteCookies_loopCount"))));
			waitFor_OneSec();
			return ele.isDisplayed();
		}
	
		public boolean verifyMMT_logo()
		{
			return verificationElement_Exists_oR_Not(mmtLogo);	
		}
		public boolean verifyChkFlight_tabActive()
		{
			return verificationElement_Exists_oR_Not(checkFlights);	
		}

		public boolean verify_radioBtn_RoundTrip()
		{
			return verificationElement_Exists_oR_Not(radioBtn_RoundTrip);	
		}

		/**
		 * 
		 */
		

		
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
		 * @author bharat
		 * @return, 
		 * get list of webElements
		 */
		public List<WebElement> suggestions_dropdownListOptions()
		{
			
			List<WebElement> li = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
			return li;
			
		}
		
		
		/**
		 * 
		 * @param city
		 */
		
		public void selectCity(String city)
		{
			List<WebElement> options = suggestions_dropdownListOptions();
			waitFor_OneSec();
			for(WebElement li:options)
			{
				String list_val = null;
				list_val = li.getText().toLowerCase(); //"Java8 makes Java more powerful"; 
				boolean isFound = list_val.contains(city); // true
				if(isFound==true)
				{
					//System.out.println("Found value in position : " + li);
					li.click();
					waitFor_OneSec();
					break;
				}
			}
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
			
		    //System.out.println("monthName :: " + monthname);
			
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
	
	
	
	   
	
	
	

}
