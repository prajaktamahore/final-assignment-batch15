package com.test.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.automation.core.WebDriverFactory;

public class SocialMediaPageObjects {

	private static final Logger Logger = LogManager.getLogger(SocialMediaPageObjects.class);
	WebDriver driver;

	private By TwitterAccName = By.xpath("(//span[text()='Selenium Framework'])[2]");
	private By TwitterIcon = By.xpath("//span[text()='Twitter']//parent::a");

	public SocialMediaPageObjects(WebDriver driver) 
	{
		this.driver = driver;
	}

	public void clickOnTheTwitterIcon() 
	{
		WebElement twitterLink = driver.findElement(TwitterIcon);
		twitterLink.click();
		Logger.info("Click on twitter icon at footer section of home page");
	}

	public void validationOfTwitterNavigatedUrl(String ExpectedTextContainsInUrl) 
	{
		WebDriverFactory.browserSwitchToTab();

		// Wait used because firefox give CurrentUrl as blank where chrome successfully executed
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.urlContains(ExpectedTextContainsInUrl));

		String CurrentUrlTwitterPage = driver.getCurrentUrl();
		System.out.println("Actual Url : "+CurrentUrlTwitterPage);

		if(CurrentUrlTwitterPage.contains(ExpectedTextContainsInUrl))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		Logger.info("Twitter Url matched");
	}

	public void validationOfAccountName(String ExpectedAccountName) 
	{
		String ActualtwitterAccountName = driver.findElement(TwitterAccName).getText();
		System.out.println("UserName : "+ActualtwitterAccountName);

		Assert.assertEquals(ExpectedAccountName,ActualtwitterAccountName);

		Logger.info("User Account name matched -"+ActualtwitterAccountName);
	}
}
