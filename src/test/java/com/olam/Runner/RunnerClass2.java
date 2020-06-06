package com.olam.Runner;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.olam.Reporting.ExtentReporterListener;

import cucumber.api.CucumberOptions;
import cucumber.api.cli.Main;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//public class RunnerClass extends Main {
@CucumberOptions(features = "src/test/resources/Features", glue = {
		"com.olam.StepDefinitions" }, monochrome = true, tags = { "@sanity" }, plugin = { "pretty",
				"html:target/cucumber-reports", "json:target\\cucumber.json",
				"rerun:target/cucumber-reports/rerun.txt" })
public class RunnerClass2 extends AbstractTestNGCucumberTests {
	public static WebDriver driver;
	public static byte exitstatus;
	public static RunnerClass2 RunnerClass;
	private Scenario scenario;

	private TestNGCucumberRunner testNGCucumberRunner;
	//
	// @BeforeClass(alwaysRun = true)
	// public void setUpClass() throws Exception {
	// testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	//
	// }
	//
	// @BeforeTest
	// public void beforeTest() {
	// }
	//
	//
	//
	// @Test(groups = "cucumber", description = "Runs Cucumber Feature",
	// dataProvider = "features")
	// public void feature(CucumberFeatureWrapper cucumberFeature) {
	// testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	// }
	//
	// @DataProvider
	// public Object[][] features() {
	// return testNGCucumberRunner.provideFeatures();
	// }
	//
	// @Test
	// public static void runner() throws Throwable {
	//
	//// String[] options = { "--monochrome", "-g", "com.olam.StepDefinitions",
	//// "src\\test\\resources\\Features", "-t", "@sanity", "-p", "pretty:STDOUT",
	// "-p",
	//// "json:target\\cucumber.json", "-p", "html:target\\" };
	//// exitstatus = run(options, Thread.currentThread().getContextClassLoader());
	//
	// }

	@AfterClass
	public static void afterClass() throws IOException, InterruptedException {
		DriverClass.killDriver();
		// System.exit(exitstatus);
	}

}
