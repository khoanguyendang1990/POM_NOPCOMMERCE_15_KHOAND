package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.FooterMyAccountPageObject;
import pageObjects.FooterSiteMapPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPO;
import pageObjects.ShippingAndReturnPO;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static FooterMyAccountPageObject getFooterMyAccountPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new FooterMyAccountPageObject(driver);
	}

	public static FooterSiteMapPageObject getSiteMapPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new FooterSiteMapPageObject(driver);
	}

	public static ShippingAndReturnPO getShippingAndReturnPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new ShippingAndReturnPO(driver);
	}

	public static SearchPO getSearchPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new SearchPO(driver);
	}
	
	
}
