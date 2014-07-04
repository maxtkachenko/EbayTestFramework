package com.home.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.home.automationdriver.Driver;

public class TestExecutionListener extends TestListenerAdapter {

	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";
	
	private void printTestResults(ITestResult result) {

		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			TestStepReporter.reportln("Test Method had the following parameters : ", params);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
			break;
		}
		Reporter.log("Test Status: " + status, true);
		takeScreenshot(result);
		//TestStepReporter.reportln("Test Status after execution: ", status);
	}

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
	}
	WebDriver driver;
	public void takeScreenshot(ITestResult result){
		String folder = SCREENSHOT_FOLDER + result.getName();
		File dir = new File(folder);
		if (!dir.exists()){
			dir.mkdir();
		}
		try {
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			//TODO get screenshot
			Thread.sleep(3000);
			if(driver == null){
				driver = Driver.getDriver();
			}
			if(driver == null){
				return;
			}
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileName = result.getName() + "_" + formater.format(Calendar.getInstance().getTime()) + SCREENSHOT_FORMAT;
			FileUtils.copyFile(f, new File(dir.getAbsoluteFile() + "/" + fileName));
			File directory = new File(".");
     		String newFileNamePath = directory.getCanonicalPath();
     		Reporter.log("<a href=" + newFileNamePath + "/" + folder + "/" + fileName + " target='_blank' >" +
     					 "<p><br/><img src=\"file:///" + newFileNamePath + "/" + folder + "/" + fileName + 
     					 "\" width=\"600\" height=\"338\" alt=\"\"/>" +
     					 "<br/></p></a>", true);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}              
}
	public void createTestDirectory(ITestResult result){
		System.out.println(result.getTestClass().getRealClass().getSimpleName());//get the classs name for grouping tests in class folders
		File dir = new File(SCREENSHOT_FOLDER + result.getName());
		if (!dir.exists()){
			dir.mkdir();
		}
	
	}
}
