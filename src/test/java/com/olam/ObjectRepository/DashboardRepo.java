package com.olam.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olam.Runner.DriverClass;

public class DashboardRepo extends DriverClass {
	public static DashboardRepo dashboardRepo = null;

	public DashboardRepo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public static DashboardRepo getInstance() {
		if (dashboardRepo == null) {
			dashboardRepo = new DashboardRepo(DriverClass.driver);
		}
		return dashboardRepo;

	}

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public static WebElement settings;

	@FindBy(xpath = "//label[contains(text(),'Product')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement productDropdownarrow;

	@FindBy(xpath = "//label[contains(text(),'Provided By')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement providedByDropdownarrow;

	@FindBy(xpath = "//span[@class='mat-option-text']")
	public static WebElement Dropdownhover;

	@FindBy(xpath = "//label[contains(text(),'Origin')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement originDropdownarrow;

	@FindBy(xpath = "//button[@class='save-button']")
	public static WebElement saveButton;

	@FindBy(xpath = "//button[@class='submit-button']")
	public static WebElement submitButton;

	@FindBy(xpath = "//a[contains(text(),'Download as EXCEL')]")
	public static WebElement downloadExceLink;

	@FindBy(xpath = "//td[contains(text(),'Farmer Group')]//following-sibling::td")
	public static WebElement FarmerGrpName;

	@FindBy(xpath = "//td[contains(text(),'Estate')]//following-sibling::td")
	public static WebElement EstateName;

	@FindBy(xpath = "//td[contains(text(),'Current Data Year')]//following-sibling::td")
	public static WebElement CurrentYear;

	@FindBy(xpath = "//td[contains(text(),'Baseline Data Year')]//following-sibling::td")
	public static WebElement BaselineYear;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public static WebElement okButton;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public static WebElement cancelButton;

	@FindBy(xpath = "//img[@alt='Log off']")
	public static WebElement logOffButton;

	@FindBy(xpath = "//h2[@id='swal2-title']")
	public static WebElement popUpWindow;

	@FindBy(xpath = "//*[contains(text(),'Please give a full explanation')]//following::textarea")
	public static WebElement rejectComments;

}
