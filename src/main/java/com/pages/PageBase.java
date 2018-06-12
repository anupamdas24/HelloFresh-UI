/*Package containing the common functionalities*/

package com.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.actionEngine.BaseOperation;

/*
 * This class contains all the common functionalities to be performed by the driver on webelements.
 */

public class PageBase {

	public WebDriver driver;

	public PageBase(WebDriver driver) {
		PageFactory.initElements(BaseOperation.driver, this);
		this.driver = driver;

	}

	public void inputText(WebElement element, String value) {

		try {
			waitForElementTobevisible(element);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			assertFail("unable to input " + value + " to the field" + element);
		}

	}

	public void click(WebElement element) {
		try {
			waitForElementTobeClickable(element);
			element.click();
		} catch (Exception e) {

			assertFail("unable to click " + element);
		}

	}

	public void assertFail(String message) {

		Assert.fail(message);
	}

	// perform mouse hover action on the element parameter
	public void mouseHoverOnElement(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	// select specified text from a dropdown(element)
	public void selectByVisibleText(WebElement element, String textToBeSelected) {
		waitForElementTobeClickable(element);
		Select select = new Select(element);
		select.selectByVisibleText(textToBeSelected);

	}

	public void selectByValue(WebElement element, String value) {
		waitForElementTobeClickable(element);
		Select select = new Select(element);
		select.selectByValue(value);

	}

	// select specified Index from a dropdown(element)
	public void selectByIndex(WebElement element, int indexToBeSelected) {
		waitForElementTobeClickable(element);
		Select select = new Select(element);
		select.selectByIndex(indexToBeSelected);
	}

	// wait for the element on the web page to be clickable
	public void waitForElementTobeClickable(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, 30);
		try {
			w.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			assertFail("Element" + element + " not found after wait");
		}

	}

	public void waitForElementTobevisible(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, 50);
		try {
			w.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pageLoad(long time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	public void implicitwait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void jsclick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void waitForTextToAppear(WebElement element, String textToAppear) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
	}

}
