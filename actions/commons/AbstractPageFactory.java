package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageFactory {

	WebDriver driver;
	WebElement element;
	WebDriverWait waitExplicit;
	long longTimeout = 30;

	public AbstractPageFactory(WebDriver driver) {
		this.driver = driver;
		waitExplicit = new WebDriverWait(driver, longTimeout);
	}

	public void waitForElementVisible(WebElement element) {
		waitExplicit.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickToElement(WebElement element) {
		element.click();
	}

	public void sendKeyToElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public boolean isElementDisplay(WebElement element) {
		return element.isDisplayed();
	}
}
