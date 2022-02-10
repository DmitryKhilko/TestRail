import base.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest extends BaseTest {
    @Test
    public void logInlogOutCorrectUsernameCorrectPassword(){
        open("/auth/login/");
        $(byXpath("//input[@name='name']")).sendKeys("hdn_tms@mail.ru"); //вводим в поле ввода "Email" email пользователя
        $(byXpath("//input[@name='password']")).sendKeys("pVui0CaU1AsUDIXrPMws"); //вводим в поле ввода "Password" пароль пользователя
        $(byXpath("//span[@class='loginpage-checkmark']")).click(); //Снимаем флажок "Keep me logged in"
        $(byXpath("//button[@id='button_primary']")).click(); // Кликаем по кнопке "Log in"
        //$(byXpath("//span[contains(text(),'Dima Hilko')]")).shouldBe(visible, Duration.ofSeconds(20)); // Duration.ofSeconds(20) - установка ожидания появления элемента, отличного от установки в BaseTest (там сейчас 5 секунд). На случай, если какие-то части приложения работают медленно
        $(byXpath("//span[contains(text(),'Dima Hilko')]")).shouldBe(visible); // Проверка успешности входа в веб-приложение: проверяем наличие пункта меню "Dima Hilko" на открывшейся странице

        $(byXpath("//span[contains(text(),'Dima Hilko')]")).click(); // Кликаем по пункту меню "Dima Hilko"
        $(byXpath("//a[@id='navigation-user-logout']")).click(); // Кликаем по пункту меню "Logout"
        $(byXpath("//button[@id='button_primary']")).shouldBe(visible); // Проверка успешности выхода в веб-приложение: проверяем наличие кнопки "Log in" на открывшейся странице
    }
}
