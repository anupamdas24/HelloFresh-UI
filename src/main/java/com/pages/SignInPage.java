/*This class contains all the page objects and the operational methods on them */

package com.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SignInPage extends PageBase {

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 30);

	@FindBy(how = How.ID, using = "email_create")
	private WebElement emailCreate;

	@FindBy(how = How.ID, using = "SubmitCreate")
	private WebElement submitCreate;

	@FindBy(how = How.XPATH, using = "//form[@id='account-creation_form']/descendant::label[@for='id_gender2']")
	private WebElement Mrs;

	@FindBy(how = How.ID, using = "customer_firstname")
	private WebElement customerFirstname;

	@FindBy(how = How.ID, using = "customer_lastname")
	private WebElement customerLastname;

	@FindBy(how = How.ID, using = "passwd")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//div[@id='uniform-days']/select")
	private WebElement days;

	@FindBy(how = How.XPATH, using = "//select[@id='months']")
	private WebElement months;

	@FindBy(how = How.XPATH, using = "//select[@id='years']")
	private WebElement years;

	@FindBy(how = How.ID, using = "company")
	private WebElement company;

	@FindBy(how = How.ID, using = "address1")
	private WebElement address1;

	@FindBy(how = How.ID, using = "address2")
	private WebElement address2;

	@FindBy(how = How.ID, using = "city")
	private WebElement city;

	@FindBy(how = How.XPATH, using = "//select[@name='id_state']")
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

	@FindBy(how = How.XPATH, using = "//div[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	private WebElement proceedToCheckOut;

	@FindBy(how = How.XPATH, using = "//p[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	private WebElement cartNavProceedToCheckOut;

	@FindBy(how = How.NAME, using = "processAddress")
	private WebElement processAddress;

	@FindBy(how = How.ID, using = "uniform-cgv")
	private WebElement uniformCgv;

	@FindBy(how = How.ID, using = "email")
	private WebElement email;

	@FindBy(how = How.XPATH, using = "//button[@id='SubmitLogin']")
	private WebElement SubmitLogin;

	@FindBy(how = How.NAME, using = "processCarrier")
	private WebElement processCarrier;

	@FindBy(how = How.CLASS_NAME, using = "bankwire")
	private WebElement bankwire;

	@FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
	private WebElement cartNavBtn;

	@FindBy(how = How.CSS, using = "h1")
	private WebElement heading;

	@FindBy(how = How.XPATH, using = "//div[@id='header_logo']/a/img")
	private WebElement headerLogo;

	@FindBy(how = How.CLASS_NAME, using = "account")
	private WebElement account;

	@FindBy(how = How.CLASS_NAME, using = "info-account")
	private WebElement info_account;

	@FindBy(how = How.CLASS_NAME, using = "logout")
	private WebElement logout;

	@FindBy(how = How.XPATH, using = "//a[@class='login']")
	private WebElement login;

	@FindBy(how = How.XPATH, using = "//button[@name='Submit']")
	private WebElement Submit;

	@FindBy(how = How.XPATH, using = "//a[@title='Women']")
	private WebElement Women;

	@FindBy(how = How.XPATH, using = "//a[@title='Faded Short Sleeve T-shirts']")
	private WebElement fadedShortSleeveTShirt;

	@FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
	private WebElement stepDoneLastFour;

	@FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
	private WebElement stepEnd;

	@FindBy(how = How.XPATH, using = "//p[@class='cheque-indent']/strong")
	private WebElement OrderConfirmation;

	public void signInTestValidation(String firstName, String lastName, String password, String dayValue,
			String monthValue, String yearValue, String companyData, String address11, String address12,
			String cityValue, String stateValue, String postalCode, String otherInfo, String homePhoneNumber,
			String mobilePhoneNumber, String addressAlias) {
		wait.until(ExpectedConditions.visibilityOf(login)).click();
		String timestamp = String.valueOf(new Date().getTime());

		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
		inputText(emailCreate, email);
		submitCreate.click();

		wait.until(ExpectedConditions.visibilityOf(Mrs)).click();

		inputText(customerFirstname, firstName);
		inputText(customerLastname, lastName);
		inputText(passwd, password);

		inputText(company, companyData);
		address1.sendKeys(address11);
		address2.sendKeys(address12);
		city.sendKeys(cityValue);

		Actions builder = new Actions(driver);
		Action mouseOverToSelect = builder.moveToElement(idState).build();
		mouseOverToSelect.perform();
		driver.findElement(By.xpath("//select[@name='id_state']/option[text()='" + stateValue + "']")).click();

		postcode.sendKeys(postalCode);
		other.sendKeys(otherInfo);
		phone.sendKeys(homePhoneNumber);
		phoneMobile.sendKeys(mobilePhoneNumber);
		alias.sendKeys(addressAlias);
		submitAccount.click();
		wait.until(ExpectedConditions.visibilityOf(heading));

		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
		Assert.assertEquals("MY ACCOUNT", heading.getText());
		Assert.assertTrue(logout.isDisplayed());

		wait.until(ExpectedConditions.visibilityOf(headerLogo)).click();
		wait.until(ExpectedConditions.visibilityOf(logout)).click();

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

		wait.until(ExpectedConditions.visibilityOf(logout)).click();

	}

	public void checkOutTestValidation(String existingUserEmail, String existingUserPassword) {

		wait.until(ExpectedConditions.visibilityOf(login)).click();
		email.sendKeys(existingUserEmail);
		passwd.sendKeys(existingUserPassword);
		SubmitLogin.click();

		wait.until(ExpectedConditions.visibilityOf(Women)).click();
		wait.until(ExpectedConditions.visibilityOf(fadedShortSleeveTShirt));
		mouseHoverOnElement(fadedShortSleeveTShirt);
		jsclick(fadedShortSleeveTShirt);

		wait.until(ExpectedConditions.visibilityOf(Submit)).click();
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckOut)).click();
		wait.until(ExpectedConditions.visibilityOf(cartNavProceedToCheckOut)).click();
		wait.until(ExpectedConditions.visibilityOf(processAddress)).click();
		wait.until(ExpectedConditions.visibilityOf(uniformCgv)).click();
		processCarrier.click();
		wait.until(ExpectedConditions.visibilityOf(bankwire)).click();
		wait.until(ExpectedConditions.visibilityOf(cartNavBtn)).click();
		wait.until(ExpectedConditions.visibilityOf(heading));

		Assert.assertTrue(driver.getCurrentUrl().contains("?controller=order-confirmation"));
		Assert.assertTrue(OrderConfirmation.getText().contains("Your order on My Store is complete"));
		Assert.assertTrue(stepEnd.getAttribute("id").contains("step_end"));

		wait.until(ExpectedConditions.visibilityOf(logout)).click();

	}

}
