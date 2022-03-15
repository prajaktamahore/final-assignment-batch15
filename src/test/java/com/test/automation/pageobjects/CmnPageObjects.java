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
	private By prodlist = By.xpath("//div[@id='block_top_menu']/ul/li");

	public CmnPageObjects(WebDriver driver) 
	{
		this.driver = driver;
	}

	public void validateCurrentUrl(String ExpectedUrl)
	{
		Assert.assertEquals(ExpectedUrl,driver.getCurrentUrl());
		Logger.info("Current Url matched : "+ExpectedUrl);
	}

	public void validationOfLogoIsDisplayed()
	{
		boolean b = driver.findElement(logo).isDisplayed();
		Assert.assertEquals(true,b);
		Logger.info("Logo is displayed");
	}

	public void validationOfdimensionOfLogo(int w,int h)
	{
		int height = driver.findElement(logo).getSize().getHeight();
		int width = driver.findElement(logo).getSize().getWidth();
		Assert.assertEquals(h,height);
		Assert.assertEquals(w,width);

		Logger.info("Dimension of logo matched "+width+" & "+height);
	}

	public void validationOfProductListCount(int count)
	{
		List<WebElement> productCategoryList = driver.findElements(prodlist);
		int prodCount = productCategoryList.size();
		System.out.println(prodCount);

		Assert.assertEquals(count, prodCount);
		Logger.info("count of product in list matched : "+prodCount);
	}

	public void validationOfProductText(String text)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		List<WebElement> productCategoryList1 = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(prodlist)));

		if(productCategoryList1.get(0).getText().equals(text))
		{
			Assert.assertTrue(true);
			Logger.info("Product WOMEN text matched ");
		}
		else if(productCategoryList1.get(1).getText().equals(text))
		{
			Assert.assertTrue(true);
			Logger.info("Product DRESSES text matched ");
		}
		else if(productCategoryList1.get(2).getText().equals(text))
		{
			Assert.assertTrue(true);
			Logger.info("Product T-SHIRTS text matched ");
		}
		else
		{
			Assert.fail();
		}
	}
}
