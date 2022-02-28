package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static pages.AdminProjectsPage.ADMIN_PROJECT_PAGE_URL;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Работа с Projects (CRUD)
public class ProjectCRUDTest extends BaseTest {

    public String PROJECT_NAME = "Проект 1 (UI)";
    public String PROJECT_NAME_UPDATE = "Проект 1 (UI)_обновлено";
    public String PROJECT_ANNOUNCEMENT_TEXT = "Ссылка на базу знаний...";
    public String PROJECT_ANNOUNCEMENT_TEXT_UPDATE = "Ссылка на базу знаний_обновлено...";
    public int PROJECT_SUITE_MODE_NUMBER = 0; //Use a single repository for all cases (recommended)
    public int PROJECT_SUITE_MODE_NUMBER_UPDATE = 1; //Use a single repository with baseline support
    public String PROJECT_SUCCESS_UPDATION_MESSAGE = "Successfully updated the project.";
    public String PROJECT_SUCCESS_DELETION_MESSAGE = "Successfully deleted the project.";

    @Description("Произвести добавления проекта с валидными значениями полей. При успешном создании проекта произойдет переход на страницу с заголовком 'Projects'") //описание теста
    @Test(priority = 2, description = "Создать проект (валидные значения)")//название теста
    public void addProjectValidValue(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME, PROJECT_ANNOUNCEMENT_TEXT, PROJECT_SUITE_MODE_NUMBER);
        log.debug("Тест " + context.getAttribute("testName") + ": убедится, что проект '" + PROJECT_NAME + "' создан");
        adminProjectsPage
                .projectName(PROJECT_NAME).shouldBe(visible);
    }

    @Description("Произвести считывание добавленного проекта. При успешном создании проект будет отображаться на странице 'Dashbord'")
    //описание теста
    @Test(priority = 3, description = "Произвести считывание добавленного проекта")//название теста
    public void readProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        dashbordPage
                .nameProjects().shouldHave(exactText(PROJECT_NAME)); // проверяем, отображается ли на странице Dashboard созданный проект
    }

    @Description("Произвести изменение добавленного проекта. При успешном создании проект будет отображаться на странице 'Dashbord'")
    //описание теста
    @Test(priority = 4, description = "Произвести изменение добавленного проекта")//название теста
    public void updateProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL); //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
        projectEditPage
                .updateProject(PROJECT_NAME, PROJECT_NAME_UPDATE, PROJECT_ANNOUNCEMENT_TEXT_UPDATE, PROJECT_SUITE_MODE_NUMBER_UPDATE)
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_UPDATION_MESSAGE)); //проверяем - при спешном резактировании выводится эта надпись
    }

    @Description("Произвести удаление проекта. При успешном удалении проекта будет выведено сообщение 'Successfully deleted the project.'")
    //описание теста
    @Test(priority = 5, description = "Удалить проект")//название теста
    public void deleteProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL) //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
                .deleteProject(PROJECT_NAME_UPDATE) //удаляем проект
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE)); //проверяем - при спешном удалении выводится эта надпись
    }
}