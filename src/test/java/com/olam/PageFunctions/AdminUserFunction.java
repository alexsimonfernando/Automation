package com.olam.PageFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olam.ObjectRepository.AdminUserRepo;
import com.olam.ObjectRepository.DashboardRepo;
import com.olam.Utils.CommonActions;
import com.olam.Utils.CommonClasses;

public class AdminUserFunction extends CommonClasses {

	public static AdminUserFunction adminUserFunction;
	public static WebDriver driver;

	public static AdminUserFunction getInstance() {
		if (adminUserFunction == null) {
			adminUserFunction = new AdminUserFunction();
		}
		get_DashboardRepo_Instance();
		return adminUserFunction;
	}

	public AdminUserFunction() {
		get_DashboardRepo_Instance();
		get_AdminRepo_Instance();
		get_CommonActions_Instance();
		driver = CommonActions.driver;
	}

	public void selectKmTypeDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(AdminUserRepo.KmTypeDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void selectFarmerGrpTypeDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(AdminUserRepo.FarmerGrpTypeDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void deleteOrEditUserByEmail(String option, String str) throws InterruptedException {
		WebElement element = null;
		if (option.equalsIgnoreCase("delete"))
			element = driver.findElement(By.xpath("//*[contains(text(),'" + str
					+ "')]//following-sibling::td//*[contains(text(),'delete_forever')]"));
		else if (option.equalsIgnoreCase("edit"))
			element = driver.findElement(
					By.xpath("//*[contains(text(),'" + str + "')]//following-sibling::td//*[contains(text(),'edit')]"));
		get_CommonActions_Instance().clickAction(element);
	}

	public void selectKmByName(String str) throws InterruptedException {
		WebElement element = null;
		String xpath;
		xpath = get_CommonActions_Instance().getXPathFromElement(AdminUserRepo.LastPageArrow);
		xpath = xpath.split("xpath:")[1];
		element = driver.findElement(By.xpath(xpath));
		while (element.isEnabled()) {
			element.click();
		}
		Thread.sleep(3000);
		element = driver.findElement(
				By.xpath("//*[contains(text(),'" + str + "')]//preceding::div[@class='mat-radio-outer-circle'][1]"));
		get_CommonActions_Instance().clickAction(element);
	}

	public void selectOriginDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(AdminUserRepo.OriginDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void selectProductDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(AdminUserRepo.ProductDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void selectUserDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(AdminUserRepo.UserDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void sessionConfiguration(List<String> data) throws InterruptedException {
		String user = data.get(0).trim();
		String product = data.get(1).trim();
		String origin = data.get(2).trim();
		selectUserDropDown(user);
		selectProductDropDown(product);
		selectOriginDropDown(origin);
		get_CommonActions_Instance().sendKeysAction(AdminUserRepo.SessionOpenDate, data.get(3).trim());
		CommonActions.getInstance().clickAction(AdminUserRepo.ItemPerPage);
		get_CommonActions_Instance().sendKeysAction(AdminUserRepo.SessionCloseDate, data.get(4).trim());
		CommonActions.getInstance().clickAction(AdminUserRepo.ItemPerPage);
	}

	public String dateConversion(String date) throws ParseException {
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		date = date1.toString();
		date = date.substring(4, 10) + ", " + date.substring(24, 28);
		return date;
		// Tue Jan 28 00:00:00 IST 2020

	}

	public void deleteOrEditSession(String option, List<String> data) throws InterruptedException, ParseException {
		WebElement element = null;
		String openDate = data.get(3).trim();
		String closeDate = data.get(4).trim();
		if (option.equalsIgnoreCase("delete"))
			element = driver.findElement(By.xpath(
					"(//td[contains(text(),'" + data.get(0).trim() + "')]//following-sibling::td[contains(text(),'"
							+ data.get(1).trim() + "')]" + "//following-sibling::td[contains(text(),'"
							+ data.get(2).trim() + "')]//following-sibling::td[contains(text(),'"
							+ dateConversion(openDate) + "')]" + "//following-sibling::td[contains(text(),'"
							+ dateConversion(closeDate) + "')]//following-sibling::td[1]//*)[2]"));
		else if (option.equalsIgnoreCase("edit"))
			element = driver.findElement(By.xpath(
					"(//td[contains(text(),'" + data.get(0).trim() + "')]//following-sibling::td[contains(text(),'"
							+ data.get(1).trim() + "')]" + "//following-sibling::td[contains(text(),'"
							+ data.get(2).trim() + "')]//following-sibling::td[contains(text(),'"
							+ dateConversion(openDate) + "')]" + "//following-sibling::td[contains(text(),'"
							+ dateConversion(closeDate) + "')]//following-sibling::td[1]//*)[1]"));
		get_CommonActions_Instance().clickAction(element);

	}
}
