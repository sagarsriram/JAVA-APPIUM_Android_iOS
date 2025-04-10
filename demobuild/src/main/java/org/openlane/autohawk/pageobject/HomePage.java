package org.openlane.autohawk.pageobject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

@HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
public class HomePage {
    AndroidDriver driver;

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /*
    Defining locators
     */
    @AndroidFindBy(id = "com.kar.avx.uat.intune:id/tv_subtitle_flipped_margin")
    @iOSXCUITFindBy(id = "")
    private WebElement homeScreenTitle;

    @AndroidFindBy(id = "com.kar.avx.uat.intune:id/search_button")
    @iOSXCUITFindBy(id = "")
    private WebElement searchIconButton;

    @AndroidFindBy(id = "com.kar.avx.uat.intune:id/overflow")
    @iOSXCUITFindBy(id = "")
    private WebElement overFlowMenu;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Assigned\")")
    @iOSXCUITFindBy(id = "")
    private WebElement assignedTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"My Inspections\")")
    @iOSXCUITFindBy(id = "")
    private WebElement myInspectionsTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Site\")")
    @iOSXCUITFindBy(id = "")
    private WebElement siteTab;

    @AndroidFindBy(id = "com.kar.avx.uat.intune:id/filter_button")
    @iOSXCUITFindBy(id = "")
    private WebElement filterButton;

    @AndroidFindBy(id = "com.kar.avx.uat.intune:id/floating")
    @iOSXCUITFindBy(id = "")
    private WebElement floatingBarcodeScannerButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Upload Logs\")")
    @iOSXCUITFindBy(id = "")
    private WebElement uploadLogs;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Get Help / Give Feedback\")")
    @iOSXCUITFindBy(id = "")
    private WebElement getHelp;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"View Intercom Messages\")")
    @iOSXCUITFindBy(id = "")
    private WebElement viewIntercomMessages;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Site Settings\")")
    @iOSXCUITFindBy(id = "")
    private WebElement siteSettings;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Logout\")")
    @iOSXCUITFindBy(id = "")
    private WebElement logout;

    /*
    Defining actions
     */

    public void verifyHomePageLoaded() {
        Assert.assertTrue(homeScreenTitle.isDisplayed(), "Homescreen title not matched");
        log.info(homeScreenTitle.getText());
    }

    public void verifyHomePageElementsDisplayedForOffSiteUser() {
        Assert.assertTrue(searchIconButton.isDisplayed(), "Search Icon button not displayed");
        Assert.assertTrue(overFlowMenu.isDisplayed(), "Over Flow Menu not displayed");
        Assert.assertTrue(assignedTab.isDisplayed(), " Assigned tab not displayed");
        Assert.assertTrue(myInspectionsTab.isDisplayed(), "My Inspection tab not displayed");
        Assert.assertTrue(floatingBarcodeScannerButton.isDisplayed(), " Barcode Scanner floater not displayed");
    }

    public void verifyOnsiteTabNotPresent() {
        boolean isSiteTabPresent = driver.findElements(By.id("Site_tab")).size() > 0;
        Assert.assertFalse(isSiteTabPresent, "Site Tab should not be visible for Offsite users");
    }

    public void verifyHomePageElementsDisplayedForOnSiteUser() {
        overFlowMenu.click();
        clickOverflowOption("site settings");
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"On Site\"]")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"OPENLANE Calgary\")")).click();
        verifyHomePageElementsDisplayedForOffSiteUser();
    }

    public void tapSearchIconButton() {
        searchIconButton.click();
    }

    public void tapOverFlowMenu() {
        overFlowMenu.click();
    }

    public void tapAssignedTab() {
        assignedTab.click();
    }

    public void tapMyInspectionsTab() {
        myInspectionsTab.click();
    }

    public void tapFloatingBarcodeScannerButton() {
        floatingBarcodeScannerButton.click();
    }

    public void tapSiteTab() {
        siteTab.click();
    }

    public void clickOverflowOption(String optionText) {
        By dynamicLocator = By.xpath("//android.widget.TextView[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') , '" + optionText + "')]");
        driver.findElement(dynamicLocator).click();
    }

    public void verifyOverflowOption(String optionText) {
        By dynamicLocator = By.xpath("//android.widget.TextView[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') , '" + optionText + "')]");
        Assert.assertTrue(driver.findElement(dynamicLocator).isDisplayed(), optionText + " is not visible");
    }


}


