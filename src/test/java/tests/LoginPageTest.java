package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginPageTest extends BaseTest {

    @Test
    public void logInlogOutCorrectUsernameCorrectPassword() {
        loginPage.openPage();
        loginPage.IsVisibleLoginButton();

        loginPage.login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws");
        menuPage.isLoginSuccessful();

        menuPage.selectMenuItemLogout();
        loginPage.IsVisibleLoginButton();
    }
}
