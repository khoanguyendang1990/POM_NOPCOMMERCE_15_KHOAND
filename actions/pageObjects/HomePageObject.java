package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPageObject;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPageObject {

	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public LoginPageObject clickToLoginLink() {
		System.out.println(driver.toString());	
		waitForElementVisible(HomePageUI.LOGIN_LINK);
		clickToElement(HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public boolean isMyAcountLinkDisplay() {
		waitForElementVisible(HomePageUI.ACCOUNT_LINK);
		return isElementDisplay(HomePageUI.ACCOUNT_LINK);
	}

	public boolean isLogoutLinkDisplay() {
		waitForElementVisible(HomePageUI.LOGOUT_LINK);
		return isElementDisplay(HomePageUI.LOGOUT_LINK);
	}

}
