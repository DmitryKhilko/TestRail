package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LoginPage.LOGINPAGE_BUTTON;
import static pages.MenuPage.TOPMENU_ITEM_USERNAME;

@Log4j2
public class LoginTest extends BaseTest {
    @TmsLink("8537") //ссылка на тест-кейс в TMS
    @Issue("1988") //ссылка на баг-репорт
    @Description("Проверить после входа в приложение наличие пункта меню с именем текущего пользователя") //описание теста
    @Test(description = "Войти в приложение с корректными значениями логина и пароля", groups = {"smoke"})//название теста, название группы
    public void logInValidUsernameAndPassword(ITestContext context) {
        //ScreenShooter.captureSuccessfulTests = true; //команда, разрешающая делать скриншоты для зеленых тестов (только скрины с проверок shouldHave и shouldBe
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeLogin("Email","hdn_tms@mail.ru")
                .writePassword("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckark("Keep me logged in")
                .clickButton("Log In");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, вошли ли в приложение - в меню должен отображаться текущий пользователь");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText("Dima Hilko")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"
    }

    @Description("Проверить после выхода из приложение на странице логина наличие кнопки 'Log in'")
    @Test (description = "Выйти из приложения")
    public void logOut(ITestContext context) {
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeLogin("Email","hdn_tms@mail.ru")
                .writePassword("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckark("Keep me logged in")
                .clickButton("Log In")
                .openUserMenu()
                .selectMenuItem("Logout");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, вышли ли из приложения - на странице должна отображаться кнопка 'Log in'");
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }
}