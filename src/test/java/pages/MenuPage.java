package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MenuPage extends BasePage {
   public static final By MENU_USERNAME = By.xpath("//span[@class = 'navigation-username']");
   public static final By MENU_MYSETTINGS = By.xpath("//a[@id = 'navigation-user-settings']");
   public static final By MENU_LOGOUT = By.xpath("//a[@id = 'navigation-user-logout']");

   //Метод выбора пункта меню "My Settings"
   public void selectMenuItemMySettings() {
      $(MENU_USERNAME).click(); // Кликаем по пункту меню "Dima Hilko"
      $(MENU_MYSETTINGS).click(); // В раскрывшемся меню кликаем по пункту меню "My Settings"
   }

   //Метод выбора пункта меню "Logout"
   public void selectMenuItemLogout() {
      $(MENU_USERNAME).click(); // Кликаем по пункту меню "Dima Hilko"
      $(MENU_LOGOUT).click(); // В раскрывшемся меню кликаем по пункту меню "Logout"
   }

}