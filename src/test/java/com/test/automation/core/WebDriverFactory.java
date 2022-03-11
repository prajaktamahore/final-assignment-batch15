package com.test.automation.core;

import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	private static final Logger Logger = LogManager.getLogger(WebDriverFactory.class);
	private static WebDriver driver = null;

	public static WebDriver getWebDriverForBrowser(String browser) throws Exception 
	{
		switch (browser.toLowerCase()) 
		{
		case "chrome":
			driver = new ChromeDriver();
			Logger.info("Chrome Browser invoked");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			Logger.info("Firefox Browser invoked");
			break;

		default :
			Logger.fatal("No such browser is implemented.Browser name sent: "+browser);
			throw new Exception("No such browser is implemented.Browser name sent: "+browser);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Logger.info("Driver maximized and implicit time out set to 20 seconds");
		return driver;
	}

	public static void navigateToTheUrl(String url)
	{
		driver.get(url);
		Logger.info("Browser navigated to the url : "+url);
	}

	public static void redirectedToTheUrl(String url)
	{
		driver.navigate().to(url);
		Logger.info("Browser navigated to the url : "+url);
	}
	
	public static void quitDriver()
	{
		driver.quit();
		Logger.info("Browser Closed");
	}

	public static void browserSwitchToTab()
	{
		Set<String> handles = driver.getWindowHandles();
		Logger.info("List of windows found: "+handles.size());
		Logger.info("Windows handles: " + handles.toString());
		Iterator<String> it = handles.iterator();
		String original = it.next();
		String nextTab = it.next();
		driver.switchTo().window(nextTab);
		Logger.info("Switched to the new window/tab");
	}

	public static String getBrowserName()
	{
		String browserDefault = "firefox";
		String browserSentFromCmd = System.getProperty("browser");

		if(browserSentFromCmd == null)
		{
			return browserDefault;
		}
		else
		{
			return browserSentFromCmd;	
		}
	}
}
