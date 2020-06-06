package com.olam.Runner;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/Features", strict = true, dryRun = false, glue = {
		"com.olam.StepDefinitions" }, monochrome = true, tags = { "@test" }, plugin = { "pretty",
				"html:target/cucumber-reports", "json:target\\cucumber.json",
				"rerun:target/cucumber-reports/rerun.txt" })

public class RunnerClass extends AbstractTestNGCucumberTests {
	public static WebDriver driver;
	public static byte exitstatus;
	public static RunnerClass RunnerClass;

	@AfterClass
	public static void afterClass() throws IOException, InterruptedException {

		DriverClass.killDriver();
	}

}
