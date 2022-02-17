package pages;

import elements.MenuItem;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;

@Log4j2
public class MenuPage extends BasePage {

    public MenuPage(ITestContext context) {
        super(context);
    }

    //Метод выбора пункта меню
    @Step("Выбрать пункт меню")
    public MenuPage selectMenuItem(String menuItemName) {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню '" + menuItemName + "'");
        new MenuItem(menuItemName, menuItemName).select();
        return this;
    }
}