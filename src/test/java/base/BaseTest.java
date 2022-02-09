package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeMethod
    public void setUp(){
        //Configuration.browser="firefox";
        //Configuration.browser="edge";
        Configuration.browser="chrome";
        Configuration.browserVersion="97.0";
        Configuration.headless = true; // браузер запускается без UI. Тесты ускоряются и становятся более стабильными. Браузер использует меньше ОЗУ (где-то в 3 раза). Этот режим просто необходим при параллелоьном запуске тестов
        Configuration.browserSize="1920x1080";
        Configuration.baseUrl="https://hdn.testrail.io/index.php?"; // Настройка важна, чтобы быстро переключаться на разные окружения (test, prod и т.п.)
        Configuration.timeout = 10000; // Неявное ожидание в милисекундах (10 секунд), указывающее на то, какое максимальное количество времени Selenium будет дожидаться появления элемента (аналог implicitlyWait)
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        getWebDriver().quit();
    }
}