/* 
 * The class to test the check out scenarios
 */

package com.hellofresh.challenge;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actionEngine.BaseOperation;
import com.pages.SignInPage;
import com.utilities.ExcelDP;

public class CheckOutTest extends BaseOperation{
	
	@Test(dataProvider = "existinguserdata",description = "Validate the successful checkout test", enabled = true)
	public void checkOutTest(String existingUserEmail, String existingUserPassword) throws InterruptedException {
		SignInPage signinpage = PageFactory.initElements(getDriver(), SignInPage.class);
		signinpage.checkOutTestValidation(existingUserEmail, existingUserPassword);
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
