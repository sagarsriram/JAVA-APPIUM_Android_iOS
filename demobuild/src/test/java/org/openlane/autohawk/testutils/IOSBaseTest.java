package org.openlane.autohawk.testutils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openlane.autohawk.utils.AppiumGeneric;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest extends AppiumGeneric {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void test() throws IOException {

        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/openlane/autohawk/resources/data.properties");

        prop.load(file);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        System.out.println("lets start");
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 16 Pro");
        options.setApp("/Users/sagars/Documents/UIKitCatalog.app");
        options.setPlatformVersion("18.3");
        options.setBundleId("com.kar.avx.uat.intune");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}