package com.olam.PageFunctions;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olam.ObjectRepository.AdminUserRepo;
import com.olam.ObjectRepository.DashboardRepo;
import com.olam.Utils.CommonActions;
import com.olam.Utils.CommonClasses;

import cucumber.api.DataTable;

public class CommonPageFunction extends CommonClasses {

	public static CommonPageFunction commonPageFunction;
	public static WebDriver driver;

	public CommonPageFunction() {
		get_DashboardRepo_Instance();
		get_AdminRepo_Instance();
		get_CommonActions_Instance();
		driver = CommonActions.driver;
	}

	public static CommonPageFunction getInstance() {
		if (commonPageFunction == null) {
			commonPageFunction = new CommonPageFunction();
		}

		return commonPageFunction;
	}

	public void selectProductDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(DashboardRepo.productDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void selectOriginDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(DashboardRepo.originDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void selectProvidedByDropDown(String str) throws InterruptedException {
		get_CommonActions_Instance().clickAction(DashboardRepo.providedByDropdownarrow);
		get_CommonActions_Instance().listDropDown(DashboardRepo.Dropdownhover, str);
		Thread.sleep(3000);
	}

	public void clickSubmit() throws InterruptedException {
		Thread.sleep(3000);
		get_CommonActions_Instance().scrollToToOfPage();
		Thread.sleep(3000);
		get_CommonActions_Instance().clickAction(DashboardRepo.submitButton);
		Thread.sleep(3000);
	}

	public void clickSave() throws InterruptedException {
		Thread.sleep(2000);
		get_CommonActions_Instance().scrollToToOfPage();
		Thread.sleep(2000);
		get_CommonActions_Instance().clickAction(DashboardRepo.saveButton);
		// get_CommonActions_Instance().scrollToToOfPage();
	}

	/**
	 * This method is used to fill data against the KM's and values on the given
	 * column no
	 * 
	 * @param String-
	 *            Column Number in string
	 * @param Datatable
	 *            - Field and values
	 * @return no return
	 * @author Francis
	 * 
	 */
	public void setInputData(String str, DataTable data) throws InterruptedException {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		int i = 0;
		String kmName = null;
		String value = null;
		ListIterator<Map<String, String>> iterator = list.listIterator();
		WebElement element = null;
		while (iterator.hasNext()) {
			kmName = (list.get(i).get("Field"));
			value = (list.get(i).get("Value"));
			iterator.next();
			i++;
			element = driver.findElement(
					By.xpath("//td[contains(text(),' " + kmName + " ')]//following-sibling::td[" + str + "]"));
			get_CommonActions_Instance().clearAction(element);
			get_CommonActions_Instance().sendKeysAction(element, value);

		}
	}

	public void rejectData(String str, DataTable data) throws InterruptedException {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		int i = 0;
		String kmName = null;
		String value = null;
		ListIterator<Map<String, String>> iterator = list.listIterator();
		WebElement element = null;
		while (iterator.hasNext()) {
			kmName = (list.get(i).get("Field"));
			value = (list.get(i).get("Value"));
			iterator.next();
			i++;
			element = driver.findElement(
					By.xpath("//td[contains(text(),'" + kmName + "')]//following-sibling::td[" + str + "]"));
			get_CommonActions_Instance().clickAction(element);
			get_CommonActions_Instance().scrollToToOfPage();
			get_CommonActions_Instance().sendKeysAction(DashboardRepo.rejectComments, value);

		}

	}

	/**
	 * This method is used to fill data against the input text fields with the label
	 * name
	 * 
	 * 
	 * @param Datatable
	 *            - Field(exact label name) and values
	 * @return no return
	 * @author Francis
	 * 
	 */

	public void fillData(DataTable data) throws InterruptedException {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		int i = 0;
		String label = null;
		String value = null;
		ListIterator<Map<String, String>> iterator = list.listIterator();
		WebElement element = null;
		while (iterator.hasNext()) {
			label = (list.get(i).get("Field"));
			value = (list.get(i).get("Value"));
			iterator.next();
			i++;
			element = driver.findElement(By.xpath("//label[contains(text(),'" + label + "')]//following::input[1]"));
			get_CommonActions_Instance().sendKeysAction(element, value);

		}
	}

	public void clickElement(String str) throws InterruptedException {
		WebElement element = null;
		element = driver.findElement(By.xpath("//*[(text()='" + str + "')]"));
		get_CommonActions_Instance().clickAction(element);
	}

	public boolean verifyElement(String str) throws InterruptedException {
		WebElement element = null;
		element = driver.findElement(By.xpath("//*[contains(text(),'" + str + "')]"));
		return element.isDisplayed();
	}

}
