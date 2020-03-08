package com.nopcommerce.framework;

import org.testng.annotations.Test;

import commons.AbstractPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Level_02_AbstractPage {
	WebDriver driver;
	AbstractPageObject abstractPage;
	@Test
	public void TC_01_Register() {
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		abstractPage = new AbstractPageObject(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
