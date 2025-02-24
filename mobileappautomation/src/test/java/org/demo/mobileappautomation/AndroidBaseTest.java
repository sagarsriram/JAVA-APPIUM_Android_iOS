package org.demo.mobileappautomation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.demo.pageobjects.android.FormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {

		public AndroidDriver driver;
		public AppiumDriverLocalService service;
		public FormPage formPage;
		
		@BeforeClass
		public void ConfigureAppium() throws MalformedURLException
		{
//			service = AppiumDriverLocalService.buildDefaultService();
//			service.start();
			 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\SagarSriram\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
				
									
				UiAutomator2Options options = new UiAutomator2Options();
				//options.setDeviceName("RahulPhone"); //emulator
				options.setDeviceName("RZCX11J3XDM");// real device
				
				
				
			//	options.setChromedriverExecutable("//Users//rahulshetty//documents//chromedriver 11");
				
			//	options.setApp("//Users//rahulshetty//workingcode//Appium//src//test//java//resources//ApiDemos-debug.apk");	
				options.setApp("D://resources//resources//General-Store.apk");
				
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 formPage = new FormPage(driver);
		}
		
	
		@AfterClass
		public void tearDown()
		{
			driver.quit();
	        service.stop();
			}
		
	}
