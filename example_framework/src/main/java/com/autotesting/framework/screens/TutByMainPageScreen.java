package com.autotesting.framework.screens;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TutByMainPageScreen extends CommonPageScreen {

	private static final String MAIL_INPUT_LOGIN_XPATH = "//*[@id='Username']";
	private static final String MAIL_INPUT_PASSWORD_XPATH = "//*[@id='Password']";
	private static final String MAIL_BUTTON_ENTER_XPATH = "//input[@value='Войти']";
	private static final String OPEN_MAIL_BUTTON_XPATH = "//*[@title='Почта']";
	private static final String MESSAGE_NOT_RIGHT_LOGIN_XPATH = "//*[@id='form']/fieldset/strong";
	private static final String NOT_RIGHT_LOGIN_TEXT = "Неверное имя пользователя или пароль";
	private static final String TEST_MAIN_URL = "http://www.tut.by";
	private static final String LOST_PASSWORD_XPATH = "//*[@id='form']/fieldset/div[2]/a";
	private static final String RESTABLISH_TITLE_NAME_XPATH = "//h2[text()='Восстановление пароля']";
	private static final String RESTABLISH_TITLE_NAME = "Восстановление пароля";

	public String Incorrectlogin(String username, String password) {
		driver.get(TEST_MAIN_URL);
		driver.clickByXpath(OPEN_MAIL_BUTTON_XPATH);
		driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(username);// вводится
																				// логин
		driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(
				password);// вводится пароль
		driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();

		return driver.findElement(By.xpath(MESSAGE_NOT_RIGHT_LOGIN_XPATH))
				.getText();
	}

	public String restablishPasswordAndLogin() {
//		driver.navigate().refresh();
		driver.navigate().to(TEST_MAIN_URL);
	
	//	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();

		Set<String> oldWindowsSet = driver.getWindowHandles();

		driver.findElement(By.xpath(LOST_PASSWORD_XPATH)).click();

		Set<String> newWindowsSet = driver.getWindowHandles();

		newWindowsSet.removeAll(oldWindowsSet);
		String newWindowHandle = newWindowsSet.iterator().next();

		driver.switchTo().window(newWindowHandle);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		return driver.findElement(By.xpath(RESTABLISH_TITLE_NAME_XPATH))
				.getText();
	}
}
