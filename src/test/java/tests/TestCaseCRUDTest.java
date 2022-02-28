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
//Работа с Section (CRUD)
public class TestCaseCRUDTest extends BaseTest {

    public String PROJECT_NAME2 = "Проект 3 (UI)";
    public String PROJECT_ANNOUNCEMENT_TEXT2 = "Ссылка на базу знаний...";
    public int PROJECT_SUITE_MODE_NUMBER2 = 0; //Use a single repository for all cases (recommended)
    public String PROJECT_SUCCESS_DELETION_MESSAGE2 = "Successfully deleted the project.";
    public String SECTION_NAME2 = "Модуль 3";
    public String SECTION_DESCRIPTION2 = "Описание модуля...";
    public String TESTCASE_TITLE2 = "Тест-кейс 1";
    public String TESTCASE_TYPE2 = "Functional";
    public String TESTCASE_PRECONDITION2 = "Предусловие...";
    public String TESTCASE_STEPS2 = "Steps of test cace...";
    public String TESTCASE_EXPECTEDRESULT2 = "Ожидаемый результат тест-кейса...";
    public String TESTCASE_SUCCESS_ADDED_MESSAGE2 = "Successfully added the new test case. Add another";

    @Description("Произвести добавление нового тест кейса с валидными значениями полей") //описание теста
    @Test(priority = 9, description = "Добавление тест кейса (валидные значения)")//название теста
    public void addTestCaseValidName(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME2 + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME2, PROJECT_ANNOUNCEMENT_TEXT2, PROJECT_SUITE_MODE_NUMBER2);
        log.debug("Тест " + context.getAttribute("testName") + ": открыть проект '" + PROJECT_NAME2 + "'");
        headerPage
                .selectMenuItemDashboard()
                .openProject(PROJECT_NAME2);
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый раздел '" + SECTION_NAME2 + "'");
        testCaseAddSectionPage
                .createNewSection(SECTION_NAME2, SECTION_DESCRIPTION2); //создать новый раздел
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый тест-кейс '" + TESTCASE_TITLE2 + "'");
        testCaseAddCasePage
                .createNewTestCase(TESTCASE_TITLE2, SECTION_NAME2, TESTCASE_TYPE2, TESTCASE_PRECONDITION2, TESTCASE_STEPS2, TESTCASE_EXPECTEDRESULT2)
                .projectActionResultMessage().shouldHave(exactText(TESTCASE_SUCCESS_ADDED_MESSAGE2)); //проверяем - при спешном создании тест-кейса выводится эта надпись
    }

    @Description("Произвести удаление проекта. При успешном удалении проекта будет выведено сообщение 'Successfully deleted the project.'")
    //описание теста
    @Test(priority = 10, description = "Удалить проект")//название теста
    public void deleteProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL) //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
                .deleteProject(PROJECT_NAME2) //удаляем проект
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE2)); //проверяем - при спешном удалении выводится эта надпись
    }
}