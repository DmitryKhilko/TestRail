package tests;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LoginPage.LOGINPAGE_BUTTON;
import static pages.MenuPage.TOPMENU_ITEM_USERNAME;


public class LoginTest extends BaseTest {
    @TmsLink("8537") //ссылка на тест-кейс в TMS
    @Issue("1988") //ссылка на баг-репорт
    @Description("Проверить после входа в приложение наличие пункта меню с именем текущего пользователя") //описание теста
    @Test(description = "Войти в приложение с корректными значениями логина и пароля", groups = {"smoke"})//название теста, название группы
    public void logInValidUsernameAndPassword() {
        ScreenShooter.captureSuccessfulTests = true;
        loginPage
                .openPage()
                .login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText("Dima Hilko")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"
    }

    @Description("Проверить после выхода из приложение на странице логина наличие кнопки 'Log in'")
    @Test (description = "Выйти из приложения")
    public void logOut() {
        loginPage
                .openPage()
                .login("hdn_tms@mail.ru", "pVui0CaU1AsUDIXrPMws")
                .selectMenuItemLogout();
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }





}