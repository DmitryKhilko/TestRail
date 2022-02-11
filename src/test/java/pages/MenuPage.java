package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class MenuPage extends BasePage {
   public static final By MENU_USERNAME = By.xpath("//span[@class = 'navigation-username']");
   public static final By MENU_MYSETTINGS = By.xpath("//a[@id = 'navigation-user-settings']");
   public static final By MENU_LOGOUT = By.xpath("//a[@id = 'navigation-user-logout']");

   //Метод проверки успешности входа в веб-приложение
   public void isLoginSuccessful() {
      //$(MENU_USERNAME).shouldBe(exactText("Dima Hilko"), Duration.ofSeconds(20)); // Duration.ofSeconds(20) - установка ожидания появления элемента, отличного от установки в BaseTest (там сейчас 5 секунд). На случай, если какие-то части приложения работают медленно
      $(MENU_USERNAME).shouldHave(exactText("Dima Hilko")); // Проверка успешности входа в веб-приложение: текст пункта меню должен иметь текст "Dima Hilko" на открывшейся странице
   }

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