package com.autotesting.framework.screens;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

// во всех скринах тут бая
public class CommonPageScreen {
	private static ChromeDriverService service;
	protected static WebDriver driver;

	private static final String PATH_TO_CHROMEDRIVER = "resource//chromedriver.exe";

	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
			.usingChromeDriverExecutable(new File(PATH_TO_CHROMEDRIVER))
			.usingAnyFreePort()
			.build();
	service.start();
	driver = new ChromeDriver(service);


}}
