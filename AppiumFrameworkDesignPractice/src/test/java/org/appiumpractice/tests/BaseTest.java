package org.appiumpractice.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.appiumpractice.pageobjects.FormPage;
import org.appiumpractice.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formpage;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws IOException {
		//Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream(
			//	System.getProperty("user.dir") + "\\src\\test\\java\\org\\appiumpractice\\testData\\data.properties");
		//String propertiesFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\org\\appiumpractice\\testData\\data.properties";
		//System.out.println("Properties file path: " + propertiesFilePath);
		//prop.load(fis);

//		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
//				: prop.getProperty("ipAddress");
//		System.out.println(ipAddress);
//		// String ipAddress = prop.getProperty("ipAddress");
//		String port = prop.getProperty("port");
//		System.out.println(port);

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Kapil Prakash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("0.0.0.0").usingPort(4723).withTimeout(Duration.ofSeconds(100)).build();
		service.start();
		//service = startAppiumServer();
		UiAutomator2Options options = new UiAutomator2Options();
		//options.setDeviceName(prop.getProperty("deviceName"));
		//System.out.println(deviceName);
		options.setDeviceName("HariEmulator");


		options.setChromedriverExecutable("C:\\Users\\Kapil Prakash\\Downloads\\chromedriver_win32\\chromedriver.exe");
		options.setApp(
				"D:\\AppiumPractice\\AppiumJavaPractice\\AppiumFrameworkDesignPractice\\src\\test\\java\\resources\\General-Store.apk");

		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		//driver = new AndroidDriver(service.getUrl(), options);
		formpage = new FormPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
