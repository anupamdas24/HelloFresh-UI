package com.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.reporters.SuiteHTMLReporter;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG extends SuiteHTMLReporter {
	public ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(
				outputDirectory + File.separator + "HelloFresh_UI_TEST_Automation_Report.html", true);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	public void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				test.getTest().setStartedTime(getTime(result.getStartMillis()));
				test.getTest().setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups()) {
					test.assignCategory(group);
				}

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null) {
					message = result.getThrowable().getMessage();
				}

				if (LogStatus.FAIL.equals(status)) {
					test.log(status, message, test.addScreenCapture(getImagePath(result)));
				} else {
					test.log(status, message);
				}

				extent.endTest(test);
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

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
