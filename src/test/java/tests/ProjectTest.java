package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LoginPage.LOGINPAGE_BUTTON;
import static pages.MenuPage.TOPMENU_ITEM_USERNAME;

public class ProjectTest extends BaseTest {

    @Test (description = "Создать проект", groups = {"smoke"})
    public void createProject() {
        //ScreenShooter.captureSuccessfulTests = true; //команда, разрешающая делать скриншоты для зеленых тестов (только скрины с проверок shouldHave и shouldBe
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeLogin("Email","hdn_tms@mail.ru")
                .writePassword("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckark("Keep me logged in")
                .clickButton("Log In");
        $(TOPMENU_ITEM_USERNAME).shouldHave(exactText("Dima Hilko")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko"

    }

    @Test (description = "Удалить тест-кейс")
    public void deleteProject() {
        loginPage
                .openPage("/index.php?/auth/login/")
                .writeLogin("Email","hdn_tms@mail.ru")
                .writePassword("Password","pVui0CaU1AsUDIXrPMws")
                .clickCheckark("Keep me logged in")
                .clickButton("Log In")
                .openUserMenu()
                .selectMenuItem("Logout");
        $(LOGINPAGE_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }

}