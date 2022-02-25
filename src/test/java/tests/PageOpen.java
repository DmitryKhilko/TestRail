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
        mySettingsPage.pageTitle().shouldHave(exactText("My Settings")); //на открывшейся странице ее название должно быть My Settings
    }

    @Description("Проверить после входа на страницу наличие заголовка страницы - 'All Projects'") //описание теста
    @Test(priority = 2, description = "Перейти на страницу 'Dashboard'")//название теста, название группы
    public void openDashboardPage(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemDashboard(); //выбираем пункт меню Dashboard
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, открылась ли страница 'All Projects'");
        dashbordPage.pageTitle().shouldHave(exactText("All Projects"));
    }

    @Description("Проверить после входа на страницу наличие заголовка страницы - 'Overview'") //описание теста
    @Test(priority = 3, description = "Перейти на страницу 'Overview'")//название теста, название группы
    public void openAdministrationPage(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemAdministration(); //выбираем пункт меню Administration
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, открылась ли страница 'Overview'");
        adminOverviewPage.pageTitle().shouldHave(exactText("Overview"));
    }

    @Description("Проверить после входа на страницу наличие заголовка страницы - 'Overview'") //описание теста
    @Test(priority = 4, description = "Перейти на страницу 'Projects'")//название теста, название группы
    public void openSubProjectsPage(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemAdministration(); //выбираем пункт меню Administration
        adminProjectsPage
                .selectMenuItemProjects(); //выбираем пункт меню Projects
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, открылась ли страница 'Projects'");
        adminProjectsPage.pageTitle().shouldHave(exactText("Projects"));
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
