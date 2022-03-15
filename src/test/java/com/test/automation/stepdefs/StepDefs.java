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
import org.junit.Assert;

public class StepDefs {

	//***********************Logger Init*************************************
	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	//***********************Declaration*************************************
	WebDriver driver;
	String base_url = "http://automationpractice.com";
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

	}

	@Then("Launched url redirected to {string}")
	public void launched_url_redirected_to(String ExpectedUrl) 
	{
		String ActualUrl = driver.getCurrentUrl();
		Assert.assertEquals(ExpectedUrl, ActualUrl);
		scn.log("Launched url redirected to : "+ActualUrl);

	}
	@Then("The Application logo is displayed")
	public void the_application_logo_is_displayed()
	{
		cmnPageObjects.validationOfLogoIsDisplayed();
		scn.log("Application logo is displayed");
	}

	@Then("The application logo width is {int} & height is {int}")
	public void the_application_logo_width_is_height_is(Integer width, Integer height) 
	{
		cmnPageObjects.validationOfdimensionOfLogo(width,height);
		scn.log("width and height of application is "+width+" & "+height);
	}

	@Then("Main product categories count should be {int}")
	public void main_product_categories_count_should_be(Integer count) 
	{
		cmnPageObjects.validationOfProductListCount(count);
		scn.log("Count of products is "+count);
	}

	@Then("Displayed text should be {string}")
	public void displayed_text_should_be(String prodName)
	{
		cmnPageObjects.validationOfProductText(prodName);
		scn.log("product text matched");
	}


	@When("User Search for product {string}")
	public void user_search_for_product(String productName)
	{
		searchProdObjects.searchProduct(productName);
		scn.log("search for product : "+productName);
	}


	@Then("The search result contains {string} as text")
	public void the_search_result_contains_as_text(String searchResultText) 
	{

		searchProdObjects.validateSearchProductSuccessful(searchResultText);
		scn.log("search result contains text "+searchResultText);
	}

	@When("User clicks on the twitter link form footer section form landing page of the application")
	public void user_clicks_on_the_twitter_link_form_footer_section_form_landing_page_of_the_application()
	{
		socialMediaPageObjects.clickOnTheTwitterIcon();
		scn.log("clicked on twitter link at footer section of page");
	}

	@Then("The url opened on a new tab contains {string}")
	public void the_url_opened_on_a_new_tab_contains(String urlString) 
	{
		socialMediaPageObjects.validationOfTwitterNavigatedUrl(urlString);
		scn.log("twitter url navigated and contains "+urlString);
	}

	@Then("The twitter account name is {string}")
	public void the_twitter_account_name_is(String AccountName) 
	{
		socialMediaPageObjects.validationOfAccountName(AccountName);
		scn.log("Twitter Account Name of User matched");
	}



}
