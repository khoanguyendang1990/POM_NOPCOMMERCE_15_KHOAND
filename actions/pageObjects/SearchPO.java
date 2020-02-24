package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPageObject;

public class SearchPO extends AbstractPageObject {

	WebDriver driver;
	public SearchPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
