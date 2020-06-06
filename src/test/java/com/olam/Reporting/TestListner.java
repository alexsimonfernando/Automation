package com.olam.Reporting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.olam.Runner.DriverClass;

public class TestListner implements ITestListener {
	public static WebDriver driver;
	public boolean isfail = true;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure");
		result.getStatus();
		DriverClass.getInstance();
		try {
			ExtentReporterListener.extentReportScrnShot(driver, isfail);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test Success");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test Skip");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("On Test failed within success percentage");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("On Start");
		try {
			ExtentReporterListener.ExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On finish");
		// ExtentReporterListener.extent.
		ExtentReporterListener.extent.flush();
	}

}
