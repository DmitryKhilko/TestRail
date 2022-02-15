package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.BasePage.*;

public class TestCaseTest extends BaseTest {

    @Test (description = "Создать тест-кейс", groups = {"smoke"})
    public void createTestCase() {
        //ScreenShooter.captureSuccessfulTests = true; //команда, разрешающая делать скриншоты для зеленых тестов (только скрины с проверок shouldHave и shouldBe
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeInput("Email","hdn_tms@mail.ru")
                .writeInput("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckboks("Keep me logged in")
                .clickButton("Log In");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText("Dima Hilko")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"
    }

    @Test (description = "Удалить тест-кейс")
    public void deleteTestCase() {
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeInput("Email","hdn_tms@mail.ru")
                .writeInput("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckboks("Keep me logged in")
                .clickButton("Log In")
                .selectMenuItem("Dima Hilko")
                .selectMenuItem("Logout");
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }
}