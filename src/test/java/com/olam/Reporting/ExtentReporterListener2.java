package com.olam.Reporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.olam.Utils.CommonActions;
import com.olam.Utils.ConfigFile;
import com.olam.Utils.ConfigFile.CONFIGURATION_CONSTANT;

import cucumber.api.Scenario;

public class ExtentReporterListener2 {
	public static WebDriver driver;
	public static ExtentReporterListener2 ExtentReporter;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extscenario;
	public static ExtentTest feature;
	private Scenario scenario;
	String featureName = "Feature ";
	private static ConfigFile config = ConfigFile.getInstance();

	public static ExtentReporterListener2 getInstance() {
		if (ExtentReporter == null) {
			ExtentReporter = new ExtentReporterListener2();
		}
		return ExtentReporter;
	}

	;

	public static ExtentReports ExtentReport() throws Exception {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
		File temp = new File(workingDir, "config.properties");
		InputStream resourceAsStream = new FileInputStream(temp);
		ConfigFile.getInstance(resourceAsStream);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/" + "ExtentReport/extentReport.html");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.PROJECT.toString())));
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.start();
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "Local");
		extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Tester", System.getProperty("name"));
		extent.setSystemInfo("Browser", "chrome");
		return extent;

	}

	public static String extentReportScrnShot(WebDriver driver) throws IOException {
		driver = CommonActions.driver;
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String filepath = System.getProperty("user.dir") + "/" + "ExtentReport/Screenshots/" + getCurrentDateAndTime()
				+ "screenshot.png";
		Files.copy(scr.toPath(), new File(filepath).toPath());

		return filepath;
	}

	private static String getCurrentDateAndTime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}

}