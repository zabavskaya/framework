package com.autotesting.framework.suits;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;



import com.autotesting.framework.screens.TutByMainPageScreen;

import org.testng.Assert;
import org.testng.annotations.Test;
public class TutByTest {

//	private static final String LOGIN="nikazabava";
	private static final String PASSWORD="wonderfulworld";
	private static final String INCORRECT_LOGIN="****";
	
	@Test  // востановление пароля
public void Incorrectlogin() throws InterruptedException {
	
	TutByMainPageScreen tutByMainPage = new TutByMainPageScreen();
	tutByMainPage.Incorrectlogin( INCORRECT_LOGIN, PASSWORD);

}
	

}
