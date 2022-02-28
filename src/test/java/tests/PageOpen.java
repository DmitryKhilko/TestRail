package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Проверка работоспособности пунктов меню, вкладок (используя локаторы)
public class PageOpen extends BaseTest {

    @Description("Перейти на страницу 'My Settings'. Проверить после входа на страницу наличие заголовка страницы - 'My Settings'") //описание теста
    @Test(priority = 6, description = "Перейти на страницу 'My Settings'")//название теста, название группы
    public void openMySettingsPage(ITestContext context) {
         loginPage
                .openPage(LOGIN_PAGE_URL) //открываем приложение
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemMySetting() //выбираем пункт меню My Settings
                .pageTitle().shouldHave(exactText("My Settings")); //проверить, открылась ли страница 'My Settings'
    }

    @Description("Перейти на страницу 'Dashboard'. Проверить после входа на страницу наличие заголовка страницы - 'All Projects'") //описание теста
    @Test(priority = 7, description = "Перейти на страницу 'Dashboard'")//название теста, название группы
    public void openDashboardPage(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем приложение
                .login(email, password) //email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemDashboard() //выбираем пункт меню Dashboard
                .pageTitle().shouldHave(exactText("All Projects")); //проверить, открылась ли страница 'All Projects'
    }

    @Description("Перейти на страницу 'Overview'. Проверить после входа на страницу наличие заголовка страницы - 'Overview'") //описание теста
    @Test(priority = 8, description = "Перейти на страницу 'Overview'")//название теста, название группы
    public void openAdministrationPage(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем приложение
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemAdministration() //выбираем пункт меню Administration
                .pageTitle().shouldHave(exactText("Overview")); //проверить, открылась ли страница 'Overview'
    }

    @Description("Перейти на страницу 'Projects'. Проверить после входа на страницу наличие заголовка страницы - 'Projects'") //описание теста
    @Test(priority = 9, description = "Перейти на страницу 'Projects'")//название теста, название группы
    public void openProjectsPage(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем приложение
                .login(email, password)//email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemAdministration(); //выбираем пункт меню Administration
        adminProjectsPage
                .selectMenuItemProjects() //выбираем пункт меню Projects
                .pageTitle().shouldHave(exactText("Projects")); //проверить, открылась ли страница 'Projects'
    }

    @Description("Произвести выход из приложения. Проверить после выхода наличие на странице логина кнопки 'Log in'")
    @Test (priority = 10, description = "Выйти из приложения")
    public void logout(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем приложение
                .login(email, password) //email и password - переменные, берущие значения из файла config.properties
                .selectMenuItemLogout() //выбираем пунет меню Logout
                .loginButton().shouldBe(visible); //проверить, вышли ли из приложения - на странице должна отображаться кнопка 'Log in'
    }
}
