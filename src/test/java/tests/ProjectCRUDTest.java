package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static pages.AdminProjectsPage.ADMIN_PROJECT_PAGE_URL;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Работа с Projects (CRUD)
public class ProjectCRUDTest extends BaseTest {

    String NAME_PROJECT = "Проект 1 (UI)";
    String NAME_PROJECT_UPDATE = "Проект 1 (UI)_обновлено";
    String ANNOUNCEMENT_TEXT = "Ссылка на базу знаний...";
    String ANNOUNCEMENT_TEXT_UPDATE = "Ссылка на базу знаний_обновлено...";
    int SUITE_MODE_NUMBER = 1;
    int SUITE_MODE_NUMBER_UPDATE = 2;
    String PROJECT_SUCCESS_UPDATION_MESSAGE = "Successfully updated the project.";
    String PROJECT_SUCCESS_DELETION_MESSAGE = "Successfully deleted the project.";

    @Description("Произвести добавления проекта с валидными значениями полей. При успешном создании проекта произойдет переход на страницу с заголовком 'Add Project'") //описание теста
    @Test(priority = 1, description = "Создать проект (валидные значения)")//название теста
    public void addProjectValidValue(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        addProjectPage
                .createNewProject(NAME_PROJECT, ANNOUNCEMENT_TEXT, SUITE_MODE_NUMBER);
        projectPage
                .pageTitle().shouldHave(exactText(NAME_PROJECT)); //на открывшейся странице ее название должно быть "Проект 1"
    }

    @Description("Произвести считывание добавленного проекта. При успешном создании проект будет отображаться на странице 'Dashbord'") //описание теста
    @Test(priority = 2, description = "Произвести считывание добавленного проекта")//название теста
    public void readProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        dashbordPage
                .nameProjects().shouldHave(exactText(NAME_PROJECT)); // проверяем, отображается ли на странице Dashboard созданный проект
    }

    @Description("Произвести изменение добавленного проекта. При успешном создании проект будет отображаться на странице 'Dashbord'") //описание теста
    @Test(priority = 3, description = "Произвести изменение добавленного проекта")//название теста
    public void updateProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL); //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
        editProjectPage
                .updateProject(NAME_PROJECT, NAME_PROJECT_UPDATE, ANNOUNCEMENT_TEXT_UPDATE, SUITE_MODE_NUMBER_UPDATE)
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_UPDATION_MESSAGE)); //проверяем - при спешном резактировании выводится эта надпись
    }

    @Description("Произвести удаление проекта. При успешном удалении проекта будет выведено сообщение 'Successfully deleted the project.'") //описание теста
    @Test(priority = 4, description = "Удалить проект")//название теста
    public void deleteProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL) //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
                .deleteProject(NAME_PROJECT_UPDATE) //удаляем проект
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE)); //проверяем - при спешном удалении выводится эта надпись
    }
}