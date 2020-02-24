package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPageObject;

public class FooterMyAccountPageObject extends AbstractPageObject{

	WebDriver driver;
	public FooterMyAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

}
