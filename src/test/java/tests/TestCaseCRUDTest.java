package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Section;
import models.SectionFactory;
import models.TestCase;
import models.TestCaseFactory;
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
public class TestCaseCRUDTest extends BaseTest {

    @BeforeMethod(description = "Подготавливаем тестовые данные для проверки работоспособности функционала, связанного с тест-ранами: входим в приложение, создаем проект, разделы")
    public void precondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties

        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME3 + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME3, PROJECT_ANNOUNCEMENT_TEXT, PROJECT_SUITE_MODE_NUMBER);

        log.debug("Тест " + context.getAttribute("testName") + ": открыть проект '" + PROJECT_NAME3 + "'");
        headerPage
                .selectMenuItemDashboard()
                .openProject(PROJECT_NAME3);

        log.debug("Тест " + context.getAttribute("testName") + ": перейти к созданию нового раздела");
        testCasePage
                .addNewSection();

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса конструктора");
        Section section = SectionFactory.get();
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый раздел '" + SECTION_NAME + "'");
        testCaseAddSectionPage
                .createNewSection(section);
    }

    @AfterMethod(description = "После проверки работоспособности функционала, связанного с тест-ранами, удаляем проект")
    public void postcondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть сразу нужную страницу, чтобы избежать нажатий на кнопки (нажатия проверялись раньше)");
        log.debug("Тест " + context.getAttribute("testName") + ": удалить проект '" + PROJECT_NAME3 + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность удаления проекта '" + PROJECT_NAME3 + "' - на странице будет выведена надпись '" + PROJECT_SUCCESS_DELETION_MESSAGE + "'");
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL)
                .deleteProject(PROJECT_NAME3)
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE));
    }

    @Description("Произвести добавление нового тест кейса с валидными значениями полей")
    @Test(priority = 9, description = "Добавление тест кейса (валидные значения)")
    public void addTestCaseValidName(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти к созданию нового тест-кейса");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность открытия страницы создания тест-кейса - заголовок на откывшейся странице '" + TESTCASE_PAGETITLE + "'");
        testCasePage
                .addNewTestCase()
                .pageTitle().shouldHave(exactText(TESTCASE_PAGETITLE));

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса конструктора");
        TestCase testCase = TestCaseFactory.get();

        log.debug("Тест " + context.getAttribute("testName") + ": создать новый тест-кейс '" + TESTCASE_TITLE + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность создания тест-кейса '" + TESTCASE_TITLE + "' - на странице, на которую происходит переход после создания тест-кейса, выводится сообщение '" + TESTCASE_SUCCESS_ADDED_MESSAGE + "'");
        testCaseAddCasePage
                .createNewTestCase(testCase)
                .projectActionResultMessage().shouldHave(exactText(TESTCASE_SUCCESS_ADDED_MESSAGE));
    }
}