package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static pages.AdminProjectsPage.ADMIN_PROJECT_PAGE_URL;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Работа с Section (CRUD)
public class TestRunCRUDTest extends BaseTest {

    @BeforeMethod(description = "Подготавливаем тестовые данные для проверки работоспособности функционала, связанного с тест-ранами: входим в приложение, создаем проект, разделы, тест-кейсы")
    public void precondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина '" + LOGIN_PAGE_URL + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "' (значения переменных берутся из файла config.properties)");
        loginPage
                .openPage(LOGIN_PAGE_URL)
                .login(email, password);

        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME4 + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME4, PROJECT_ANNOUNCEMENT_TEXT, PROJECT_SUITE_MODE_NUMBER);

        log.debug("Тест " + context.getAttribute("testName") + ": перейти на вкладку 'Dashboard'");
        log.debug("Тест " + context.getAttribute("testName") + ": на вкладке 'Dashboard' открыть проект '" + PROJECT_NAME4 + "'");
        headerPage
                .selectMenuItemDashboard()
                .openProject(PROJECT_NAME4);

        log.debug("Тест " + context.getAttribute("testName") + ": открыть диалог создания нового раздела");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность открытия диалога создания раздела - заголовок на открывшемся диалоге '" + SECTION_DIALOGTITLE + "'");
        testCasePage
                .addNewSection()
                .dialogTitle().shouldHave(exactText(SECTION_DIALOGTITLE));

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса - объект с тестовыми данными для заполнения диалога '" + SECTION_DIALOGTITLE + "'");
        Section section = SectionFactory.get();
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый раздел '" + SECTION_NAME + "'");
        testCaseAddSectionPage
                .createNewSection(section);

        log.debug("Тест " + context.getAttribute("testName") + ": перейти к созданию нового тест-кейса внутри созданного раздела '" + SECTION_NAME + "'");
        testCasePage
                .addNewTestCase();

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса - объект с тестовыми данными для заполнения диалогового окна создания тест-кейса");
        TestCase testCase = TestCaseFactory.get();
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый тест-кейс '" + TESTCASE_TITLE + "'");
        testCaseAddCasePage
                .createNewTestCase(testCase);
    }

    @AfterMethod(description = "После проверки работоспособности функционала, связанного с тест-ранами, удаляем проект")
    public void postcondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть сразу нужную страницу, чтобы избежать нажатий на кнопки (нажатия проверялись раньше)");
        log.debug("Тест " + context.getAttribute("testName") + ": удалить проект '" + PROJECT_NAME4 + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность удаления проекта '" + PROJECT_NAME4 + "' - на странице будет выведена надпись '" + PROJECT_SUCCESS_DELETION_MESSAGE + "'");
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL)
                .deleteProject(PROJECT_NAME4)
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE));
    }

    @Description("Произвести добавление нового тест рана с валидными значениями полей")
    @Test(priority = 9, description = "Создание тест рана (валидные значения)")
    public void addTestRunValidName(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти к созданию нового тест-рана");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность открытия страницы создания тест-рана - заголовок на открывшейся странице '" + TESTRUN_PAGETITLE + "'");
        testRunPage
                .addNewTestRun()
                .pageTitle().shouldHave(exactText(TESTRUN_PAGETITLE));

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса - объект с тестовыми данными для заполнения диалога '" + TESTRUN_PAGETITLE + "'");
        TestRun testRun = TestRunFactory.get();

        log.debug("Тест " + context.getAttribute("testName") + ": создать новый тест-ран '" + TESTRUN_NAME + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность создания тест-рана '" + TESTRUN_NAME + "' - заголовок страницы, на которую происходит переход после создания тест-рана '" + TESTRUN_NAME + "'");
        testRunAddPage
                .createNewTestRun(testRun)
                .pageTitle().shouldHave(exactText(TESTRUN_NAME));
    }
}