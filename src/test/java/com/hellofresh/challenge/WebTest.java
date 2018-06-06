package com.hellofresh.challenge;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actionEngine.BaseOperation;


public class WebTest extends BaseOperation{

	@Test(priority = 0, description = "Validate the successful sign in test", enabled = true, alwaysRun=true)
	public void signInTest() throws InterruptedException {
		container.loginPage.signInTestValidation();
	}
	
	@Test(dataProvider = "LoginTest", priority = 1, description = "Validate the successful login in test", enabled = false,alwaysRun=true)
	public void logInTest(String existingUserEmail, String existingUserPassword) throws InterruptedException {
		System.out.println("existingUserEmail::::"+existingUserEmail);
		container.loginPage.logInTestValidation(existingUserEmail, existingUserPassword);
	}
	
	@Test(dataProvider = "LoginTest", priority = 2, description = "Validate the successful checkout test", enabled = false, alwaysRun=true)
	public void checkOutTest(String existingUserEmail, String existingUserPassword) throws InterruptedException {
		container.loginPage.checkOutTestValidation(existingUserEmail, existingUserPassword);
	}
	
	
    @DataProvider(name = "LoginTest")
	public Object[][] LoginDataProvider() throws Exception {
    	return com.utilities.ExcelDP.getTableArray("testData", "DemoChallengeData");
	}
}
