package com.nopcommerce.framework;

import org.testng.annotations.Test;

import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Level_05_ApplyPageObject_PageGenerator {
	WebDriver driver;
	
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
//		abstractPage = new AbstractPage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_Login() {
		loginPage = homePage.openLoginPage();
		System.out.println("Input Email");
		loginPage.inputTextToEmail("khoa.nguyendang1990@hotmail.com");
		System.out.println("Input Password");
		loginPage.inpurtTextToPassword("123456");
		homePage = loginPage.clickOnLogin();
		assertTrue(homePage.isMyAcountLinkDisplay());
		assertTrue(homePage.isLogoutLinkDisplay());
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
