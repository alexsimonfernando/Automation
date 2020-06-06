package com.olam.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olam.Runner.DriverClass;

public class LoginRepo extends DriverClass {

	public static LoginRepo loginRepo = null;

	// Page intialize
	public LoginRepo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Create Singelton Class

	public static LoginRepo getInstance() {
		if (loginRepo == null) {
			loginRepo = new LoginRepo(DriverClass.driver);
		}
		return loginRepo;

	}

	@FindBy(id = "user-name")
	public static WebElement userName;

	@FindBy(id = "password-field")
	public static WebElement password;

	@FindBy(xpath = "//button[contains(text(),'LOG IN')]")
	public static WebElement loginButton;

}
