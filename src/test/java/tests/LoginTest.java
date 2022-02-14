package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LoginPage.LOGINPAGE_BUTTON;
import static pages.MenuPage.TOPMENU_ITEM_USERNAME;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void logInValidUsernameAndPassword() {
        loginPage
                .openPage()
                .login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText("Dima Hilko")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"
    }

    @Test
    public void logOut() {
        loginPage
                .openPage()
                .login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws")
                .selectMenuItemLogout();
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }





}