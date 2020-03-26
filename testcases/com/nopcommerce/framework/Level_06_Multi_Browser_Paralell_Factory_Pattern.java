package com.nopcommerce.framework;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_06_Multi_Browser_Paralell_Factory_Pattern extends AbstractTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	
	@BeforeClass
	@Parameters("browserName")
	public void beforeClass(String browserName) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = getBrowserDriver(browserName);
//		abstractPage = new AbstractPage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.openUrl("https://demo.nopcommerce.com/");
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
