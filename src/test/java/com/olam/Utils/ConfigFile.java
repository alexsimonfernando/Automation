package com.olam.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class ConfigFile {
	static HashMap<String, String> configurationMap = new HashMap<String, String>();
	private boolean isBuilt = false;

	public enum CONFIGURATION_CONSTANT {
		APP_RUN_ENVIRONMENT("env"), IE_DRIVER_LOCATION("ie_driver_location"), EDGE_DRIVER_LOCATION(
				"edge_driver_location"), BROWSER("browser"), PROJECT("project"), CHROME_DRIVER_LOCATION(
						"chrome_driver_location"), FIREFOX_DRIVER_LOCATION("firefox_driver_location"), APPLICATION_URL(
								"application_url"), DEFAULT_HIGHLIGHT_OBJECT_BEFORE_ACTION(
										"highlight"), SCREENSHOT("screenshot");

		private String value;

		CONFIGURATION_CONSTANT(String value) {
			this.value = value;
		}

		public static CONFIGURATION_CONSTANT fromString(String value) {
			value = value.toUpperCase().trim();
			CONFIGURATION_CONSTANT toReturn = null;
			for (CONFIGURATION_CONSTANT each : values()) {
				if (each.value().toUpperCase().trim().equals(value)) {
					toReturn = each;
					break;
				}
			}
			return toReturn;
		}

		public String value() {
			return this.value;
		}
	}

	private static final ThreadLocal<ConfigFile> config = new ThreadLocal<ConfigFile>() {
		protected ConfigFile initialValue() {
			return new ConfigFile();
		}
	};
	static ConfigFile env = (ConfigFile) config.get();

	public static ConfigFile getInstance() {
		env = (ConfigFile) config.get();
		if (!env.isBuilt)
			;

		return env;
	}

	public HashMap<String, String> getConfigurationMap() {
		return ConfigFile.configurationMap;
	}

	public void setConfigurationValue(String env, String value) {
		ConfigFile.configurationMap.put(env, value);
	}

	public String getConfigurationValue(String envConstant) {
		String toReturn = null;
		envConstant = envConstant.toLowerCase();
		if (ConfigFile.configurationMap.containsKey(envConstant)) {
			toReturn = (String) ConfigFile.configurationMap.get(envConstant);
		}
		return toReturn;
	}

	private void build(InputStream is) throws IOException {
		ConfigFile.configurationMap.clear();
		loadConfiguration(is);
	}

	private void loadConfiguration(InputStream is) throws IOException {
		Properties runProp = new Properties();
		runProp.load(is);
		Set<Object> properties = runProp.keySet();
		for (Object prop : properties) {
			CONFIGURATION_CONSTANT temp = null;
			if ((temp = CONFIGURATION_CONSTANT.fromString(prop.toString())) != null) {
				addConfig(temp.value(), runProp.getProperty(prop.toString()));
				continue;
			}
			addConfig(prop.toString().trim().toLowerCase(), runProp.getProperty(prop.toString()));
		}
	}

	private void addConfig(String prop, String value) {
		ConfigFile.configurationMap.put(prop, value);
	}

	public static ConfigFile getInstance(InputStream is) throws Exception {

		env.build(is);
		env.isBuilt = true;
		return env;
	}

	public static String getAbsoluteFileOrDirectoryPath(String filePath) {
		return getAbsoluteFileOrDirectoryPath(filePath, false);
	}

	public static String getAbsoluteFileOrDirectoryPath(String filePath, boolean copyJarResFileToTempLoc) {
		if (filePath == null) {
			return null;
		}
		String tempFilePath = filePath;
		String toReturn;
		boolean filePathFound = false;
		tempFilePath = tempFilePath.trim();
		File fl = new File(tempFilePath);
		toReturn = tempFilePath;
		tempFilePath = tempFilePath.replaceAll("\\\\", "/");
		String workingDir = System.getProperty("user.dir");

		String strOSName = System.getProperty("os.name").toLowerCase();

		if (fl.exists()) {
			filePathFound = true;
			toReturn = fl.getAbsolutePath();

		} else {

			while (strOSName.contains("win") && tempFilePath.startsWith("/")) {
				tempFilePath = tempFilePath.replaceFirst("/", "");
			}
			while (tempFilePath.endsWith("/")) {
				tempFilePath = tempFilePath.substring(0, tempFilePath.length() - 1);
			}
			try {

				URI resURI = ConfigFile.class.getClassLoader().getResource(tempFilePath).toURI();
				if (resURI.toString().startsWith("jar:")) {
					if (copyJarResFileToTempLoc) {
						InputStream stream = null;
						FileOutputStream resStreamOut = null;
						try {
							stream = ConfigFile.class.getClassLoader().getResourceAsStream(tempFilePath);

							byte[] buffer = new byte[4096];

							String tempFileLoc = System.getProperty("java.io.tmpdir") + "/"
									+ (new File(tempFilePath)).getName();

							resStreamOut = new FileOutputStream(tempFileLoc);
							int readBytes;
							while ((readBytes = stream.read(buffer)) > 0) {
								resStreamOut.write(buffer, 0, readBytes);
							}
							toReturn = tempFileLoc;
						} catch (Exception ex) {
							throw ex;
						} finally {
							stream.close();
							resStreamOut.close();
						}
					}
				} else {
					toReturn = resURI.getPath();
				}
				if (toReturn != null) {
					filePathFound = true;
				}
			} catch (Exception exception) {
			}
		}

		if (strOSName.contains("win") && filePathFound && toReturn.startsWith("/")) {
			toReturn = toReturn.replaceFirst("/", "");
		}
		return toReturn.replaceAll("\\\\", "/");
	}

}
