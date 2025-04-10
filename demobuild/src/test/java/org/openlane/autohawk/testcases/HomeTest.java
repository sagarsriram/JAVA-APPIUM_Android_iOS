package org.openlane.autohawk.testcases;

import org.openlane.autohawk.pageobject.HomePage;
import org.openlane.autohawk.pageobject.LoginPage;
import org.openlane.autohawk.testutils.AndroidBaseTest;
import org.openlane.autohawk.utils.AppiumGeneric;
import org.testng.annotations.Test;

public class HomeTest extends AndroidBaseTest {

    @Test(priority = 1)
    public void login() {
        LoginPage Login = new LoginPage(driver);
        Login.verifyLoginScreenLoadedAfterLoginButton();
        Login.enterCredentials(AppiumGeneric.getProperty("username"), AppiumGeneric.getProperty("password"));
        Login.clickSignIn();
    }

    @Test(priority = 2)
    public void verifyHomePageForOffsiteUser() {
        HomePage home = new HomePage(driver);
        home.verifyHomePageElementsDisplayedForOffSiteUser();
        home.verifyOnsiteTabNotPresent();
    }

    @Test(priority = 3)
    public void verifyHomePageForOnsiteUser() {
        HomePage home = new HomePage(driver);
        home.verifyHomePageElementsDisplayedForOnSiteUser();
    }

}
