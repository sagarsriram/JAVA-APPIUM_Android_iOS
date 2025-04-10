package org.openlane.autohawk.testcases;

import org.openlane.autohawk.pageobject.HomePage;
import org.openlane.autohawk.pageobject.LoginPage;
import org.openlane.autohawk.testutils.AndroidBaseTest;
import org.openlane.autohawk.utils.AppiumGeneric;
import org.testng.annotations.Test;

public class LoginTest extends AndroidBaseTest {
    public void performLoginAndVerify(String username, String password, String expectedErrorType) {
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        login.verifyLoginScreenLoadedAfterLoginButton();
        login.enterCredentials(username, password);
        login.clickRememberMe();
        login.clickSignIn();
        switch (expectedErrorType) {
            case "blank":
                login.verifyBlankFieldError(); // handles error for blank username/password
                driver.navigate().back();
                break;
            case "invalid":
                login.verifyInvalidCredentialError(); // handles error for wrong username/password
                driver.navigate().back();
                break;
            case "none":
                home.verifyHomePageLoaded(); // success path
                break;
        }
    }

    @Test(priority = 1)
    public void blankUserNameAndPassword() {
        performLoginAndVerify("", "", "blank");
    }

    @Test(priority = 2)
    public void invalidUserNameAndPassword() {
        performLoginAndVerify("random", "random123", "invalid");
    }

    @Test(priority = 3)
    public void validUserNameInvalidPassword() {
        performLoginAndVerify("sagar@openlane.com", "random123", "invalid");
    }

    @Test(priority = 4)
    public void invalidUserNameValidPassword() {
        performLoginAndVerify("random", "Jalal@9715", "invalid");
    }

    @Test(priority = 5)
    public void loginWithValidCredentials() {
        String name = AppiumGeneric.getProperty("username");
        String password = AppiumGeneric.getProperty("password");
        performLoginAndVerify(name, password, "none");
    }

}
