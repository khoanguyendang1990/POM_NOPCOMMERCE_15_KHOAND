package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPageObject;

public class FooterSiteMapPageObject extends AbstractPageObject {

	WebDriver driver;
	public FooterSiteMapPageObject(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
