package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

@Log4j2
//Проверка работоспособности пунктов меню, вкладок (используя локаторы)
public class PageOpen extends BaseTest {

    @Description("Проверить после входа на страницу наличие заголовка страницы - 'My Settings'") //описание теста
    @Test(priority = 1, description = "Перейти на страницу 'My Settings'")//название теста, название группы
    public void openMySettingsPage(ITestContext context) {
         loginPage
                .openPage("/auth/login/")
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                 .selectMenuItem("My Settings"); //выбираем пункт меню My Settings
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, открылась ли страница 'My Settings'");
        //menuPage.menuItemUserName().shouldHave(exactText(userName)); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko" (переменная берущая значение из файла config.properties)
        mySettingsPage.pageTitle().shouldHave(exactText("My Settings"));
    }




    @Description("Проверить после выхода из приложение на странице логина наличие кнопки 'Log in'")
    @Test (priority = 11, description = "Выйти из приложения")
    public void logout(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, password) //email и password - переменные, берущие значения из файла config.properties
                .selectMenuItem("Logout");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, вышли ли из приложения - на странице должна отображаться кнопка 'Log in'");
        loginPage.loginButton().shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
    }
}
