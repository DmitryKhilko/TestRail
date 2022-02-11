package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {
    public static final By USERNAME_INPUT = By.xpath("//input[@id='name']");
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    public static final By CHECKMARK = By.xpath("//span[@class='loginpage-checkmark']");
    public static final By LOGIN_BUTTON = By.xpath("//button[@id='button_primary']");

    //Метод открытия станицы с логином
    public void openPage() {
        open(BASE_URL + "/auth/login/");
    }

    //Метод ввода логина, пароля и входа в приложение
    public void login(String userName, String password) {
        $(USERNAME_INPUT).sendKeys(userName); //вводим в поле ввода "Email" email пользователя
        $(PASSWORD_INPUT).sendKeys(password); //вводим в поле ввода "Password" пароль пользователя
        $(CHECKMARK).click(); //Снимаем флажок "Keep me logged in"
        $(LOGIN_BUTTON).click(); // Кликаем по кнопке "Log in"
    }

//    public String getErrorMessage() {
//        return;
//    }



}
