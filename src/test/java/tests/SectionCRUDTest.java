package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Section;
import models.SectionFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static pages.AdminProjectsPage.ADMIN_PROJECT_PAGE_URL;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Работа с Section (CRUD)
public class SectionCRUDTest extends BaseTest {

    @BeforeMethod(description = "Подготавливаем тестовые данные для проверки работоспособности функционала, связанного с тест-ранами: входим в приложение, создаем проект")
    public void precondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": войти в приложение c логином '" + email + "' и паролем '" + password + "'");
        loginPage
                .openPage(LOGIN_PAGE_URL) //открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый проект '" + PROJECT_NAME2 + "'");
        projectAddPage
                .createNewProject(PROJECT_NAME2, PROJECT_ANNOUNCEMENT_TEXT, PROJECT_SUITE_MODE_NUMBER);
        log.debug("Тест " + context.getAttribute("testName") + ": открыть проект '" + PROJECT_NAME2 + "'");
        headerPage
                .selectMenuItemDashboard()
                .openProject(PROJECT_NAME2);
    }

    @AfterMethod(description = "После проверки работоспособности функционала, связанного с тест-ранами, удаляем проект")
    public void postcondition(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть сразу нужную страницу, чтобы избежать нажатий на кнопки (нажатия проверялись раньше)");
        log.debug("Тест " + context.getAttribute("testName") + ": удалить проект '" + PROJECT_NAME2 + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность удаления проекта '" + PROJECT_NAME2 + "' - на странице будет выведена надпись '" + PROJECT_SUCCESS_DELETION_MESSAGE + "'");
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL)
                .deleteProject(PROJECT_NAME2)
                .projectActionResultMessage().shouldHave(exactText(PROJECT_SUCCESS_DELETION_MESSAGE));
    }


    @Description("Произвести добавление нового раздела с валидными значениями полей для добавления в него тест-кейсов.")
    @Test(priority = 6, description = "Произвести добавление нового раздела (валидные значения)")
    public void addSectionValidName(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти к созданию нового раздела");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность открытия диалога создания раздела - заголовок на откывшемся диалоге '" + SECTION_DIALOGTITLE + "'");
        testCasePage
                .addNewSection()
                .dialogTitle().shouldHave(exactText(SECTION_DIALOGTITLE));

        log.debug("Тест " + context.getAttribute("testName") + ": создать экземпляр класса конструктора");
        Section section = SectionFactory.get();
        log.debug("Тест " + context.getAttribute("testName") + ": создать новый раздел '" + SECTION_NAME + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность создания раздела '" + SECTION_NAME + "' - он должен отображаться в дереве разделов");
        testCaseAddSectionPage
                .createNewSection(section)
                .sectionNameInSectionTree().shouldHave(exactText(SECTION_NAME)); // проверяем, отображается ли в дереве разделов созданный раздел
    }

    @Description("Произвести удаление раздела") //описание теста
    @Test(priority = 7, description = "Удалить раздел")//название теста
    public void deleteSection(ITestContext context) {
        log.debug("Тест " + context.getAttribute("testName") + ": удаляем заданный раздел '" + SECTION_NAME + "'");
        log.debug("Тест " + context.getAttribute("testName") + ": проверить успешность удаления раздела '" + SECTION_NAME + "' - он не должен отображаться на странице");
        testCasePage
                .deleteSection(SECTION_NAME) //удаляем раздел
                .sectionNameInPage(SECTION_NAME).shouldNot(visible);//проверяем видимость удаленного раздела - не должен быть виден
    }
}