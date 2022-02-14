package pages;

import elements.MenuItem;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MenuPage extends BasePage {
    public static final By TOPMENU_ITEM_USERNAME = By.xpath("//a[@id = 'navigation-user']");

    //Метод открытия меню, связанного с текущим пользователем
    public void openUserMenu() {
        $(TOPMENU_ITEM_USERNAME).click(); //кликаем по пункту меню "Dima Hilko"
    }

    //Метод выбора пункта меню "My Settings"
    public void selectMenuItemMySettings() {
        openUserMenu();
        new MenuItem("My Settings").select(); //в раскрывшемся меню кликаем по пункту меню "My Settings"
        //todo добавить инициализацию страницы настроек
    }

    //Метод выбора пункта меню "Logout"
    //В соответствии с паттерном Fluent/Chain of Invocations после выхода мы оказываемся на странице loginPage, метод будет возвращать данную страницу и появляется команда "return new LoginPage();"
    public LoginPage selectMenuItemLogout() {
        openUserMenu();
        new MenuItem("Logout").select(); //в раскрывшемся меню кликаем по пункту меню "Logout"
        return new LoginPage();
    }
}