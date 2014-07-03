package com.autotesting.framework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autotesting.framework.screens.CommonPageScreen;

public class WebDriverWrapper extends ChromeDriver {
	
	protected final Logger log = Logger.getRootLogger();
	public static final int TIMEOUT_FOR_ACTION_SECONDS = 3;
	
	public WebDriverWrapper(ChromeDriverService service) {
	    super(service, DesiredCapabilities.chrome());
	}

	public void clickByXpath(String xpath) {
	    log.info("[ACTION]: Click element by xpath: '" + xpath + "'");
	    waitForElementPresentAndVisible(xpath, TIMEOUT_FOR_ACTION_SECONDS);
	    findElement(By.xpath(xpath)).click();
	 }
	
	public void clickByXpath(String xpath, int timeout) {
	    log.info("[ACTION]: Click element by xpath: '" + xpath + "'");
	    waitForElementPresentAndVisible(xpath, timeout);
	    findElement(By.xpath(xpath)).click();
	 }

	public void waitForElementPresentAndVisible(String locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(this, timeout);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void sendKeysByXpath(String xpath, int timeout, CharSequence keysToSend) {
		log.info("[ACTION]: Send key by xpath: '" + xpath + "'");
	    waitForElementPresentAndVisible(xpath, timeout);
	    findElement(By.xpath(xpath)).sendKeys(keysToSend);
	}
}

