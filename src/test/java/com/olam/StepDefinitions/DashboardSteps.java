package com.olam.StepDefinitions;

import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.olam.ObjectRepository.AdminUserRepo;
import com.olam.ObjectRepository.DashboardRepo;
import com.olam.PageFunctions.AdminUserFunction;
import com.olam.PageFunctions.CommonPageFunction;
import com.olam.Reporting.ExtentReporterListener;
import com.olam.Utils.CommonActions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DashboardSteps extends ExtentReporterListener {
	static ExtentTest logInfo;

	@When("^user enters the below data in column \"([^\"]*)\"(.*)$")
	public void user_enters_the_below_data_in_column(String str, String str1, DataTable data) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer enter the value in the column");
			CommonPageFunction.getInstance().setInputData(str, data);
			logInfo.pass("Customer sucessfully entered the data");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to fill the data" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user reject the below data in column \"([^\"]*)\"(.*)$")
	public void user_rejects_the_below_data_in_column(String str, String str1, DataTable data) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer enter the value in the column");
			CommonPageFunction.getInstance().rejectData(str, data);
			logInfo.pass("Customer sucessfully rejected the selected data");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to reject the selected" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user selects the KM \"([^\"]*)\"(.*)$")
	public void user_selects_the_KM(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer selects the KM");
			AdminUserFunction.getInstance().selectKmByName(str);
			CommonActions.getInstance().scrollToToOfPage();
			logInfo.pass("Customer sucessfully selected the KM");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the KM" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user selects the product \"([^\"]*)\"(.*)$")
	public void user_selects_the_product(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer select the product" + str);
			CommonPageFunction.getInstance().selectProductDropDown(str);
			logInfo.pass("Customer sucessfully seleted the product " + str);
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the product" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user \"([^\"]*)\" the user \"([^\"]*)\"(.*)$")
	public void delete_or_edit_user(String str, String str1, String str2)
			throws InterruptedException, ClassNotFoundException, IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer clicks the " + str);
			AdminUserFunction.getInstance().deleteOrEditUserByEmail(str, str1);
			logInfo.pass("Customer sucessfully clicked on the element");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to click the element" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}

	}

	@When("^user clicks on the \"([^\"]*)\" (.*)$")
	public void user_clicks_the_element(String str, String str1) throws IOException {
		try {
			Thread.sleep(3000);
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer clicks the " + str);
			CommonPageFunction.getInstance().clickElement(str);
			Thread.sleep(3000);
			logInfo.pass("Customer sucessfully clicked on the element");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to click the element" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@Then("^verify the \"([^\"]*)\" text (.*)$")
	public void verify_the_details(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("Then"), "verify the" + str + str1);
			CommonPageFunction.getInstance().verifyElement(str);
			logInfo.pass("Customer sucessfully verified the element");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Unable to find the element" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user fill the below data(.*)$")
	public void user_fill_the_data(String str, DataTable data) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "User fill the data");
			CommonPageFunction.getInstance().fillData(data);
			logInfo.pass("Customer sucessfully entered the data");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to fill the data" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());

		}
	}

	@When("^user selects the origin \"([^\"]*)\"(.*)$")
	public void user_selects_the_origin(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer select the origin" + str);
			CommonPageFunction.getInstance().selectOriginDropDown(str);
			logInfo.pass("Customer sucessfully seleted the origin " + str);
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the origin" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user selects the providedby \"([^\"]*)\"(.*)$")
	public void user_selects_the_providedBy(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer select the origin" + str);
			CommonPageFunction.getInstance().selectProvidedByDropDown(str);
			logInfo.pass("Customer sucessfully seleted the origin " + str);
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the origin" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user save the campaign(.*)$")
	public void user_save_the_campaign(String str1) throws IOException {
		try {
			logInfo = extscenario.createNode("Customer click submit button");
			CommonPageFunction.getInstance().clickSave();
			// CommonPageFunction.getInstance().clickSubmit();
			logInfo.pass("Customer sucessfully saved the campaign ");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to save the campaign" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user selects the KmType \"([^\"]*)\" and famergroup type \"([^\"]*)\" from the dropdown$")
	public void user_selects_KMtype_and_farmergroup(String str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode("User selects the KmType and faremergroup type");
			AdminUserFunction.getInstance().selectKmTypeDropDown(str);
			AdminUserFunction.getInstance().selectFarmerGrpTypeDropDown(str1);
			CommonActions.getInstance().sendKeysAction(AdminUserRepo.KmDescriptionText, "Testing");
			logInfo.pass("Customer sucessfully entered the details ");
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the value" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user selects the role \"([^\"]*)\" from the dropdown$")
	public void user_select_the_dropdown(String str) throws IOException {
		try {
			logInfo = extscenario.createNode("Customer selects the value from the dropdown");
			AdminUserRepo.getInstance();
			CommonActions.getInstance().selectByVisibleText(AdminUserRepo.roleDropdown, str);
			logInfo.pass("Customer seleted the dropdown successfully");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to select the dropdown" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user logout of the application(.*)$")
	public void logout_application(String str1) throws Exception {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer click logout button");
			CommonActions.getInstance().clickAction(DashboardRepo.logOffButton);
			CommonActions.getInstance().clickAction(DashboardRepo.okButton);
			Thread.sleep(3000);
			logInfo.pass("Customer sucessfully logged out of the application ");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to logout of the application" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));

			System.out.println(e.getMessage());

		}
	}

	@Then("^verify the \"([^\"]*)\" pop up message is displayed(.*)$")
	public void verify_is_displayed(String msg, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("Then"), "Customer verifies " + msg + "is displayed");
			CommonActions.getInstance().verifyText(DashboardRepo.popUpWindow, msg);
			logInfo.pass("Pop up Message" + msg + "is displayed");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Verification of pop message Failed" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user enters the session details \"([^\"]*)\" (.*)$")
	public void user_inputs_the_session_details(List<String> str, String str1) throws IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer enter the session details");
			AdminUserFunction.getInstance().sessionConfiguration(str);
			logInfo.pass("Customer sucessfully entered the data");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to enter the data" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}
	}

	@When("^user \"([^\"]*)\" the session of the combination \"([^\"]*)\"(.*)$")
	public void delete_or_edit_session(String operation, List<String> sessiondata, String step)
			throws InterruptedException, ClassNotFoundException, IOException {
		try {
			logInfo = extscenario.createNode(new GherkinKeyword("When"), "Customer try to delete the session" + step);
			AdminUserFunction.getInstance().deleteOrEditSession(operation, sessiondata);
			logInfo.pass("Customer sucessfully deleted the session");
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, false));
		} catch (AssertionError | Exception e) {
			logInfo.log(Status.FAIL, "Failed to delete the session" + e.getMessage());
			logInfo.addScreenCaptureFromPath(extentReportScrnShot(driver, true));
			System.out.println(e.getMessage());
		}

	}
}
