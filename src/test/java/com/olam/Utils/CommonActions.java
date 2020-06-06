package com.olam.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.olam.Runner.DriverClass;
import com.olam.Utils.ConfigFile.CONFIGURATION_CONSTANT;

import junit.framework.Assert;

public class CommonActions {

	public static WebDriver driver;

	public static CommonActions actionItem = null;

	private static ConfigFile config = ConfigFile.getInstance();

	public CommonActions(WebDriver driver) {
		CommonActions.driver = driver;

	}

	public static CommonActions getInstance() {
		if (actionItem == null) {
			actionItem = new CommonActions(DriverClass.driver);
		}
		return actionItem;
	}

	/// Navigate URL

	public void navigateToURL() throws Exception {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
		File temp = new File(workingDir, "config.properties");
		InputStream resourceAsStream = new FileInputStream(temp);
		ConfigFile.getInstance(resourceAsStream);
		driver = DriverClass.getDriver(ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.BROWSER.toString())));
		System.out.println(driver);
		driver.get(ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.APPLICATION_URL.toString())));
		// linkCheck(driver);

	}

	// Sleep

	public void sleep(int seconds) throws InterruptedException {
		Thread.sleep(seconds);
	}

	// Click Action

	public void clickAction(WebElement element) throws InterruptedException {
		element.click();

	}

	// Input text
	public void setInput(WebElement element, String str) throws InterruptedException {
		element.sendKeys(str);

	}

	// Send Action

	public void sendKeysAction(WebElement element, String inputText) {
		element.sendKeys(inputText);

	}

	// Clear Action

	public void clearAction(WebElement element) {
		element.clear();

	}

	// get text from element by attribute
	public String getTextUsingAttribute(WebElement element, String attributeValue) {
		String text = element.getAttribute(attributeValue);
		return text;

	}

	// get size of the elements
	public int getSizeByXpath(String xpath) {
		int size = driver.findElements(By.xpath(xpath)).size();
		return size;

	}

	// get text of the element

	public String getText(WebElement element) {
		String text = element.getText();
		return text;

	}

	// Implicit wait

	public void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	public static List findAllLinks(WebDriver driver) {
		List<WebElement> elementList = new ArrayList();
		elementList = driver.findElements(By.tagName("a"));
		elementList.addAll(driver.findElements(By.tagName("img")));
		List finalList = new ArrayList();
		;
		for (WebElement element : elementList) {
			if (element.getAttribute("href") != null) {
				finalList.add(element);
			}
		}
		return finalList;
	}

	public static void linkCheck(WebDriver driver) throws Exception {

		driver.get(ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.APPLICATION_URL.toString())));

		// ff.get("http://www.yahoo.com/");

		List<WebElement> allImages = findAllLinks(driver);

		System.out.println("Total number of elements found " + allImages.size());

		for (WebElement element : allImages) {

			try

			{
				System.out.println("URL: " + element.getAttribute("href") + " returned "
						+ isLinkBroken(new URL(element.getAttribute("href"))));
				// System.out.println("URL: " + element.getAttribute("outerhtml")+ " returned "
				// + isLinkBroken(new URL(element.getAttribute("href"))));
			} catch (Exception exp) {
				System.out.println(
						"At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
			}
		}
	}

	public static String isLinkBroken(URL url) throws Exception {
		// url = new URL("https://yahoo.com");
		String response = "";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.connect();
			response = connection.getResponseMessage();
			connection.disconnect();
			return response;
		} catch (Exception exp) {
			return exp.getMessage();
		}
	}

	// Tab Keys

	public void pressKeys(Keys key) {
		driver.switchTo().activeElement().sendKeys();
	}

	// moveToElement using action Method

	public void moveToElement(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	// Send keys using active element

	public void typeIntoActiveElement(String inputText) {
		driver.switchTo().activeElement().sendKeys(inputText);

	}

	// scroll by visible element

	public void scrollToMakeElementVisible(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	// scroll to the bottom of the page

	public void scrollToBottomOfPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// scroll to the top of the page

	public void scrollToToOfPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
	}

	// wait till element clickable

	public void verifyElementClickableThenClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 600);
		// wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public boolean isEnabled(WebElement element) {
		boolean flag = element.isEnabled();
		return flag;

	}

	public boolean isDisplayed(WebElement element) {
		boolean flag = element.isDisplayed();
		return flag;

	}

	public boolean isSelected(WebElement element) {
		boolean flag = element.isSelected();
		return flag;

	}

	public void selectByVisibleText(WebElement element, String text) {
		Select se = new Select(element);
		se.selectByVisibleText(text);

	}

	public void listDropDown(WebElement b, String text) {
		try {
			String path = getXPathFromElement(b);
			String finalpath = path.split("xpath:")[1];
			List<WebElement> myElements = driver.findElements(By.xpath(finalpath));
			for (WebElement e : myElements) {
				if (e.getText().equalsIgnoreCase(text)) {
					e.click();
					break;
				} else {
					e.equals(false);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String getXPathFromElement(WebElement element) {
		String elementDescription = element.toString();
		return elementDescription.substring(elementDescription.lastIndexOf("-> ") + 3,
				elementDescription.lastIndexOf("]"));
	}

	public void selectByValue(WebElement element, String Value) {
		Select se = new Select(element);
		se.selectByValue(Value);

	}

	public void waitTillClickable(WebElement element) {

		JavascriptExecutor ex = (JavascriptExecutor) driver;
		ex.executeScript("arguments[0].click()", element);
	}

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void selectDate(String date) {
		driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='" + date + "']")).click();

	}

	public void pageTitleValidation(String actualTitle) {
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	public int randomNumberGenerator(int size) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, size + 1);
		// Generates 10 Random Numbers in the range 1 -20
		return randomNum;

	}

	public String currentDate() {
		LocalDate localDate = LocalDate.now();
		String date = DateTimeFormatter.ofPattern("dd").format(localDate);

		return date;

	}

	public int randNum() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 9999 + 1);
		// Generates 10 Random Numbers in the range 1 -20
		return randomNum;

	}

	public String getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
		return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

	}

	public int getNumberOfDaysInCurrentMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;

	}

	// verify text of the element
	public boolean verifyText(WebElement element, String msg) {
		String text = element.getText();
		if (text.equalsIgnoreCase(msg))
			return true;
		else
			return false;
	}

	public String getDay(String date) throws ParseException {
		Calendar c = Calendar.getInstance();
		String input = date;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = format1.parse(input);
		return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(dt1.getTime());

	}

	public int randomNumber() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 9999 + 1);
		// Generates 10 Random Numbers in the range 1 -20
		return randomNum;

	}

}
