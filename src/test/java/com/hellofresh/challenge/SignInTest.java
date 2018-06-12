/*
 * This class to check the Login functionality for the new user after the successful registration.
 */

package com.hellofresh.challenge;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actionEngine.BaseOperation;
import com.pages.SignInPage;
import com.utilities.ExcelDP;

public class SignInTest extends BaseOperation {

	@Test(dataProvider = "newuserdata", description = "Validate the successful sign in test after registration", enabled = true)
	public void signInTest(String FirstName, String LastName, String Password, String Days, String Month,
			String Year, String Company, String Address1, String Address2, String City, String State, String PostalCode,
			String OtherInfo, String HomePhoneNumber, String MobilePhoneNumber, String AddressAlias)
			throws InterruptedException {
		SignInPage signinpage = PageFactory.initElements(getDriver(), SignInPage.class);
		signinpage.signInTestValidation(FirstName, LastName, Password, Days, Month, Year, Company, Address1,
				Address2, City, State, PostalCode, OtherInfo, HomePhoneNumber, MobilePhoneNumber, AddressAlias);

	}

	@DataProvider(name = "newuserdata")
	public Object[][] NewUserData() throws Exception {
		return ExcelDP.getTableArray("testData", "DemoChallengeNewUserData");
	}

}
