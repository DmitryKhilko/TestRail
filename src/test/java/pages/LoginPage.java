package pages;

import elements.Button;
import elements.Checboks;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage {



    public LoginPage(ITestContext context) {
        super(context);
    }

    //В соответствии с паттерном Fluent/Chain of Invocations метод возвращает страницу LoginPage и появляется команда "return this;"
    @Step("Открыть стартовую страницу приложения")
    public LoginPage openPage(String loginUrl) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина " + BASE_URL + loginUrl);
        open(BASE_URL + loginUrl);
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    @Step("Заполнить поле ввода")
    public LoginPage writeToInput(String inputLabel, String value) {
        log.debug("Тест " + context.getAttribute("testName") + ": заполнить поле ввода '" + inputLabel + "' значением " + value);
        new Input(inputLabel).write(value); //вводим в поле ввода значение (логин или пароль)
        return this; //возвращаем текущую страницу
    }

    @Step("Снять флажок, предлагающий сохранить логин и пароль для последующих входов в приложение")
    public LoginPage clickCheckboks(String checkboksLabel) {
        log.debug("Тест " + context.getAttribute("testName") + ": снять флажок '" +checkboksLabel+ "', предлагающий сохранить логин и пароль для последующих входов в приложение");
        new Checboks(checkboksLabel).click(checkboksLabel); //снимаем флажок "Keep me logged in"
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Fluent/Chain of Invocations" так как после вода логина и пароля мы оказываемся на странице menuPage, метод будет возвращать данную страницу и появляется команда "new MenuPage(context)"
    @Step("Нажать кнопку 'Log In' для входа в приложение")
    public MenuPage clickButton(String buttonName) {
        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку '" + buttonName + "' для входа в приложение");
        new Button(buttonName, buttonName).click(buttonName);
        return new MenuPage(context); //Инициализуем страницу, с которой дальше будем работать
    }

//    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
//    @Step("Заполнить поле ввода")
//    public LoginPage getErrorMessageNotCorrectEmail() {
//        log.debug("Тест " + context.getAttribute("testName") + ": вывести сообщение об ошибке при неправильном Email");
//        new getErrorMessage(); //вводим в поле ввода значение (логин или пароль)
//        return this; //возвращаем текущую страницу
//    }

}
