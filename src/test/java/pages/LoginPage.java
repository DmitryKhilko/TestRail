package pages;

import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage {
    public static final By CHECKMARK = By.xpath("//span[@class='loginpage-checkmark']");
    public static final By LOGINPAGE_BUTTON = By.xpath("//button[@id='button_primary']");

    public LoginPage(ITestContext context) {
        super(context);
    }

    //Метод открытия станицы с интерфейсом ввода логина и пароля
    //В соответствии с паттерном Fluent/Chain of Invocations метод возвращает страницу LoginPage и появляется команда "return this;"
    @Step("Открыть стартовую страницу приложения")
    public LoginPage openPage() {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина " + BASE_URL + "/index.php?/auth/login/");
        open(BASE_URL + "/index.php?/auth/login/");
        return this; //возвращаем текущую страницу
    }

    //Метод ввода логина, пароля и входа в приложение
    //В соответствии с паттерном "Fluent/Chain of Invocations" так как после вода логина и пароля мы оказываемся на странице menuPage, метод будет возвращать данную страницу и появляется команда "return this;"
    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    @Step("Ввести логин, пароль и войти в приложение")
    public MenuPage login(String userEmail, String password) {
        log.debug("Тест " + context.getAttribute("testName") + ": на странице логина ввести логин пользователя: " + userEmail);
        new Input("Email").write(userEmail); //вводим в поле ввода "Email" email пользователя (логин)
        log.debug("Тест " + context.getAttribute("testName") + ": на странице логина ввести пароль пользователя: " + password);
        new Input("Password").write(password); //вводим в поле ввода "Password" пароль пользователя
        log.debug("Тест " + context.getAttribute("testName") + ": на странице логина снять флажок 'Keep me logged in'");
        $(CHECKMARK).click(); //снимаем флажок "Keep me logged in"
        log.debug("Тест " + context.getAttribute("testName") + ": на странице логина нажать кнопку 'Log in'");
        $(LOGINPAGE_BUTTON).click(); //кликаем по кнопке "Log in"
        return new MenuPage(context); //Инициализуем страницу, с которой дальше будем работать
    }


//    public String getErrorMessage() {
//        return;
//    }


}
