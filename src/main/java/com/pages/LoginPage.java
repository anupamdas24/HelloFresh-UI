package com.pages;

import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.CustomException;

import junit.framework.Assert;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(how = How.ID, using = "email_create")
	private WebElement emailCreate;

	@FindBy(how = How.ID, using = "SubmitCreate")
	private WebElement submitCreate;

	@FindBy(how = How.ID, using = "id_gender2")
	private WebElement idGender2;

	@FindBy(how = How.ID, using = "customer_firstname")
	private WebElement customerFirstname;

	@FindBy(how = How.ID, using = "customer_lastname")
	private WebElement customerLastname;

	@FindBy(how = How.ID, using = "passwd")
	private WebElement password;

	@FindBy(how = How.ID, using = "days")
	private WebElement days;

	@FindBy(how = How.ID, using = "months")
	private WebElement months;

	@FindBy(how = How.ID, using = "years")
	private WebElement years;

	@FindBy(how = How.ID, using = "company")
	private WebElement company;

	@FindBy(how = How.ID, using = "address1")
	private WebElement address1;

	@FindBy(how = How.ID, using = "address2")
	private WebElement address2;

	@FindBy(how = How.ID, using = "city")
	private WebElement city;

	@FindBy(how = How.ID, using = "id_state")
	private WebElement idState;

	@FindBy(how = How.ID, using = "postcode")
	private WebElement postcode;

	@FindBy(how = How.ID, using = "other")
	private WebElement other;

	@FindBy(how = How.ID, using = "phone")
	private WebElement phone;

	@FindBy(how = How.ID, using = "phone_mobile")
	private WebElement phoneMobile;

	@FindBy(how = How.ID, using = "alias")
	private WebElement alias;

	@FindBy(how = How.ID, using = "submitAccount")
	private WebElement submitAccount;

	@FindBy(how = How.ID, using = "passwd")
	private WebElement passwd;

	@FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	private WebElement proceedToCheckOut;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	private WebElement cartNavProceedToCheckOut;

	@FindBy(how = How.NAME, using = "processAddress")
	private WebElement processAddress;

	@FindBy(how = How.ID, using = "uniform-cgv")
	private WebElement uniformCgv;

	@FindBy(how = How.ID, using = "email")
	private WebElement email;

	@FindBy(how = How.ID, using = "SubmitLogin")
	private WebElement SubmitLogin;

	@FindBy(how = How.NAME, using = "processCarrier")
	private WebElement processCarrier;

	@FindBy(how = How.CLASS_NAME, using = "bankwire")
	private WebElement bankwire;

	@FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
	private WebElement cartNavBtn;

	@FindBy(how = How.CSS, using = "h1")
	private WebElement heading;

	@FindBy(how = How.CLASS_NAME, using = "account")
	private WebElement account;

	@FindBy(how = How.CLASS_NAME, using = "info-account")
	private WebElement info_account;

	@FindBy(how = How.CLASS_NAME, using = "logout")
	private WebElement logout;

	@FindBy(how = How.CLASS_NAME, using = "login")
	private WebElement login;

	@FindBy(how = How.NAME, using = "Submit")
	private WebElement Submit;

	@FindBy(how = How.LINK_TEXT, using = "Women")
	private WebElement Women;

	@FindBy(how = How.XPATH, using = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	private WebElement fadedShortSleeveTShirt;

	@FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
	private WebElement stepDoneLastFour;

	@FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
	private WebElement stepEnd;

	@FindBy(how = How.XPATH, using = "//*[@class='cheque-indent']/strong")
	private WebElement YourOrderOnMyStore;

	public void signInTestValidation() {
		System.out.println("Inside the signIntest");
			wait.until(ExpectedConditions.visibilityOf(login)).click();
			String timestamp = String.valueOf(new Date().getTime());

			String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
			String name = "Firstname";
			String surname = "Lastname";
			inputText(emailCreate, email);
			submitCreate.click();

			wait.until(ExpectedConditions.visibilityOf(idGender2)).click();

			inputText(customerFirstname, name);
			inputText(customerLastname, surname);
			inputText(passwd, "Qwerty");

			selectByValue(days, "1");
			selectByValue(months, "1");
			selectByValue(years, "2000");

			inputText(company,"Company");
			address1.sendKeys("Qwerty, 123");
			address2.sendKeys("zxcvb");
			city.sendKeys("Qwerty");

			selectByVisibleText(idState, "Colorado");
			postcode.sendKeys("12345");
			other.sendKeys("Qwerty");
			phone.sendKeys("12345123123");
			phoneMobile.sendKeys("12345123123");
			alias.sendKeys("hf");
			submitAccount.click();
			wait.until(ExpectedConditions.visibilityOf(heading));

	}

	public void logInTestValidation(String existingUserEmail, String existingUserPassword) {

		String fullName = "Joe Black";
		wait.until(ExpectedConditions.visibilityOf(login)).click();
		email.sendKeys(existingUserEmail);
		passwd.sendKeys(existingUserPassword);
		SubmitLogin.click();
		wait.until(ExpectedConditions.visibilityOf(heading));

		Assert.assertEquals("MY ACCOUNT", heading.getText());
		Assert.assertEquals(fullName, account.getText());
		Assert.assertTrue(info_account.getText().contains("Welcome to your account."));
		Assert.assertTrue(logout.isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}

	public void checkOutTestValidation(String existingUserEmail, String existingUserPassword) {

		wait.until(ExpectedConditions.visibilityOf(login)).click();
		email.sendKeys(existingUserEmail);
		passwd.sendKeys(existingUserPassword);
		SubmitLogin.click();

		wait.until(ExpectedConditions.visibilityOf(Women)).click();
		fadedShortSleeveTShirt.click();
		wait.until(ExpectedConditions.visibilityOf(Submit)).click();
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckOut)).click();
		wait.until(ExpectedConditions.visibilityOf(cartNavProceedToCheckOut)).click();
		wait.until(ExpectedConditions.visibilityOf(processAddress)).click();
		wait.until(ExpectedConditions.visibilityOf(uniformCgv)).click();
		processCarrier.click();
		wait.until(ExpectedConditions.visibilityOf(bankwire)).click();
		wait.until(ExpectedConditions.visibilityOf(cartNavBtn)).click();
		wait.until(ExpectedConditions.visibilityOf(heading));

	}

}
