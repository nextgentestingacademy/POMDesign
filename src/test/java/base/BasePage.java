package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	
	protected boolean waitForElementClickable(By locator) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		}catch(Exception e) {
			System.out.println("Element is not clickable: " + locator + " " + e.getMessage());
			return false;
		}
	}
	
	protected boolean waitForVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		}catch(Exception e) {
			System.out.println("Element is not visible: " + locator + " " + e.getMessage());
			return false;
		}
	}	
	
	protected String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		}catch(Exception e) {
			System.out.println("Unable to fetch the URL: " + e.getMessage());
			return "";
		}
	}
	
	protected boolean click(By locator) {
		try {
			waitForElementClickable(locator);
			driver.findElement(locator).click();
			return true;
		}catch(Exception e) {
			System.out.println("Unable to click on " + locator + " :" + e.getMessage());
			return false;
		}
	}
	
	protected boolean enterText(By locator, String text) {
		try {
			waitForVisible(locator);
			driver.findElement(locator).sendKeys(text);
			return true;
		}catch(Exception e) {
			System.out.println("Unable to enter on " + locator + " :" + e.getMessage());
			return false;
		}
		
	}
	
}
