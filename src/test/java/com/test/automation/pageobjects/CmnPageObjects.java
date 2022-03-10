package com.test.automation.pageobjects;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.pageobjects.CmnPageObjects;

public class CmnPageObjects
{
	private static final Logger Logger = LogManager.getLogger(CmnPageObjects.class);
	WebDriver driver;

	private By logo = By.xpath("//img[@class='logo img-responsive']");
	private By heightOfLogo  = By.xpath("//img[@class='logo img-responsive']");
	private By widthOfLogo = By.xpath("//img[@class='logo img-responsive']");
	private By prodlist = By.xpath("//div[@id='block_top_menu']/ul/li");

	public CmnPageObjects(WebDriver driver) 
	{
		this.driver = driver;
	}

	public void validatePageTitleMatch(String expectedTitle) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		Logger.info("Page title matched: " + expectedTitle );
	}

	public void validateCurrentUrl(String ExpectedUrl)
	{
		Assert.assertEquals(ExpectedUrl,driver.getCurrentUrl());
		Logger.info("Current Url matched : "+ExpectedUrl);
	}

	public void validationOfdimensionOfLogo()
	{
		boolean b = driver.findElement(logo).isDisplayed();
		Assert.assertEquals(true,b);

		int height = driver.findElement(heightOfLogo).getSize().getHeight();
		int width = driver.findElement(widthOfLogo).getSize().getWidth();
		Assert.assertEquals(height,99);
		Assert.assertEquals(width,350);

		Logger.info("Dimension of logo matched "+height+" "+width);
	}

	public void validationOfProductList()
	{
		List<WebElement> productCategoryList = driver.findElements(prodlist);
		int prodCount = productCategoryList.size();
		System.out.println(prodCount);

		Assert.assertEquals(3, prodCount);
		Logger.info("count of product in list matched : "+prodCount);

		for(int i=0;i<prodCount;i++)
		{
			String ExpectedProductList = "WOMEN"+"DRESSES"+"T-SHIRTS";
			
			String productList = productCategoryList.get(i).getText(); 
			System.out.println(productList);

			
			if (ExpectedProductList.equals(productList)) 
			{
				Assert.assertTrue(true);
				
			} 
			else 
			{
				Assert.assertFalse(false);
			}
		}
		Logger.info("Product List matched");
	}

}
