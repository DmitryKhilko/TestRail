package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.BasePage.LOGINPAGE_BUTTON;
import static pages.BasePage.TOPMENU_ITEM_USERNAME;

public class TestCaseTest extends BaseTest {

    @Test (description = "Создать тест-кейс", groups = {"smoke"})
    public void createTestCase() {
        //ScreenShooter.captureSuccessfulTests = true; //команда, разрешающая делать скриншоты для зеленых тестов (только скрины с проверок shouldHave и shouldBe
        loginPage
                .openPage("/auth/login/")
                .writeToInput("Email",email)
                .writeToInput("Password",password)
                .clickCheckboks("Keep me logged in")
                .clickButton("Log In");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText(userName)); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"
    }

    @Test (description = "Удалить тест-кейс")
    public void deleteTestCase() {
        loginPage
                .openPage("/auth/login/")
                .writeToInput("Email",email)
                .writeToInput("Password",password)
                .clickCheckboks("Keep me logged in")
                .clickButton("Log In")
                .selectMenuItem(userName)
                .selectMenuItem("Logout");
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }
}