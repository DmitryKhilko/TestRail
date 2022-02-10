package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LoginPage.LOGIN_BUTTON;
import static pages.MenuPage.MENU_USERNAME;

public class LoginPageTest extends BaseTest {

    @Test
    public void logInlogOutCorrectUsernameCorrectPassword() {
        loginPage.openPage();
        $(LOGIN_BUTTON).shouldBe(visible); // Проверка успешности входа на страницу логина: проверяем наличие кнопки "Log in" на открывшейся странице
        loginPage.login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws");
        //$(MENU_USERNAME).shouldBe(visible, Duration.ofSeconds(20)); // Duration.ofSeconds(20) - установка ожидания появления элемента, отличного от установки в BaseTest (там сейчас 5 секунд). На случай, если какие-то части приложения работают медленно
        $(MENU_USERNAME).shouldHave(exactText("Dima Hilko")); // Проверка успешности входа в веб-приложение: проверяем наличие пункта меню "Dima Hilko" на открывшейся странице
    }
}
