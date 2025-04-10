package org.openlane.autohawk.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppiumGeneric {
    public AppiumDriverLocalService service;

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public String getScreenShot(String testCaseName, AppiumDriver driver) throws IOException {
        File Source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileHandler.copy(Source, new File(destinationFile));
        return destinationFile;
    }

    public void takeScreenshot(String screenshotName, AppiumDriver driver) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/reports/" + screenshotName + ".png");
            FileUtils.copyFile(screenshot, destination);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    private static Properties props;

    static {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/openlane/autohawk/resources/data.properties");
            props = new Properties();
            props.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

}
