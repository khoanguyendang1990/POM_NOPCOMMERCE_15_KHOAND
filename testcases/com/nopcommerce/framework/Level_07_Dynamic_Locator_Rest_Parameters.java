package com.nopcommerce.framework;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.FooterMyAccountPageObject;
import pageObjects.FooterSiteMapPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.ShippingAndReturnPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_07_Dynamic_Locator_Rest_Parameters extends AbstractTest {
	private WebDriver driver;

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private FooterMyAccountPageObject footerMyAccountPage;
	private FooterSiteMapPageObject footerSiteMapPage;
	private ShippingAndReturnPO shippingAndReturnPage;
	private SearchPO searchPage;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browserName) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = getBrowserDriver(browserName);
//		abstractPage = new AbstractPage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_Login() {

		loginPage = (LoginPageObject) homePage.openMultiplePage("Home Page");
		System.out.println("Input Email");
		loginPage.inputTextToEmail("khoa.nguyendang1990@hotmail.com");
		System.out.println("Input Password");
		loginPage.inpurtTextToPassword("123456");
		homePage = loginPage.clickOnLogin();
		assertTrue(homePage.isMyAcountLinkDisplay());
		assertTrue(homePage.isLogoutLinkDisplay());
	}

	@Test
	public void TC_03_Dynamic_Locator_Less_Page() {
		// Home Page -> Footer My Account
		footerMyAccountPage = (FooterMyAccountPageObject) homePage.openMultiplePage("My Account");

		// Footer My Account -> Site Map
		footerSiteMapPage = (FooterSiteMapPageObject) footerMyAccountPage.openMultiplePage("Site Map");
		
		// Site Map -> Shiping and Return
		shippingAndReturnPage = (ShippingAndReturnPO) footerSiteMapPage.openMultiplePage("Shipping And Return");
		
		// Shiping and Return -> Footer My Account
		footerMyAccountPage = (FooterMyAccountPageObject) shippingAndReturnPage.openMultiplePage("My Account");
		
		// Footer My Account -> Search page
		searchPage = (SearchPO) footerMyAccountPage.openMultiplePage("Search Page");
		
		// Search -> Shiping and Return
		shippingAndReturnPage = (ShippingAndReturnPO) searchPage.openMultiplePage("Shipping And Return");
		
		// Shiping and Return -> Footer My Account
		footerMyAccountPage = (FooterMyAccountPageObject) shippingAndReturnPage.openMultiplePage("My Account");
	}

	@Test
	public void TC_04_Dynamic_Locator_Many_Page() {
		// Home Page -> Footer My Account
		homePage.openMultiplePages("My Account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
		
		// Footer My Account -> Site Map
		footerMyAccountPage.openMultiplePages("Site Map");
		footerSiteMapPage = PageGeneratorManager.getSiteMapPage(driver);
		
		// Site Map -> Shiping and Return
		footerSiteMapPage.openMultiplePages("Shipping And Return");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);
		
		// Shiping and Return -> Footer My Account
		shippingAndReturnPage.openMultiplePages("My Account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
		
		// Footer My Account -> Search page
		footerMyAccountPage.openMultiplePages("Search Page");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		// Search -> Shiping and Return
		searchPage.openMultiplePages("My Account");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);
		
		// Shiping and Return -> Footer My Account
		shippingAndReturnPage.openMultiplePages("My Account");
		footerMyAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
