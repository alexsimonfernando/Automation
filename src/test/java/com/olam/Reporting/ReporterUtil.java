package com.olam.Reporting;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReporterUtil {
	public static ReporterUtil ReporterUtil;

	public static ReporterUtil getInstance() {
		if (ReporterUtil == null) {
			ReporterUtil = new ReporterUtil();
		}
		return ReporterUtil;
	}

	public WebDriver driver;

	public static ExtentReports extent;

	public static ExtentTest scenariodef;

	public static ExtentTest feature;

}
