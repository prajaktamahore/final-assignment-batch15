package com.test.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.test.automation.pageobjects.searchPageObjects;

public class searchPageObjects {

	private static final Logger Logger = LogManager.getLogger(searchPageObjects.class);

	private WebDriver driver;

	private By search_product  = By.xpath("//input[@id='search_query_top']");
	private By resultProdText = By.xpath("//strong[text()='T-shirt']");

	public searchPageObjects(WebDriver driver)
	{
		this.driver = driver;
	}

	public void searchProduct(String text)
	{
		driver.findElement(search_product).sendKeys(text);
		Logger.info("Search product : "+text);
	}

	public void validateSearchProductSuccessful(String ExpectedText)
	{
		String resultText = driver.findElement(resultProdText).getText();
		Assert.assertEquals(ExpectedText,resultText); 
		Logger.info("Product search result contains : "+resultText);
	}
}
