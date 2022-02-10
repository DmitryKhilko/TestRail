package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MenuPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    public LoginPage loginPage;
    public MenuPage menuPage;

    @BeforeMethod
    public void setUp(){
        Configuration.browser="firefox";
        //Configuration.browser="edge";
        //Configuration.browser="chrome";
        //Configuration.headless = true; // браузер запускается без UI. Тесты ускоряются и становятся более стабильными. Браузер использует меньше ОЗУ (где-то в 3 раза). Этот режим просто необходим при параллелоьном запуске тестов
        Configuration.browserSize="1920x1080";
        //Configuration.baseUrl="https://hdn.testrail.io/index.php?"; // Настройка важна, чтобы быстро переключаться на разные окружения (test, prod и т.п.)
        Configuration.timeout = 10000; // неявное ожидание в милисекундах (10 секунд), указывающее на то, какое максимальное количество времени Selenium будет дожидаться появления элемента (аналог implicitlyWait)

        //Инициализация страниц, с которыми мы будем работать в тестах
        loginPage = new LoginPage();
        menuPage = new MenuPage();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        getWebDriver().quit();
    }
}
