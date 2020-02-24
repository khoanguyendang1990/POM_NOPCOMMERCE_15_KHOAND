package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPageObject;

public class ShippingAndReturnPO extends AbstractPageObject {

	WebDriver driver;
	public ShippingAndReturnPO(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
}
