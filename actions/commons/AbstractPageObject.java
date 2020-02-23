package commons;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageObject {

	WebDriver driver;
	WebElement element;
	List<WebElement> elements;
	Select select;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecutor;
	By by;
	Actions action;
	long shortTimeout = 5;
	long longTimeout = 30;
	static Logger logger = Logger.getLogger(AbstractPageObject.class.getName());

	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
		waitExplicit = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	public void openUrl(String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void waitAlertPresence() {
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}

	public String getTextAlert(String value) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public WebElement find(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public List<WebElement> finds(String locator) {
		return driver.findElements(By.xpath(locator));
	}

	public void clickToElement(String locator) {
		try {
			element = find(locator);
			element.click();
		} catch (ElementClickInterceptedException e) {
			logger.error("Exception Occurred While Clicking To Element: " + e.getMessage());
		}
	}

	public void sendKeyToElement(String locator, String value) {
		try {
			element = find(locator);
			element.clear();
			element.sendKeys(value);
		} catch (NoSuchElementException e) {
			logger.error("Exception Occurred While Sendkey To Element: " + e.getMessage());
		}
	}

	public void selectItemInDropdown(String locator, String valueItem) {
		try {
			element = find(locator);
			select = new Select(element);
			select.selectByVisibleText(valueItem);
		} catch (ElementNotSelectableException e) {
			logger.error("Exception Occurred While Select Item In Dropdown List To Element: " + e.getMessage());
		}
	}

	public void selectItemInCustomDropdown(String parentLocator, String allItemLocator, String expectedItem)
			throws InterruptedException {
		element = find(parentLocator);

		jsExecutor.executeScript("agrument[0].scrollIntoView(true);", element);
		sleepInSecond(1);
		jsExecutor.executeScript("agrument[0].click();", element);
		sleepInSecond(1);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
		elements = finds(allItemLocator);

		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor.executeScript("agurment[0].scrollIntoView(true);", expectedItem);
				sleepInSecond(1);
				item.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public void sleepInSecond(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	public String getItemInDropdown(String locator) {
		String valueItem = null;
		try {
			element = find(locator);
			select = new Select(element);
			valueItem = select.getFirstSelectedOption().getText();
		} catch (ElementNotSelectableException e) {
			logger.error("Exception Occurred While Select Item In Dropdown List To Element: " + e.getMessage());
		}
		return valueItem;
	}

	public String getAttributeValue(String locator, String attributeName) {
		element = find(locator);
		return element.getAttribute(attributeName);
	}

	public String getTextElement(String locator) {
		element = find(locator);
		return element.getText();
	}

	public int countElementNumber(String locator) {
		elements = finds(locator);
		return elements.size();
	}

	public void checkToCheckbox(String locator) {
		element = find(locator);
		if (element.isSelected() == false) {
			element.click();
		}
	}

	public void uncheckToCheckbox(String locator) {
		element = find(locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(String locator) {
		element = find(locator);
		return element.isDisplayed();
	}

	public boolean isElementSelected(String locator) {
		element = find(locator);
		return element.isSelected();
	}

	public boolean isElementEnabled(String locator) {
		element = find(locator);
		return element.isEnabled();
	}

	public void switchToChildWindowByID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToChildWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToFameOrIframe(String locator) {
		element = find(locator);
		driver.switchTo().frame(element);
	}

	public void switchToParentPage() {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(String locator) {
		element = find(locator);
		action.moveToElement(element).perform();
	}

	public void doubleClickToElement(String locator) {
		element = find(locator);
		action.doubleClick(element).perform();
	}

	public void sendKeyToElement(String locator, Keys key) {
		element = find(locator);
		action.sendKeys(element, key).perform();
	}

	public void drapAndDrop(String sourceLocator, String destinationLocator) {

		WebElement sourceElement;
		WebElement destinationElement;
		sourceElement = find(sourceLocator);
		destinationElement = find(destinationLocator);
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				logger.error("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			logger.error("Element with " + sourceElement + "or" + destinationElement
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			logger.error("Element with " + sourceElement + "or" + destinationElement + "was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			logger.error("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}

	}

	public void waitForElementVisible(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementPresence(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForElementInvisible(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForElementClickable(String locator) {
		by = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(by));
	}
}
