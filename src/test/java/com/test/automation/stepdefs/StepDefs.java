package com.test.automation.stepdefs;


import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.stepdefs.StepDefs;
import com.test.automation.pageobjects.CmnPageObjects;
import com.test.automation.pageobjects.SocialMediaPageObjects;
import com.test.automation.pageobjects.searchPageObjects;
import com.test.automation.core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {

	//***********************Logger Init*************************************
	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	//***********************Declaration*************************************
	WebDriver driver;
	String base_url = "http://automationpractice.com";
	String redirected_url = "http://automationpractice.com/index.php";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn;
	WebDriverWait wait;
	//***********************Page Object Model Declaration*******************
	CmnPageObjects cmnPageObjects;
	searchPageObjects searchProdObjects;
	SocialMediaPageObjects socialMediaPageObjects;

	//***********************HOOKS*******************************************

	@Before
	public void setUp(Scenario scn) throws Exception
	{
		this.scn=scn;

		//Get the browser name by default it is chrome
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");
		wait = new WebDriverWait(driver,30);

		//Init Page Object Model Objects
		cmnPageObjects = new CmnPageObjects(driver);
		searchProdObjects = new searchPageObjects(driver);
		socialMediaPageObjects = new SocialMediaPageObjects(driver);
	}

	@After(order=1)
	public void cleanUp()
	{
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
	}

	//screenshot capturing
	@After(order=2)
	public void takescreenShot(Scenario s)
	{
		if (s.isFailed()) 
		{
			TakesScreenshot scrnShot = ((TakesScreenshot)driver);
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data,"image/png","Failed Step name : "+s.getName());
		} 
		else 
		{
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	//***********************Step Defs***************************************
	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url()
	{
		WebDriverFactory.navigateToTheUrl(base_url);
		scn.log("Browser navigated to URL: " + base_url);

		String expected = "My Store";
		cmnPageObjects.validatePageTitleMatch(expected);
	}

	@When("User redirected to another url")
	public void user_redirected_to_another_url() 
	{
		WebDriverFactory.redirectedToTheUrl(redirected_url);
		scn.log("Browser redirected to URL: " + redirected_url);
	}

	@Then("Result Page is Displayed")
	public void result_page_is_displayed() 
	{
		String expectedUrl = "http://automationpractice.com/index.php";
		cmnPageObjects.validateCurrentUrl(expectedUrl);
	}

	@Then("logo is displayed")
	public void logo_is_displayed()
	{
		cmnPageObjects.validationOfdimensionOfLogo();
		scn.log("logo dimensions matched");
	}

	@Then("product category list is displayed")
	public void product_category_list_is_displayed()
	{
		cmnPageObjects.validationOfProductList();
		scn.log("product category list matched");
		scn.log("product count matched");

	}

	@When("User Search for product {string}")
	public void user_search_for_product(String productName)
	{
		searchProdObjects.searchProduct(productName);
		scn.log("search for product : "+productName);
	}

	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() 
	{
		searchProdObjects.validateSearchProductSuccessful();
	}

	@When("User click on the twitter link at footer section of a page")
	public void user_click_on_the_twitter_link_at_footer_section_of_a_page() 
	{
		socialMediaPageObjects.clickOnTheTwitterIcon();
		scn.log("clicked on twitter link at footer section of page");
	}
	@Then("User navigated to twitter page")
	public void user_navigated_to_twitter_page() throws InterruptedException
	{
		
		socialMediaPageObjects.validationOfTwitterNavigatedUrl();
		scn.log("twitter url navigated");
	}
	@Then("Details of user account displayed")
	public void details_of_user_account_displayed() 
	{
		socialMediaPageObjects.validationOfAccountName();
		scn.log("Twitter Account Name of User matched");
	}





}
