package com.pages;

import org.openqa.selenium.WebDriver;

public class PageContainer {

	public WebDriver driver;
	public LoginPage loginPage;


	public PageContainer(WebDriver driver) {
		this.driver = driver;
		initPages();
	}

	public void initPages() {

		loginPage = new LoginPage(driver);

	}

}
