package com.olam.Reporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.olam.Utils.CommonActions;
import com.olam.Utils.ConfigFile;
import com.olam.Utils.ConfigFile.CONFIGURATION_CONSTANT;

public class ExtentReporterListener {
	public static WebDriver driver;
	public static ExtentReporterListener ExtentReporter;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extscenario;
	public static ExtentTest feature;
	String featureName = "Feature ";
	private static ConfigFile config = ConfigFile.getInstance();
	static String screenShot;

	public static ExtentReporterListener getInstance() {
		if (ExtentReporter == null) {
			ExtentReporter = new ExtentReporterListener();
		}
		return ExtentReporter;
	}

	;

	public static void ExtentReport() throws Exception {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
		File temp = new File(workingDir, "config.properties");
		InputStream resourceAsStream = new FileInputStream(temp);
		ConfigFile.getInstance(resourceAsStream);
		screenShot = ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.SCREENSHOT.toString()));
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/" + "ExtentReport/extentReport.html");
		htmlReporter.loadConfig(System.getProperty("user.dir") + "/ExtentReport/extent-config.xml");
		htmlReporter.config().setDocumentTitle("Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(ExtentReporterListener.htmlReporter);
		extent.setSystemInfo("HostName", "Local");
		extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Tester", System.getProperty("name"));
		extent.setSystemInfo("Browser", "chrome");

	}

	public static String extentReportScrnShot(WebDriver driver, boolean isfail) throws IOException {

		if (screenShot.equalsIgnoreCase("PASS") || isfail) {
			driver = CommonActions.driver;
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filepath = System.getProperty("user.dir") + "/" + "ExtentReport/Screenshots/"
					+ getCurrentDateAndTime() + "screenshot.png";
			Files.copy(scr.toPath(), new File(filepath).toPath());

			return filepath;
		}
		return ".";
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