package pages;

import elements.Input;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {
    public static final By CHECKMARK = By.xpath("//span[@class='loginpage-checkmark']");
    public static final By LOGINPAGE_BUTTON = By.xpath("//button[@id='button_primary']");

    //Метод открытия станицы с интерфейсом ввода логина и пароля
    //В соответствии с паттерном Fluent/Chain of Invocations метод возвращает страницу LoginPage и появляется команда "return this;"
    public LoginPage openPage() {
        open(BASE_URL + "/index.php?/auth/login/");
        return this; //возвращаем текущую страницу
    }

    //Метод ввода логина, пароля и входа в приложение
    //В соответствии с паттерном "Fluent/Chain of Invocations" так как после вода логина и пароля мы оказываемся на странице menuPage, метод будет возвращать данную страницу и появляется команда "return this;"
    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    public MenuPage login(String userEmail, String password) {
        new Input("Email").write(userEmail); //вводим в поле ввода "Email" email пользователя (логин)
        new Input("Password").write(password); //вводим в поле ввода "Password" пароль пользователя
        $(CHECKMARK).click(); //снимаем флажок "Keep me logged in"
        $(LOGINPAGE_BUTTON).click(); //кликаем по кнопке "Log in"
        return new MenuPage(); //Инициализуем страницу, с которой дальше будем работать
    }


//    public String getErrorMessage() {
//        return;
//    }


}
