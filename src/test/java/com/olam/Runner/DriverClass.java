package com.olam.Runner;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.olam.ObjectRepository.LoginRepo;
import com.olam.Utils.ConfigFile;
import com.olam.Utils.ConfigFile.CONFIGURATION_CONSTANT;

public class DriverClass {
	public static WebDriver driver;
	private static ConfigFile config = ConfigFile.getInstance();
	static String path = null;
	public static DriverClass driverInstance = null;
	static String browser;

	public static DriverClass getInstance() {
		if (driverInstance == null) {
			driverInstance = new LoginRepo(DriverClass.driver);
		}
		return driverInstance;

	}

	public static WebDriver getDriver(String browser) {
		if (driver == null) {
			if (browser.equalsIgnoreCase("chrome")) {

				path = System.getProperty("user.dir") + ConfigFile.getAbsoluteFileOrDirectoryPath(
						config.getConfigurationValue(CONFIGURATION_CONSTANT.CHROME_DRIVER_LOCATION.toString()));
				path = path.replaceAll("^\\$", "^\\\\$");
				System.setProperty("webdriver.chrome.driver", path);
				driver = new ChromeDriver();
				driver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("firefox")) {
				path = System.getProperty("user.dir") + ConfigFile.getAbsoluteFileOrDirectoryPath(
						config.getConfigurationValue(CONFIGURATION_CONSTANT.FIREFOX_DRIVER_LOCATION.toString()));
				path = path.replaceAll("^\\$", "^\\\\$");
				System.setProperty("webdriver.gecko.driver", path);
				driver = new FirefoxDriver();
				driver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("ie")) {
				path = System.getProperty("user.dir") + ConfigFile.getAbsoluteFileOrDirectoryPath(
						config.getConfigurationValue(CONFIGURATION_CONSTANT.IE_DRIVER_LOCATION.toString()));
				path = path.replaceAll("^\\$", "^\\\\$");
				System.setProperty("webdriver.ie.driver", path);
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

			}

			else if (browser.equalsIgnoreCase("edge")) {
				path = System.getProperty("user.dir") + ConfigFile.getAbsoluteFileOrDirectoryPath(
						config.getConfigurationValue(CONFIGURATION_CONSTANT.EDGE_DRIVER_LOCATION.toString()));
				path = path.replaceAll("^\\$", "^\\\\$");
				System.setProperty("webdriver.edge.driver", path);

				driver = new EdgeDriver();

				driver.manage().window().maximize();

			}
		}
		return driver;

	}

	public static void quitdriver() {
		driver.quit();
	}

	public static void killDriver() throws IOException, InterruptedException {
		browser = ConfigFile.getAbsoluteFileOrDirectoryPath(
				config.getConfigurationValue(CONFIGURATION_CONSTANT.BROWSER.toString()));
		if (browser.equalsIgnoreCase("chrome")) {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		} else if (browser.equalsIgnoreCase("firefox")) {
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		} else if (browser.equalsIgnoreCase("ie")) {
			Runtime.getRuntime().exec("taskkill /F /IM ieexplorer.exe");
		} else if (browser.equalsIgnoreCase("edge"))
			Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
		// driver=null;
		Thread.sleep(3000);

	}
}
