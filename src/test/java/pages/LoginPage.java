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
    public static final By LOGINPAGE_BUTTON = By.xpath("//button[@id='button_primary']");
    String loginButtonLocator = "//span[contains(text(),'%s')]";
    String loginpageCheckark = "//label[contains(text(),'%s')]/span[@class='loginpage-checkmark']";


    public LoginPage(ITestContext context) {
        super(context);
    }

    //В соответствии с паттерном Fluent/Chain of Invocations метод возвращает страницу LoginPage и появляется команда "return this;"
    @Step("Открыть стартовую страницу приложения")
    public LoginPage openPage(String loginUrl) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина " + BASE_URL + "/index.php?/auth/login/");
        open(BASE_URL + loginUrl);
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    @Step("Ввести логин")
    public LoginPage writeLogin(String inputLabel, String loginValue) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода логин");
        new Input(inputLabel).write(loginValue); //вводим в поле ввода "Email" email пользователя (логин)
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    @Step("Ввести пароль")
    public LoginPage writePassword(String inputLabel, String passwordValue) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода пароль");
        new Input(inputLabel).write(passwordValue); //вводим в поле ввода "Password" пароль пользователя
        return this; //возвращаем текущую страницу
    }

    @Step("Снять флажок, предлагающий сохранить логин и пароль для последующих входов в приложение")
    public LoginPage clickCheckark(String checkarkLabel) {
        log.debug("Тест " + context.getAttribute("testName") + ": снять флажок, предлагающий сохранить логин и пароль для последующих входов в приложение");
        $(By.xpath(String.format(loginpageCheckark,checkarkLabel))).click(); //снимаем флажок "Keep me logged in"
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Fluent/Chain of Invocations" так как после вода логина и пароля мы оказываемся на странице menuPage, метод будет возвращать данную страницу и появляется команда "new MenuPage(context)"
    @Step("Нажать кнопку 'Log In' для входа в приложение")
    public MenuPage clickButton(String buttonName) {
        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Log In' для входа в приложение");
        $(By.xpath(String.format(loginButtonLocator,buttonName))).click(); //кликаем по кнопке "Log In"
        return new MenuPage(context); //Инициализуем страницу, с которой дальше будем работать
    }

//    public String getErrorMessage() {
//        return;
//    }


}
