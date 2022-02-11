package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {
    public static final By USERNAME_INPUT = By.xpath("//input[@id='name']");
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    public static final By CHECKMARK = By.xpath("//span[@class='loginpage-checkmark']");
    public static final By LOGIN_BUTTON = By.xpath("//button[@id='button_primary']");

    //Метод открытия станицы с интерфейсом ввода логина и пароля
    public void openPage() {
        open(BASE_URL + "/auth/login/");
    }

    @Override
    //Метод, определяющий, что страница загружена, если отображается кнопка "Log in"
    public boolean isOpenPage() {
        return isExist(LOGIN_BUTTON);
    }

//    //Метод определения успешности окрытия приложения и выхода из приложения
//    public void isVisibleLoginButton() {
//        //$(LOGIN_BUTTON).shouldBe(visible, Duration.ofSeconds(20));
//        $(LOGIN_BUTTON).shouldBe(visible); //проверяем наличие кнопки "Log in" на открывшейся странице
//    }

    //Метод ввода логина, пароля и входа в приложение
    public void login(String userName, String password) {
        $(USERNAME_INPUT).setValue(userName); //вводим в поле ввода "Email" email пользователя
        $(PASSWORD_INPUT).setValue(password); //вводим в поле ввода "Password" пароль пользователя
        $(CHECKMARK).click(); //снимаем флажок "Keep me logged in"
        $(LOGIN_BUTTON).click(); //кликаем по кнопке "Log in"
    }


//    public String getErrorMessage() {
//        return;
//    }


}
