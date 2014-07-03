package com.autotesting.framework.screens;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import com.autotesting.framework.utils.WebDriverRunner;
import com.autotesting.framework.utils.WebDriverWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonPageScreen {
	  protected WebDriverWrapper driver = WebDriverRunner.getDriver();
	  protected final Logger log = LoggerFactory.getLogger(CommonPageScreen.class); 
		
	 

		public void closePage() {
			log.info("close browser");
			WebDriverRunner.stopWebDriver();
		}
		
		
}
