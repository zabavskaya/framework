package com.autotesting.framework;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass_1 {
	private static ChromeDriverService service;
	private static WebDriver driver;

	private static final String PATH_TO_CHROMEDRIVER = "resource//chromedriver.exe";
	private static final String OPEN_MAIL_BUTTON_XPATH = "//*[@id='mainmenu']/div/ul[1]/li[2]/a";
	private static final String TEST_MAIN_URL = "http://www.tut.by";
	private static final String MAIL_INPUT_LOGIN_XPATH = "//*[@id='Username']";
	private static final String MAIL_INPUT_PASSWORD_XPATH = "//*[@id='Password']";
	private static final String MAIL_BUTTON_ENTER_XPATH = "//input[@value='Войти']";
	private static final String LOGIN="nikazabava";
	private static final String PASSWORD="wonderfulworld";
	private static final String INCORRECT_LOGIN="****";
	private static final String LOST_PASSWORD_XPATH = "//*[@id='form']/fieldset/div[2]/a";
	private static final String RESTABLISH_TITLE_NAME_XPATH="//h2[text()='Восстановление пароля']";
	private static final String INBOX_TITLE_XPATH="//*[@title='nikazabava@tut.by']";
	private static final String INBOX_TITLE="nikazabava@tut.by";
	private static final String EMAIL_NAME="Вероника Забавская";
	private static final String RESTABLISH_TITLE_NAME="Восстановление пароля";
	private static final String MESSAGE_NOT_RIGHT_LOGIN_XPATH = "//*[@id='form']/fieldset/strong";
	private static final String NOT_RIGHT_LOGIN_TEXT="Неверное имя пользователя или пароль";
	private static final String BUTTON_EXIT_XPATH="//*[@id='gb_71']"; 
	private static final String WRITE_LETTER_XPATH="//*[@id=':32']/div/div";
	private static final String MAIL_TO_XPATH="//input[@text-area=':5x']";
	private static final String MAIL_TO_VALUE="azhiznevsky@mail.ru";
	private static final String MAIL_SUBJECT_XPATH="//*[@id=':6b']";
	private static final String MAIL_SUBJECT_VALUE="Тема письма";
	private static final String MAIL_BODY_XPATH="//*[@id=':5a']";
	private static final String MAIL_BODY_VALUE="Тело письма отправляемого";
	private static final String BUTTON_SEND_XPATH="//*[@id=':6l']";
	private static final String OLD_URL="http://mail.tut.by/?utm_source=main_page&utm_medium=main_resource_block&utm_campaign=tutby_links";
	private static final String NEW_URL="http://profile.tut.by/secret.html";
	private static final String NAME_AUTIRIZE="//*[@id='authorize']/div/a[2]";

	
	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingChromeDriverExecutable(new File(PATH_TO_CHROMEDRIVER))
				.usingAnyFreePort().build();
		service.start();
		driver = new ChromeDriver(service);
	}

	

	@Test   
	public void notRightLogin() throws InterruptedException {
		driver.get(TEST_MAIN_URL);
		driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
		driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(INCORRECT_LOGIN); //Вводит некорректрый логин
		driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(PASSWORD);//Вводит пароль
		driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();//кликает на кнопку "Войти"
		driver.findElement(By.xpath(MESSAGE_NOT_RIGHT_LOGIN_XPATH)).click();//Получает XPATH текстового сообщенея "Неверное имя пользователя или пароль"
		Assert.assertEquals(driver.findElement(By.xpath(MESSAGE_NOT_RIGHT_LOGIN_XPATH)).getText(), NOT_RIGHT_LOGIN_TEXT,"Неверное имя пользователя или пароль");
		// сверяет текст полученного через XPATH переменной c  заданным в переменных текстовым значением
	
	}

	@Test ( dependsOnMethods="notRightLogin") // востановление пароля
	public void restablishPasswordAndLogin() throws InterruptedException {
		
	driver.get(TEST_MAIN_URL);	
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);	
	driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
    Set<String>oldWindowsSet = driver.getWindowHandles();    
    driver.findElement(By.xpath(LOST_PASSWORD_XPATH)).click();    
    Set<String> newWindowsSet = driver.getWindowHandles();    
    newWindowsSet.removeAll(oldWindowsSet);
    String newWindowHandle = newWindowsSet.iterator().next(); 
    driver.switchTo().window(newWindowHandle);
    Assert.assertEquals(driver.findElement(By.xpath(RESTABLISH_TITLE_NAME_XPATH)).getText(),RESTABLISH_TITLE_NAME,"Не открылась форма востановления пароля");//Получает XPATH текстового сообщенея "Восстановить забытый пароль"
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    
	}
	

@Test ( dependsOnMethods="restablishPasswordAndLogin") //не рабочий
	public void autarization() throws InterruptedException {
		driver.get(TEST_MAIN_URL);
		driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
		driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(LOGIN);//вводится логин
		driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(PASSWORD);//вводится пароль
		driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();
	//авторизация прошла успешно
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);//ждет еще определенное время для загрузки ящика
		
		WebElement titleElement = driver.findElement(By.xpath(INBOX_TITLE_XPATH));
		Assert.assertEquals(titleElement.getText(),INBOX_TITLE,"Не совпадает e-mail указанный при регистрации");
		titleElement.click();
	
		driver.findElement(By.xpath(BUTTON_EXIT_XPATH)).click();
		
		
	}

@Test ( dependsOnMethods="autarization")
public void enterLogin() throws InterruptedException {
	driver.get(TEST_MAIN_URL);
	driver.findElement(By.cssSelector("a[data-target-popup=authorize-form]")).click();
	driver.findElement(By.cssSelector("input[name=login]")).sendKeys(LOGIN);//вводится логин
	driver.findElement(By.cssSelector("input[value=Войти]")).isDisplayed();
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	WebElement titleEmail = driver.findElement(By.xpath(NAME_AUTIRIZE));
	Assert.assertEquals(titleEmail.getText(),EMAIL_NAME,"Не совпадает e-mail указанный при регистрации");

	
}

@Test ( dependsOnMethods="enterLogin")
public void enterPassword() throws InterruptedException {
	driver.get(TEST_MAIN_URL);
	driver.findElement(By.cssSelector("a[data-target-popup=authorize-form]")).click();
	driver.findElement(By.cssSelector("input[name=password]")).sendKeys(PASSWORD);//вводится логин
	driver.findElement(By.cssSelector("input[value=Войти]")).isDisplayed();
	
}



//@Test ( dependsOnMethods="enterPassword")
//public void sendLetter() throws InterruptedException {
//	driver.get(TEST_MAIN_URL);
//	driver.findElement(By.xpath(OPEN_MAIL_BUTTON_XPATH)).click();
//	driver.findElement(By.xpath(MAIL_INPUT_LOGIN_XPATH)).sendKeys(LOGIN);//вводится логин
//	driver.findElement(By.xpath(MAIL_INPUT_PASSWORD_XPATH)).sendKeys(PASSWORD);//вводится пароль
//	driver.findElement(By.xpath(MAIL_BUTTON_ENTER_XPATH)).click();//авторизация прошла успешно
//	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);//ждет еще определенное время для загрузки ящика
//	driver.findElement(By.xpath(WRITE_LETTER_XPATH)).click();
//	driver.findElement(By.cssSelector(MAIL_TO_XPATH)).sendKeys(MAIL_TO_VALUE);
//	driver.findElement(By.xpath(MAIL_SUBJECT_XPATH)).sendKeys(MAIL_SUBJECT_VALUE);
//	driver.findElement(By.xpath(MAIL_BODY_XPATH)).sendKeys(MAIL_BODY_VALUE);
//	driver.findElement(By.xpath(BUTTON_SEND_XPATH)).click();
//	
//}



	@AfterClass
	public static void createAndStopService() {
		driver.quit();
		service.stop();
	}
	
	
}
