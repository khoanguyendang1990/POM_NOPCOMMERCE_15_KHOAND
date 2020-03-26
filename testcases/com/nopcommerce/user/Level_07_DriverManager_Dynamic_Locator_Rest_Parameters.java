package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.AbstractPageObject;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import pageObjects.FooterMyAccountPageObject;
import pageObjects.FooterSiteMapPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.ShippingAndReturnPO;
import reportConfig.ExtentManager;
import reportConfig.ExtentTestManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_07_DriverManager_Dynamic_Locator_Rest_Parameters extends AbstractTest {
	private WebDriver driver;

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private FooterMyAccountPageObject footerMyAccountPage;
	private FooterSiteMapPageObject footerSiteMapPage;
	private ShippingAndReturnPO shippingAndReturnPage;
	private SearchPO searchPage;
	private AbstractPageObject abstractPage;
	private DriverManager driverManager;
	@BeforeClass
	@Parameters("browserName")
	public void beforeClass(String browserName) {
//		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driverManager = DriverManagerFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.openUrl("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Login() {

		ExtentTestManager.startTest("TC_01_Login", "TC_01_Login Test Login function");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1: Open Login Page");
		loginPage =  homePage.openLoginPage();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2: Input Email");
		loginPage.inputTextToEmail("khoa.nguyendang1990@hotmail.com");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3: Input Password");
		loginPage.inpurtTextToPassword("123456");
		homePage = loginPage.clickOnLogin();
		assertTrue(homePage.isMyAcountLinkDisplay());
		assertTrue(homePage.isLogoutLinkDisplay());
		ExtentTestManager.endTest();
	}

	@Test
	public void TC_03_Dynamic_Locator_Less_Page() {
		ExtentTestManager.startTest("TC_03_Dynamic_Locator_Less_Page", "TC_03_Dynamic_Locator_Less_Page Test Dynamic locator");
		// Home Page -> Footer My Account
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 1");
		footerMyAccountPage = (FooterMyAccountPageObject) homePage.openMultiplePage("My account");

		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 2");
		// Footer My Account -> Site Map
		footerSiteMapPage = (FooterSiteMapPageObject) footerMyAccountPage.openMultiplePage("Sitemap");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Step 3");
		// Site Map -> Shiping and Return
		shippingAndReturnPage = (ShippingAndReturnPO) footerSiteMapPage.openMultiplePage("Shipping & returns");
		
		// Shiping and Return -> Footer My Account
		footerMyAccountPage = (FooterMyAccountPageObject) shippingAndReturnPage.openMultiplePage("My account");
		
		// Footer My Account -> Search page
		searchPage = (SearchPO) footerMyAccountPage.openMultiplePage("Search");
		
		// Search -> Shiping and Return
		shippingAndReturnPage = (ShippingAndReturnPO) searchPage.openMultiplePage("Shipping & returns");
		
		// Shiping and Return -> Footer My Account
		footerMyAccountPage = (FooterMyAccountPageObject) shippingAndReturnPage.openMultiplePage("My account");
		ExtentTestManager.endTest();
	}

	@Test
	public void TC_04_Dynamic_Locator_Many_Page() {
		// Home Page -> Footer My Account
		homePage.openMultiplePages("My account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
		
		// Footer My Account -> Site Map
		footerMyAccountPage.openMultiplePages("Sitemap");
		footerSiteMapPage = PageGeneratorManager.getSiteMapPage(driver);
		
		// Site Map -> Shiping and Return
		footerSiteMapPage.openMultiplePages("Shipping & returns");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);
		
		// Shiping and Return -> Footer My Account
		shippingAndReturnPage.openMultiplePages("My account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
		
		// Footer My Account -> Search page
		footerMyAccountPage.openMultiplePages("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		// Search -> Shiping and Return
		searchPage.openMultiplePages("Shipping & returns");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);
		
		// Shiping and Return -> Footer My Account
		shippingAndReturnPage.openMultiplePages("My account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
