package org.appiumpractice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public AppiumDriverLocalService service;

	By pageTitle = By.id("com.androidsample.generalstore:id/toolbar_title");

	public void waitUntilPresenceOfElement(AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(pageTitle));
	}

	public void waitUntilattributeContainsValue(AppiumDriver driver, String textValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(pageTitle, "text", "" + textValue + ""));
	}

	public void waitUntilElementIsVisible(AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
	}

	public AppiumDriverLocalService startAppiumServer() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Kapil Prakash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("0.0.0.0").usingPort(4723).withTimeout(Duration.ofSeconds(10)).build();
		service.start();
		return service;
	}

//	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
//		System.out.println(ipAddress + "," + port);
//
//		try {
//			service = new AppiumServiceBuilder().withAppiumJS(new File(
//					"C:\\Users\\Kapil Prakash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//					.withIPAddress(ipAddress).usingPort(port).withTimeout(Duration.ofSeconds(10)).build();
//
//			System.out.println("build started with" + ipAddress + port);
//
//			service.start();
//			System.out.println("Server started with" + ipAddress + port);
//			return service;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return service;
//		}
//		System.out.println(ipAddress+","+port);
//
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(new File(
//						"C:\\Users\\Kapil Prakash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress(ipAddress).usingPort(port).withTimeout(Duration.ofSeconds(10)).build();
//		System.out.println("build started with"+ipAddress+port);
//
//		service.start();
//		System.out.println("Server started with"+ipAddress+port);
//		return service;
	// }

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		try {

			// System.out.println("Entered");

			File source = driver.getScreenshotAs(OutputType.FILE);
			System.out.println("captured");

			String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			FileUtils.copyFile(source, new File(destinationFile));
			System.out.println("Screenshot captured and saved to: " + destinationFile);
			return destinationFile;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
			return null;
		}
//		File source = driver.getScreenshotAs(OutputType.FILE);
//		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
//		FileUtils.copyFile(source, new File(destinationFile));
//		return destinationFile;
	}

}
