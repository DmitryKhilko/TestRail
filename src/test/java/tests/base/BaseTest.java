package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2 //аннотация для вставки в клласс команд логирования
@Listeners({ ScreenShooter.class}) //аннотация для подготовки для принудительного создания скриншотов (даже для зеленых тестов)
public abstract class BaseTest {

    public LoginPage loginPage;
    public HeaderPage headerPage;
    public MySettingsPage mySettingsPage;
    public DashbordPage dashbordPage;
    public AdminOverviewPage adminOverviewPage;
    public AdminProjectsPage adminProjectsPage;
    public ProjectAddPage projectAddPage;
    public ProjectEditPage projectEditPage;
    public ProjectOverviewPage projectOverviewPage;
    public TestCasePage testCasePage;
    public TestCaseAddSectionPage testCaseAddSectionPage;
    public TestCaseAddCasePage testCaseAddCasePage;
    public TestCaseDetailsPage testCaseDetailsPage;
    public TestRunPage testRunPage;
    public TestRunAddPage testRunAddPage;
    public TestRunDetailsPage testRunDetailsPage;

    public String email, password, userName;

    //Константы, связанные с тестовыми данными
    public static String PROJECT_NAME1 = "Проект 1 (UI)";
    public static String PROJECT_NAME2 = "Проект 2 (UI)";
    public static String PROJECT_NAME3 = "Проект 3 (UI)";
    public static String PROJECT_NAME4 = "Проект 4 (UI)";
    public static String PROJECT_ANNOUNCEMENT_TEXT = "Ссылка на базу знаний...";
    public static int PROJECT_SUITE_MODE_NUMBER = 0; //Use a single repository for all cases (recommended)
    public static String PROJECT_SUCCESS_DELETION_MESSAGE = "Successfully deleted the project."; //сообщение об успехе создания сущности

    public static String SECTION_DIALOGTITLE = "Add Section";
    public static String SECTION_NAME = "Модуль 1";

    public static String TESTCASE_PAGETITLE = "Add Test Case";
    public static String TESTCASE_TITLE = "Тест-кейс 1";
    public static String TESTCASE_TEMPLATE = "Test Case (Text)";
    public static String TESTCASE_TYPE = "Functional";
    public static String TESTCASE_PRYORITY = "Medium";
    public static String TESTCASE_AUTOMATIONTYPE = "None";
    public static String TESTCASE_SUCCESS_ADDED_MESSAGE = "Successfully added the new test case. Add another";

    public static String TESTRUN_PAGETITLE = "Add Test Run";
    public static String TESTRUN_NAME = "Test Run 1";
    public static String TESTRUN_MILESTONE = "Sprint 1";
    public static String TESTRUN_ASSIGN_TO = "Me";
    public static String TESTRUN_TESTCASE_CHOICE = "Include all test cases";


    @Parameters({"BROWSER"}) //параметр из файла regression_multi_browser.xml (для запуска тестов в нескольких браузерах параллельно)
    @BeforeMethod (description = "Настроить и открыть браузер")//Предусловие
    public void setUp(@Optional("chrome") String browser, ITestContext context, ITestResult result) { //@Optional ("chrome") - если не будет передаваться парметр "browser", то запустится по умолчанию в chrome
        log.info("Тест " + result.getMethod().getMethodName() + ": старт"); //команда лога, куда передается имя выполняемого теста
        log.debug("Передать  в тест " + result.getMethod().getMethodName() + " параметр 'BROWSER' со значением: " + browser); //лог для проверки параллельного запуска тестов в нескольких браузерах

        //Выбор, в каком браузере должен запускаться тест
        switch (browser) {
            case "chrome":
                Configuration.browser = "chrome";
                break;
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "edge":
                Configuration.browser = "edge";
                break;
        }

        Configuration.headless = true; // браузер запускается без UI. Тесты ускоряются и становятся более стабильными. Браузер использует меньше ОЗУ (где-то в 3 раза). Этот режим просто необходим при параллелоьном запуске тестов
        Configuration.browserPosition = "0x0"; //команда задает позицию левого верхнего угла браузера. Без нее браузер при запуске смещен немного вправо, что может привести к невидимости каких-то элементов.
        Configuration.browserSize = "1920x1080"; //задает разрешение, с каким запускается браузер. Этот параметр, как и виды браузеров важен и может потребоваться тестировать на разных разрешениях.
        Configuration.timeout = 10000; // неявное ожидание в милисекундах (10 секунд), указывающее на то, какое максимальное количество времени Selenium будет дожидаться появления элемента (аналог implicitlyWait)
        Configuration.fastSetValue = false; //для ускорения заполнения полей ввода ($(PASSWORD_INPUT).setValue(password);). Если установлено значение true, значение устанавливается с помощью javascript вместо использования встроенной в Selenium функции «sendKey» (это довольно медленно, поскольку отправляет каждый символ отдельно). sendKeys() были вынуждены заменить на setValue(), так как Jenkins не мог прогнать тесты
        Configuration.savePageSource = false; //при падении теста не сохраняет Page source (file:/D:/Projects/TestRail/build/reports/tests/1644796597073.11.html)

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false)); //Для взаимодействия Selenide и Allure (https://ru.selenide.org/documentation/reports.html#allure-report)

        email = System.getenv().getOrDefault("TESTRAIL_EMAIL", PropertyReader.getProperty("testrail.email")); //команда, берущая значение для переменной или с настроек CI (TESTRAIL_EMAIL) или из настройки testrail.email файла config.properties
        password = System.getenv().getOrDefault("TESTRAIL_PASSWORD", PropertyReader.getProperty("testrail.password")); //команда, берущая значение для переменной или с настроек CI (TESTRAIL_PASSWORD) или из настройки testrail.password файла config.properties
        userName = System.getenv().getOrDefault("TESTRAIL_USERNAME", PropertyReader.getProperty("testrail.username")); //команда, берущая значение для переменной или с настроек CI (TESTRAIL_USERNAME) или из настройки testrail.username файла config.properties

        context.setAttribute("testName", result.getMethod().getMethodName()); //передаем имя выполняемого теста в методы тестового фреймворка для наглядного формирования логов

        //Инициализация страниц (которые описаны в пакете pages), с которыми мы будем работать в тестах
        loginPage = new LoginPage(context);
        headerPage = new HeaderPage(context);
        mySettingsPage = new MySettingsPage(context);
        dashbordPage = new DashbordPage(context);
        adminOverviewPage = new AdminOverviewPage(context);
        adminProjectsPage = new AdminProjectsPage(context);
        projectAddPage = new ProjectAddPage(context);
        projectEditPage = new ProjectEditPage(context);
        projectOverviewPage = new ProjectOverviewPage(context);
        testCasePage = new TestCasePage(context);
        testCaseAddSectionPage = new TestCaseAddSectionPage(context);
        testCaseAddCasePage = new TestCaseAddCasePage(context);
        testCaseDetailsPage = new TestCaseDetailsPage(context);
        testRunPage = new TestRunPage(context);
        testRunAddPage = new TestRunAddPage(context);
        testRunDetailsPage = new TestRunDetailsPage(context);


    }

    @AfterMethod(alwaysRun = true, description = "Закрыть браузер") //Постусловие
    public void tearDown(ITestResult result) {
        getWebDriver().quit(); //явным образом после каждого теста, расположенного в классе, закрываем браузер, так как по умолчанию в Selenide браузер закрывается по завершению всех методов класса
        log.info("Тест " + result.getMethod().getMethodName() + ": финиш"); //команда лога, куда передается имя выполняемого теста
    }
}