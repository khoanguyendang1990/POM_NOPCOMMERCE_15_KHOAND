package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPageFactory;

public class LoginPageFactory extends AbstractPageFactory {

	
	public LoginPageFactory(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='Email']")
	WebElement emailTextbox;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement passwordTextbox;
	
	@FindBy(xpath="//input[@class='button-1 login-button']")
	WebElement loginButton;
	public void inputTextToEmail(String email) {
		waitForElementVisible(emailTextbox);
		sendKeyToElement(emailTextbox, email);
	}

	public void inpurtTextToPassword(String password) {
		waitForElementVisible(passwordTextbox);
		sendKeyToElement(passwordTextbox, password);
	}

	public void clickOnLogin() {
		waitForElementVisible(loginButton);
		clickToElement(loginButton);
	}

}
