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
//Работа с Section (CRUD)
public class SectionCRUDTest extends BaseTest {

    public String PROJECT_NAME1 = "Проект 2 (UI)";
    public String PROJECT_ANNOUNCEMENT_TEXT1 = "Ссылка на базу знаний...";
    public int PROJECT_SUITE_MODE_NUMBER1 = 0; //Use a single repository for all cases (recommended)
    public String PROJECT_SUCCESS_DELETION_MESSAGE1 = "Successfully deleted the project.";
    public String SECTION_NAME1 = "Модуль 1";
    public String SECTION_DESCRIPTION1 = "Описание модуля...";

    @Description("Произвести добавление нового раздела с валидными значениями полей для добавления в него тест-кейсов. При успешном создании проекта произойдет переход на страницу с заголовком 'Add Project'") //описание теста
    @Test(priority = 6, description = "Произвести добавление нового раздела (валидные значения)")//название теста
    public void addSectionValidName(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME1 + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME1, PROJECT_ANNOUNCEMENT_TEXT1, PROJECT_SUITE_MODE_NUMBER1);
        log.debug("Тест " + context.getAttribute("testName") + ": открыть проект '" + PROJECT_NAME1 + "'");
        headerPage
                .selectMenuItemDashboard()
                .openProject(PROJECT_NAME1);
        log.debug("Тест " + context.getAttribute("testName") + ": создать  в проекте новый раздел '" + SECTION_NAME1 + "'");
        testCaseAddSectionPage
                .createNewSection(SECTION_NAME1, SECTION_DESCRIPTION1) //создать новый раздел
                .sectionNameInSectionTree().shouldHave(exactText(SECTION_NAME1)); // проверяем, отображается ли в дереве разделов созданный раздел
    }

    @Description("Произвести удаление раздела") //описание теста
    @Test(priority = 7, description = "Удалить раздел")//название теста
    public void deleteSection(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": открыть проект '" + PROJECT_NAME1 + "'");
        dashbordPage
                .openProject(PROJECT_NAME1); // открываем проект
        log.debug("Тест " + context.getAttribute("testName") + ": удалить раздел '" + SECTION_NAME1 + "' и проверить успешность его удаления - он не должен быть видимым");
        testCasePage
                .deleteSection(SECTION_NAME1) //удаляем раздел
                .sectionNameInPage(SECTION_NAME1).shouldNot(visible);//проверяем видимость удаленного раздела - не должен быть виден
    }

    @Description("Произвести удаление проекта. При успешном удалении проекта будет выведено сообщение 'Successfully deleted the project.'")
    //описание теста
    @Test(priority = 8, description = "Удалить проект")//название теста
    public void deleteProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL) //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
                .deleteProject(PROJECT_NAME1) //удаляем проект
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE1)); //проверяем - при спешном удалении выводится эта надпись
    }
}