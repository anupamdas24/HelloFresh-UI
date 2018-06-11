package com.pages;

import org.openqa.selenium.WebDriver;


public class PageContainer {

	public WebDriver driver;
	public SignInPage loginPage;
	

	public PageContainer(WebDriver driver) {
		this.driver = driver;
		initPages();
	}

	public void initPages() {

		loginPage = new SignInPage(driver);
		

	}
}
