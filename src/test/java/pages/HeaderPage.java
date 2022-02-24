package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class HeaderPage extends BasePage {

    String navigationUserLocator = "//a[@id = 'navigation-user']"; //пункт меню с именем текущего пользователя
    String menuItemLocator = "//a[@class='dropdown-menu-link'][contains(text(),'%s')]"; //остальные пункты меню
    String menuItemName; //переменная часть локатора (%s) - название пункта меню
    String navigationDashboardLocator = "//a[@id='navigation-dashboard']"; // пункт меню 'Dashboard'
    public String navigationAdminLocator = "//a[@id='navigation-admin']"; // пункт меню 'Administration'

    public HeaderPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - пункт меню с именем текущего пользователя
    //Метод нужен для проверки наличия элемента при входе в приложение
    public SelenideElement menuItemUserName() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - пункт меню с текущим пользователем");
        return $(By.xpath(navigationUserLocator));
    }

//    //Метод, возвращающий веб-элемент - пункт меню 'Dashboard'
//    public SelenideElement menuItemDashboard() {
//        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - пункт меню 'Dashboard'");
//        return $(By.xpath(navigationDashboardLocator));
//    }
//
//    //Метод, возвращающий веб-элемент - пункт меню 'Administration'
//    public SelenideElement menuItemAdministration() {
//        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - пункт меню 'Administration'");
//        return $(By.xpath(navigationAdminLocator));
//    }

    //Создаем метод выбора пункта меню 'Dashboard'
    @Step("Выбрать пункт меню 'Dashboard'")
    public DashbordPage selectMenuItemDashboard() {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Dashboard'");
        $(By.xpath(navigationDashboardLocator)).click();
        return new DashbordPage(context);
    }

    //Создаем метод выбора пункта меню 'Administration'
    @Step("Выбрать пункт меню 'Administration'")
    public AdminOverviewPage selectMenuItemAdministration() {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Administration'");
        $(By.xpath(navigationAdminLocator)).click();
        return new AdminOverviewPage(context);
    }

    //Создаем метод выбора пункта меню
    @Step("Выбрать пункт меню")
    public HeaderPage selectMenuItem(String menuItemName) {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню с именем текущего пользователя");
        $(By.xpath(navigationUserLocator)).click();
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню '" + menuItemName + "'");
        $(By.xpath(String.format(menuItemLocator, menuItemName))).click();
        return this;
    }
}