package com.autotesting.framework.screens;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TutByMainPageScreen extends CommonPageScreen {

	private static final String MAIL_INPUT_LOGIN_XPATH = "//input[@id='Username']";
	private static final String MAIL_INPUT_PASSWORD_XPATH = "//input[@id='Password']";
	private static final String MAIL_BUTTON_ENTER_XPATH = "//input[@value='Войти']";
	private static final String OPEN_MAIL_BUTTON_XPATH = "//*[@title='Почта']";
	private static final String MESSAGE_NOT_RIGHT_LOGIN_XPATH = "//*[@id='form']/fieldset/strong";
	private static final String TEST_MAIN_URL = "http://www.tut.by";
	private static final String LOST_PASSWORD_XPATH = "//*[@id='form']/fieldset/div[2]/a";
	private static final String RESTABLISH_TITLE_NAME_XPATH = "//h2[text()='Восстановление пароля']";

	public String Incorrectlogin(String username, String password) {
		driver.get(TEST_MAIN_URL);
		driver.clickByXpath(OPEN_MAIL_BUTTON_XPATH);
		driver.sendKeysByXpath(MAIL_INPUT_LOGIN_XPATH,100,username);// вводится логин
		driver.sendKeysByXpath(MAIL_INPUT_PASSWORD_XPATH, 100,password);// вводится пароль
		driver.clickByXpath(MAIL_BUTTON_ENTER_XPATH);

		return driver.findElement(By.xpath(MESSAGE_NOT_RIGHT_LOGIN_XPATH)).getText();
	}

	public String restablishPasswordAndLogin() {
		driver.navigate().to(TEST_MAIN_URL);
		driver.clickByXpath(OPEN_MAIL_BUTTON_XPATH, 100);
		Set<String> oldWindowsSet = driver.getWindowHandles();
		driver.clickByXpath(LOST_PASSWORD_XPATH, 100);
		Set<String> newWindowsSet = driver.getWindowHandles();
		newWindowsSet.removeAll(oldWindowsSet);
		String newWindowHandle = newWindowsSet.iterator().next();
		driver.switchTo().window(newWindowHandle);
		
		return driver.findElement(By.xpath(RESTABLISH_TITLE_NAME_XPATH)).getText();
	}
}
