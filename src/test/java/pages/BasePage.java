package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public abstract class BasePage {

    public static final String BASE_URL = System.getenv().getOrDefault("TESTRAIL_BASEURL", PropertyReader.getProperty("testrail.baseurl")); //команда, берущая значение для переменной или с настроек CI (TESTRAIL_BASEURL) или из настройки testrail.authorization файла config.baseurl (вместо команды public static final String BASE_URL = "https://hdn.testrail.io/index.php?"; - dынесли по причине того, что адрес может изменится и изменим только в одном месте. Также это важно, чтобы быстро переключаться на разные окружения (test, prod и т.п.)
//    String navigationDashboardLocator = "//a[@id='navigation-dashboard']"; // пункт меню 'Dashboard'
//    public String navigationAdminLocator = "//a[@id='navigation-admin']"; // пункт меню 'Administration'
    String contentHeaderTitleLocator = "//div[contains(@class,'content-header-title')]";
    ITestContext context;

    public BasePage(ITestContext context) {
        this.context = context;
    }

//    //Создаем метод выбора пункта меню 'Dashboard'
//    @Step("Выбрать пункт меню 'Dashboard'")
//    public DashbordPage selectMenuItemDashboard() {
//        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Dashboard'");
//        $(By.xpath(navigationDashboardLocator)).click();
//        return new DashbordPage(context);
//    }
//
//    //Создаем метод выбора пункта меню 'Administration'
//    @Step("Выбрать пункт меню 'Administration'")
//    public AdminOverviewPage selectMenuItemAdministration() {
//        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Administration'");
//        $(By.xpath(navigationAdminLocator)).click();
//        return new AdminOverviewPage(context);
//    }

    //Метод, возвращающий веб-элемент - заголовок страницы
    //Метод нужен для проверки наличия элемента при открытии страницы
    //Метод вынесли в BasePage, так как данный элемент присутствует на многих страницах
    public SelenideElement pageTitle() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - заголовок страницы");
        return $(By.xpath(contentHeaderTitleLocator));
    }








}
