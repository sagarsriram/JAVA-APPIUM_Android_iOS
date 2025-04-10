package org.openlane.autohawk.testutils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openlane.autohawk.utils.AppiumGeneric;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumGeneric {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureApppium() throws IOException {
        /*
        To start APPIUM server
         */
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/openlane/autohawk/resources/data.properties");
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        prop.load(file);
        String port = prop.getProperty("port");

        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        //APPIUM Android - capabilites to launch the app on the running physical/virtual device
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp(System.getProperty("user.dir") + "/src/test/java/org/openlane/autohawk/resources/app-uat-intune2.apk");
        options.autoGrantPermissions();

        //To initiate driver and set Implicit wait for all elements
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        /*
         Adding Device details to Extent Report's system info
        This must be done *after* driver is initialized to avoid NullPointerException.
        It uses the driver capabilities to fetch platformName (e.g., Android/iOS) dynamically.
        The ExtentManager holds the shared ExtentReports instance created during test start.
         */
        ExtentReporterNG.getExtent().setSystemInfo("Platform Name", driver.getCapabilities().getCapability("platformName").toString());
        ExtentReporterNG.getExtent().setSystemInfo("Platform Version", driver.getCapabilities().getCapability("platformVersion").toString());
        ExtentReporterNG.getExtent().setSystemInfo("Automation Name", driver.getCapabilities().getCapability("automationName").toString());
        ExtentReporterNG.getExtent().setSystemInfo("Device Name", driver.getCapabilities().getCapability("deviceName").toString());
        ExtentReporterNG.getExtent().setSystemInfo("App Package", driver.getCapabilities().getCapability("appPackage").toString());
    }

    //To exit driver and stop APPIUM server
    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}