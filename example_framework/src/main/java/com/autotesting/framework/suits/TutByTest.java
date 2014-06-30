package com.autotesting.framework.suits;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;

import com.autotesting.framework.screens.CommonPageScreen;
import com.autotesting.framework.screens.TutByMainPageScreen;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TutByTest {

	// private static final String LOGIN="nikazabava";
	private static final String PASSWORD = "wonderfulworld";
	private static final String INCORRECT_LOGIN = "****";
	private static final String NOT_RIGHT_LOGIN_TEXT = "Неверное имя пользователя или пароль";
	private static final String RESTABLISH_TITLE_NAME = "Восстановление пароля";
	private TutByMainPageScreen tutByMainPage;
	Logger log;

	// protected final Logger log = LoggerFactory
	// .getLogger(CommonPageScreen.class);

	@Test
	// востановление пароля
	public void Incorrectlogin() throws InterruptedException {

		// инициализация логирования
		PropertyConfigurator
				.configure("D:/Nika/workspace/framework/framework/example_framework/resource/log4j.properties");
		log = Logger.getLogger(getClass().getName());
		log.info("starting...");
		System.out.println("1");
		tutByMainPage = new TutByMainPageScreen();
		String inscription = tutByMainPage.Incorrectlogin(INCORRECT_LOGIN,
				PASSWORD);
		Assert.assertEquals(inscription, NOT_RIGHT_LOGIN_TEXT);
		log.info("Incorrectlogin test completed");
		tutByMainPage.closePage();
		System.out.println("1e");
	}

	@Test

	public void restablishPasswordAndLogin() throws InterruptedException {
		System.out.println("2");
		tutByMainPage = new TutByMainPageScreen();
		String restablish = tutByMainPage.restablishPasswordAndLogin();
		Assert.assertEquals(restablish, RESTABLISH_TITLE_NAME,
				"Не открылась форма востановления пароля");// Получает XPATH
															// текстового
															// сообщенея
															// "Восстановить забытый пароль"
		tutByMainPage.closePage();
		System.out.println("2e");

	}

}
