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


public class PageBase {

	public WebDriver driver;

	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	public static int getRandomDoubleBetweenRange(double min, double max){
	    int x = (int)Math.round((Math.random()*((max-min)+1))+min);
	    return x;
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

			assertFail("unable to click "+element);
		}


	}

	// return true if element is present
	public boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void assertFail(String message) {

		Assert.fail(message);
	}

	// perform mouse hover action on the element parameter
	public void mouseHoverAndClickOnElement(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	// perform mouse hover actions using Javascript executer.
	public void JSmouseHover(WebElement element) {
		try {
			if (isElementPresent(element)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, element);

			} else {
				System.out.println("Element not visible to hover " + "\n");

			}
		} catch (

		StaleElementReferenceException e) {
			System.out.println("Element with " + element + "is not attached to the page document" + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
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
		WebDriverWait w = new WebDriverWait(driver, 50);
		try {
			w.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			assertFail("Element"+element+" not found after wait");
		}

	}

	// from the specified container, click on the text that is passed as
	// parameter
	public void selectElementFromContainer(WebElement element, String tagName, String textToBeClicked)
			throws IOException {
		List<WebElement> list = element.findElements(By.tagName(tagName));
		for (WebElement webElement : list) {
			if (webElement.getText().equals(textToBeClicked)) {
				webElement.click();
			}
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


	public void pageLoad(long time){
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	public void implicitwait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void jsclick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public boolean isAlertPresent() {

		try {
			Alert alert = driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getNumberElementsFromContainer(WebElement container, String tagName) {
		int number = 0;
		List<WebElement> webelements = container.findElements(By.tagName(tagName));
		for (WebElement webElement : webelements) {
			number++;
		}
		return number;
	}

	public void doubleClick(WebElement el){
		Actions action=new Actions(driver);
		action.doubleClick(el);
	}

	public String convertListToString(List<String> values) {

		StringBuffer newValue = new StringBuffer();

		for (String string : values) {
			newValue.append(string);
			newValue.append(">");
		}
		newValue.deleteCharAt(newValue.length() - 1);

		return newValue.toString();
	}
	public List<String> convertStringToList(String value) {

		List<String> list = new ArrayList<String>(Arrays.asList(value.split(">")));
		return list;

	}

	public boolean comapreTwoStrings(String value1, String value2) {
		boolean flag = true;
		if (value1 != value2) {
			flag = false;
		}
		return flag;
	}


	public String getText(WebElement element){
		String message = "";
		try {
			waitForElementTobevisible(element);
		 message= element.getText();
		} catch (Exception e) {
			assertFail("unable to get text of "+element);

		}
		return message;
	}

	public void waitForTextToAppear(WebElement element,String textToAppear) {
	    WebDriverWait wait = new WebDriverWait(driver,100);
	    wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
	}

}
