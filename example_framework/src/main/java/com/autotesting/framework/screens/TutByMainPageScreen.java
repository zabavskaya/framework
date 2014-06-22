package com.autotesting.framework.screens;

import org.openqa.selenium.By;
import org.testng.Assert;

public class TutByMainPageScreen extends CommonPageScreen  {
	
	private static final String MAIL_INPUT_LOGIN_XPATH = "//*[@id='Username']";
	private static final String MAIL_INPUT_PASSWORD_XPATH = "//*[@id='Password']";
	private static final String MAIL_BUTTON_ENTER_XPATH = "//input[@value='Войти']";
	private static final String OPEN_MAIL_BUTTON_XPATH = "//*[@title='Почта']";
	private static final String MESSAGE_NOT_RIGHT_LOGIN_XPATH = "//*[@id='form']/fieldset/strong";
	private static final String NOT_RIGHT_LOGIN_TEXT="Неверное имя пользователя или пароль";
	private static final String TEST_MAIN_URL = "http://www.tut.by";
	
	/*public void login(String username, String password)
	{
		driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
		driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(username);//вводится логин
		driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(password);//вводится пароль
		driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();
	
	}
	*/
	
	public void Incorrectlogin(String username, String password)
	{
		driver.get(TEST_MAIN_URL);
		driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
		driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(username);//вводится логин
		driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(password);//вводится пароль
		driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();
		Assert.assertEquals(driver.findElement(By.xpath(MESSAGE_NOT_RIGHT_LOGIN_XPATH)).getText(), NOT_RIGHT_LOGIN_TEXT,"Неверное имя пользователя или пароль");
	}
	

}
