package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Level_01_Step_by_step {
	WebDriver driver;

	WebDriverWait waitExplicit;
	
	@Test
	public void TC_01_Register() {
		
		driver.findElement(By.xpath("//div[@id='khoa']"));
//		waitExplicit = new WebDriverWait(driver, 25);
//		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='khoa']")));
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
