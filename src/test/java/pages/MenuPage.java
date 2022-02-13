package pages;

import elements.MenuItem;
import org.openqa.selenium.By;

public class MenuPage extends BasePage {
    public static final By TOPMENU_ITEM_USERNAME = By.xpath("//a[@id = 'navigation-user']");
//    public static final By TOPMENU_ITEM_MYSETTINGS = By.xpath("//a[@id = 'navigation-user-settings']");
//    public static final By TOPMENU_ITEM_LOGOUT = By.xpath("//a[@id = 'navigation-user-logout']");

    //Метод выбора пункта меню "My Settings"
    public void selectMenuItemMySettings() {
        //$(TOPMENU_ITEM_USERNAME).click(); //кликаем по пункту меню "Dima Hilko"
        //$(TOPMENU_ITEM_MYSETTINGS).click(); //в раскрывшемся меню кликаем по пункту меню "My Settings"
        new MenuItem("navigation-user").select(); //кликаем по пункту меню "Dima Hilko"
        new MenuItem("navigation-user-settings").select(); //в раскрывшемся меню кликаем по пункту меню "My Settings"
    }

    //Метод выбора пункта меню "Logout"
    //В соответствии с паттерном Fluent/Chain of Invocations после выхода мы оказываемся на странице loginPage, метод будет возвращать данную страницу и появляется команда "return new LoginPage();"
    public LoginPage selectMenuItemLogout() {
        //$(TOPMENU_ITEM_USERNAME).click(); //кликаем по пункту меню "Dima Hilko"
        //$(TOPMENU_ITEM_LOGOUT).click(); //в раскрывшемся меню кликаем по пункту меню "Logout"
        new MenuItem("navigation-user").select(); //кликаем по пункту меню "Dima Hilko"
        new MenuItem("navigation-user-logout").select(); //в раскрывшемся меню кликаем по пункту меню "Logout"
        return new LoginPage();
    }
}