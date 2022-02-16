package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.DashbordPage;
import pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2 //аннотация для вставки в клласс команд логирования
@Listeners({ ScreenShooter.class}) //аннотация для подготовки для принудительного создания скриншотов (даже для зеленых тестов)
public abstract class BaseTest {
    public LoginPage loginPage;
    //public MenuPage menuPage;
    public DashbordPage dashbordPage;

    @Parameters({"browser"}) //параметр из файла regression_multi_browser.xml (для запуска тестов в нескольких браузерах параллельно)
    @BeforeMethod (description = "Настроить и открыть браузер")//Предусловие
    public void setUp(@Optional("chrome") String browser, ITestContext context, ITestResult result) { //@Optional ("chrome") - если не будет передаваться парметр "browser", то запустится по умолчанию в chrome
        log.info("Тест " + result.getMethod().getMethodName() + ": старт"); //команда лога, куда передается имя выполняемого теста
        log.debug("Передать  из 'multi_browser.xml' в тест " + result.getMethod().getMethodName() + " параметр 'browser' со значением: " + browser); //лог для проверки параллельного запуска тестов в нескольких браузерах

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

        //Configuration.headless = true; // браузер запускается без UI. Тесты ускоряются и становятся более стабильными. Браузер использует меньше ОЗУ (где-то в 3 раза). Этот режим просто необходим при параллелоьном запуске тестов
        Configuration.browserPosition = "0x0"; //команда задает позицию левого верхнего угла браузера. Без нее браузер при запуске смещен немного вправо, что может привести к невидимости каких-то элементов.
        Configuration.browserSize = "1920x1080"; //задает разрешение, с каким запускается браузер. Этот параметр, как и виды браузеров важен и может потребоваться тестировать на разных разрешениях.
        Configuration.timeout = 10000; // неявное ожидание в милисекундах (10 секунд), указывающее на то, какое максимальное количество времени Selenium будет дожидаться появления элемента (аналог implicitlyWait)
        Configuration.fastSetValue = true; //для ускорения заполнения полей ввода ($(PASSWORD_INPUT).setValue(password);). Если установлено значение true, значение устанавливается с помощью javascript вместо использования встроенной в Selenium функции «sendKey» (это довольно медленно, поскольку отправляет каждый символ отдельно). sendKeys() были вынуждены заменить на setValue(), так как Jenkins не мог прогнать тесты
        Configuration.savePageSource = false; //при падении теста не сохраняет Page source (file:/D:/Projects/TestRail/build/reports/tests/1644796597073.11.html)
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false)); //Для взаимодействия Selenide и Allure (https://ru.selenide.org/documentation/reports.html#allure-report)

        context.setAttribute("testName", result.getMethod().getMethodName()); //передаем имя выполняемого теста в методы тестового фреймворка для наглядного формирования логов

        //Инициализация страниц (которые описаны в пакете pages), с которыми мы будем работать в тестах
        loginPage = new LoginPage(context);
        //menuPage = new MenuPage(context); вызываю страницу из метода login
        dashbordPage = new DashbordPage(context);
    }

    @AfterMethod(alwaysRun = true, description = "Закрыть браузер") //Постусловие
    public void tearDown(ITestResult result) {
        getWebDriver().quit(); //явным образом после каждого теста, расположенного в классе, закрываем браузер, так как по умолчанию в Selenide браузер закрывается по завершению всех методов класса
        log.info("Тест " + result.getMethod().getMethodName() + ": завершение"); //команда лога, куда передается имя выполняемого теста
    }
}