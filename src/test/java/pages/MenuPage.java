package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuPage extends BasePage {

    String navigationUserLocator = "//a[@id = 'navigation-user']"; //пункт меню с именем текущего пользователя
    String menuItemLocator = "//a[@class='dropdown-menu-link'][contains(text(),'%s')]"; //остальные пункты меню
    String menuItemName; //переменная часть локатора (%s) - название пункта меню

    public MenuPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - пункт меню с именем текущего пользователя
    //Метод нужен для проверки наличия элемента при входе в приложение
    public SelenideElement menuItemUserName() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - пункт меню с текущим пользователем");
        return $(By.xpath(navigationUserLocator));
    }

    //Создаем метод выбора пункта меню
    @Step("Выбрать пункт меню")
    public MenuPage selectMenuItem(String menuItemName) {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню с именем текущего пользователя");
        $(By.xpath(navigationUserLocator)).click();
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню '" + menuItemName + "'");
        $(By.xpath(String.format(menuItemLocator, menuItemName))).click();
        return this;
    }
}