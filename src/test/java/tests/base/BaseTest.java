package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.DashbordPage;
import pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BaseTest {
    public LoginPage loginPage;
    //public MenuPage menuPage;
    public DashbordPage dashbordPage;

    @Parameters({"browser"})
    @BeforeMethod //Предусловие
    public void setUp(@Optional("chrome") String browser) { //@Optional ("chrome") - если не будет передаваться парметр "browser", то запустится по умолчанию в chrome
        //Выбор, в каком браузере должен запускаться тест
        if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
        } else if (browser.equals("firefox")) {
            Configuration.browser = "firefox";
        } else if (browser.equals("edge")) {
            Configuration.browser = "edge";
        }

        Configuration.headless = true; // браузер запускается без UI. Тесты ускоряются и становятся более стабильными. Браузер использует меньше ОЗУ (где-то в 3 раза). Этот режим просто необходим при параллелоьном запуске тестов
        Configuration.browserPosition = "0x0"; //команда задает позицию левого верхнего угла браузера. Без нее браузер при запуске смещен немного вправо, что может привести к невидимости каких-то элементов.
        Configuration.browserSize = "1920x1080"; //задает разрешение, с каким запускается браузер. Этот параметр, как и виды браузеров важен и может потребоваться тестировать на разных разрешениях.
        Configuration.timeout = 10000; // неявное ожидание в милисекундах (10 секунд), указывающее на то, какое максимальное количество времени Selenium будет дожидаться появления элемента (аналог implicitlyWait)
        Configuration.fastSetValue = true; //для ускорения заполнения полей ввода ($(PASSWORD_INPUT).setValue(password);). Если установлено значение true, значение устанавливается с помощью javascript вместо использования встроенной в Selenium функции «sendKey» (это довольно медленно, поскольку отправляет каждый символ отдельно).
        Configuration.savePageSource = false; //при падении теста не сохраняет Page source (file:/D:/Projects/TestRail/build/reports/tests/1644796597073.11.html)

        //Инициализация страниц (которые описаны в пакете pages), с которыми мы будем работать в тестах
        loginPage = new LoginPage();
        //menuPage = new MenuPage(); вызываю страницу из метода login
        dashbordPage = new DashbordPage();
    }

    @AfterMethod(alwaysRun = true) //Постусловие
    public void tearDown() {
        getWebDriver().quit(); //явным образом после каждого теста, расположенного в классе, закрываем браузер, так как по умолчанию в Selenide браузер закрывается по завершению всех методов класса
    }
}
