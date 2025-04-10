package org.openlane.autohawk.pageobject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

@HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
public class LoginPage {
    AndroidDriver driver;
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /*
    Defining locators
     */
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")")
    @iOSXCUITFindBy(id = "")
    private WebElement allowGPSWhileUsingTheAPP;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_one_time_button\")")
    @iOSXCUITFindBy(id = "")
    private WebElement allowGPSOnlyThisTime;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_deny_button\")")
    @iOSXCUITFindBy(id = "")
    private WebElement declineGpsDontAllow;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"Login\")")
    @iOSXCUITFindBy(id = "")
    private WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"existing-id-text\")")
    @iOSXCUITFindBy(id = "")
    private WebElement welcomeMessage;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"We found some errors. Please review the form and make corrections.\")")
    @iOSXCUITFindBy(id = "")
    private WebElement blankError;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Username or password incorrect\")")
    @iOSXCUITFindBy(id = "")
    private WebElement invalidCredError;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement userName;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement userPassword;

    @AndroidFindBy(className = "android.widget.CheckBox")
    @iOSXCUITFindBy(id = "")
    private WebElement rememberMeCheckBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign in\")")
    private WebElement signInButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
    private WebElement userLoginConfirmation;

    /*
    Defining actions
     */
    public void verifyLoginScreenLoadedAfterLoginButton() {
        loginButton.click();
        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message not displayed");
    }

    public void verifyBlankFieldError() {
        Assert.assertTrue(blankError.isDisplayed(), "Error message not displayed for invalid login");
        System.out.println(blankError.getText());
    }

    public void verifyInvalidCredentialError() {
        Assert.assertTrue(invalidCredError.isDisplayed(), "Error message not displayed for invalid login");
        System.out.println(invalidCredError.getText());
    }

    public void enterCredentials(String name, String password) {
        userName.sendKeys(name);
        userPassword.sendKeys(password);
    }

    public void clickRememberMe() {
        rememberMeCheckBox.click();
    }

    public void clickSignIn() {
        signInButton.click();
    }


}


