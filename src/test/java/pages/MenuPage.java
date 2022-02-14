package pages;

import elements.MenuItem;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuPage extends BasePage {
    public static final By TOPMENU_ITEM_USERNAME = By.xpath("//a[@id = 'navigation-user']");

    public MenuPage(ITestContext context) {
        super(context);
    }

    //Метод открытия меню, связанного с текущим пользователем
    @Step("Кликнуть по пункту меню с именем текущего пользователя, чтобы открыть меню")
    public MenuPage openUserMenu() {
        log.debug("Тест " + context.getAttribute("testName") + ": кликнуть по пункту меню с именем текущего пользователя, чтобы открыть вложенное меню");
        $(TOPMENU_ITEM_USERNAME).click(); //кликаем по пункту меню "Dima Hilko"
        return this;
    }

    //Метод выбора пункта меню "My Settings"
    public void selectMenuItemMySettings() {
        openUserMenu();
        new MenuItem("My Settings").select(); //в раскрывшемся меню кликаем по пункту меню "My Settings"
        //todo добавить инициализацию страницы настроек
    }

    //Метод выбора пункта меню "Logout"
    //В соответствии с паттерном Fluent/Chain of Invocations после выхода мы оказываемся на странице loginPage, метод будет возвращать данную страницу и появляется команда "return new LoginPage();"
    @Step("Выбрать пункт меню Logout для выхода из приложения")
    public LoginPage selectMenuItemLogout() {
        log.debug("Тест " + context.getAttribute("testName") + ": для выхода из приложения кликнуть по пункту меню 'Logout'");
        new MenuItem("Logout").select(); //в раскрывшемся меню кликаем по пункту меню "Logout"
        return new LoginPage(context);
    }
}