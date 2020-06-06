package com.olam.StepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.olam.Reporting.ExtentReporterListener;
import com.olam.Utils.CommonClasses;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LoginSteps extends ExtentReporterListener {
	private Scenario scenario;
	private Feature featuredesc;
	String featureName = "Feature ";
	String featureScenario;
	ExtentTest logInfo = null;
	public static LoginSteps LoginSteps;

	public static LoginSteps getInstance() {
		if (LoginSteps == null) {
			LoginSteps = new LoginSteps();
		}

		return LoginSteps;

	}

	// @BeforeStep
	// public void after() {
	//
	//
	// System.out.println("after step");
	//
	// }

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
		featureName = getFeatureFileNameFromScenarioId(scenario);
		feature = extent.createTest(featureName);
		featureName = featureName.split("Feature")[1];
		extscenario = feature.createNode(featureName);

	}

	protected String getFeatureFileNameFromScenarioId(Scenario scenario) {
		String rawFeatureName = scenario.getName();
		if (rawFeatureName.equals(null) || rawFeatureName.equals(""))
			rawFeatureName = scenario.getId().split(";")[0].replace("-", " ");
		featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
		System.out.println(featureName);
		return featureName;
	}

	@Given("^Customer opens the application(.*)$")
	public void customer_enters_the_website_and_clicks_Sign_In(String str1) throws Throwable {

		try {
			logInfo = extscenario.createNode(new GherkinKeyword("Given"),
					"Customer_enters_the_website_and_clicks_Sign_In");
			logInfo.log(Status.PASS, "Opened chrome browser and entered url");
			CommonClasses.get_LoginPageFunction_Instance().lauchURL();
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Opening of chrome browser failed" + e.getMessage());
			System.out.println(e.getMessage());

		}
	}

	@When("^Customer enters email as \"([^\"]*)\" and password as \"([^\"]*)\"(.*)$")
	public void customer_enters_email_as_and_password_as(String email, String password, String str1) throws Throwable {
		try {
			Thread.sleep(3000);
			logInfo = extscenario.createNode(new GherkinKeyword("When"),
					" the Customer enter the email and password and login");
			CommonClasses.get_LoginPageFunction_Instance().loginwithValidCredentials(email, password);
			logInfo.pass("Customer logged in to the atsource portal");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Customer Login Failed" + e.getMessage());
			System.out.println(e.getMessage());

		}
	}

}
