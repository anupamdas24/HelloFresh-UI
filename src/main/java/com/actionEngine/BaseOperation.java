package com.actionEngine;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.pages.PageContainer;
import com.utilities.ExcelDP;
import com.utilities.Getconfig;

public class BaseOperation{

	public ExcelDP readExcel;

	public static WebDriver driver;
	protected String browser;
	public PageContainer container;

	@BeforeClass
	@Parameters("BROWSER")

	public void launhOS(String browser) throws MalformedURLException {
		getExtentConfigXML();
		if (System.getProperty("os.name").toLowerCase().contains("win") == true) {
			System.out.println("We are now in Windows !! Welcome !!!");
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
						+ "drivers" + File.separator + "chrome_win32" + File.separator + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				maximize();
				driver.get(Getconfig.getProperties("URL"));

			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "drivers"
						+ File.separator + "iedriver_win32" + File.separator + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				maximize();
				driver.get(Getconfig.getProperties("URL"));
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
						+ File.separator + "gecko_win32" + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
				maximize();
				driver.get(Getconfig.getProperties("URL"));
			}
		} else if (System.getProperty("os.name").toLowerCase().contains("mac") == true) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
						+ "drivers" + File.separator + "chrome_mac" + File.separator + "chromedriver");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				maximize();
				driver.get(Getconfig.getProperties("URL"));
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
						+ File.separator + "gecko_mac" + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
				maximize();
				driver.get(Getconfig.getProperties("URL"));
			}
		} else if ((System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux")) == true) {
			System.out.println("Running on Linux Operating System");
			String seleniumHub = System.getProperty("seleniumHub");
			System.out.println(seleniumHub);
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
						+ "drivers" + File.separator + "chrome_linux64" + File.separator + "chromedriver");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				maximize();
				driver.get(Getconfig.getProperties("URL"));
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
						+ File.separator + "gecko_linux64" + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
				maximize();
				driver.get(Getconfig.getProperties("URL"));
		}
		// Initiate page driver, to initiates all page objects
		container = new PageContainer(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		}
	}

	public static void maximize() {
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point position = new Point(0, 0);
		driver.manage().window().setPosition(position);
		Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(maximizedScreenSize);
		driver.manage().window().maximize();

	}

	public static WebDriver getDriver() {
		if (driver == null)
			throw new RuntimeException("We have not instantiated the driver.");
		return driver;
	}

	public void getExtentConfigXML() {
		try {
			com.utilities.ExtentXmlUtility.extentXmlGenerate();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(getImagePath(testResult)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getImagePath(ITestResult testResult) {

		String path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "images";
		File filePath = new File(path);
		filePath.mkdirs();

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(testResult.getEndMillis()));
		String filePathName = filePath.getAbsolutePath() + File.separator + testResult.getName() + timeStamp + ".png";
		return filePathName;
	}

	@AfterSuite(alwaysRun = true)
	public void afterTest() {
		driver.quit();
	}

}
