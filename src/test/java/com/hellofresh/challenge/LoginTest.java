/*
 * This class to test the Login Logout functionality for the existing users.
 */

package com.hellofresh.challenge;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actionEngine.BaseOperation;
import com.pages.SignInPage;
import com.utilities.ExcelDP;

public class LoginTest extends BaseOperation {
	
	@Test(dataProvider = "existinguserdata", description = "Validate the successful login in test", enabled = true)
	public void logInTest(String existingUserEmail, String existingUserPassword) throws InterruptedException {
		SignInPage signinpage = PageFactory.initElements(getDriver(), SignInPage.class);
		signinpage.logInTestValidation(existingUserEmail, existingUserPassword);
	}

	@DataProvider(name = "existinguserdata")
	public Object[][] ExistingUserData() throws Exception {
		return ExcelDP.getTableArray("testData", "DemoChallengeExistingUserData");
	}

	@AfterTest
	public void endTest() {
		driver.close();
		driver.quit();
	}

}
