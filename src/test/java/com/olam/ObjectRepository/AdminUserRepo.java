package com.olam.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olam.Runner.DriverClass;

public class AdminUserRepo {
	public static AdminUserRepo adminUserRepo = null;

	public AdminUserRepo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public static AdminUserRepo getInstance() {
		if (adminUserRepo == null) {
			adminUserRepo = new AdminUserRepo(DriverClass.driver);
		}
		return adminUserRepo;

	}

	// KM Configuration
	@FindBy(xpath = "//label[contains(text(),'Description')]//following::textarea")
	public static WebElement KmDescriptionText;

	@FindBy(xpath = "//button[@aria-label='Last page']")
	public static WebElement LastPageArrow;

	@FindBy(xpath = "//label[contains(text(),'Select Type')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement KmTypeDropdownarrow;

	@FindBy(xpath = "//label[contains(text(),'Description')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement FarmerGrpTypeDropdownarrow;

	// Session Configuration
	@FindBy(xpath = "//*[contains(text(),'Items per page:')]")
	public static WebElement ItemPerPage;

	@FindBy(xpath = "//*[contains(text(),'User Creation')]//following::div[@class='mat-select-arrow'][1]")
	public static WebElement UserDropdownarrow;

	@FindBy(xpath = "//*[contains(text(),'User Creation')]//following::div[@class='mat-select-arrow'][2]")
	public static WebElement ProductDropdownarrow;

	@FindBy(xpath = "//*[contains(text(),'User Creation')]//following::div[@class='mat-select-arrow'][3]")
	public static WebElement OriginDropdownarrow;

	@FindBy(xpath = "//*[contains(text(),'Choose open date')]//preceding::input[1]")
	public static WebElement SessionOpenDate;

	@FindBy(xpath = "//*[contains(text(),'Choose close date')]//preceding::input[1]")
	public static WebElement SessionCloseDate;

	// User Creation
	@FindBy(xpath = "//select[@name='role']")
	public static WebElement roleDropdown;

}
