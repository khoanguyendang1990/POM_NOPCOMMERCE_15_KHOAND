package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPageFactory;

public class HomePageFactory extends AbstractPageFactory {

	public HomePageFactory(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@class='ico-login']")
	WebElement loginLink;
	
	@FindBy(xpath="//a[@class='ico-account']")
	WebElement myAccountLink;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	WebElement logoutLink;
	
	public void clickToLoginLink() {
		waitForElementVisible(loginLink);
		clickToElement(loginLink);
	}

	public boolean isMyAcountLinkDisplay() {
		return isElementDisplay(myAccountLink);
	}

	public boolean isLogoutLinkDisplay() {
		return isElementDisplay(logoutLink);
	}

}
