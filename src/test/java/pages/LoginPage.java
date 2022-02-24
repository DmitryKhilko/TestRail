package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage {

    String loginInputLocator = "//input[@id='name']"; //локатор объявляем здесь (локально на конкретной page), так как он больше нигде не понадобится
    String passwordInputLocator = "//input[@id='password']"; //локатор объявляем здесь (локально на конкретной page), так как он больше нигде не понадобится
    String checboksLocator = "//span[@class='loginpage-checkmark']"; //локатор объявляем здесь (локально на конкретной page), так как он больше нигде не понадобится
    String loginButtonLocator = "//button[@id='button_primary']";
    String errorMessageWhenValueIsEmptyLocator = "//div[contains(@class, 'loginpage-message')]"; //сообщение при пустом поле «Email»: «Email/Login is required» («Требуется электронная почта/логин»); сообщение при пустом поле «Password»: «Password is required» («Необходим пароль»)
    String errorMessageForInvalidValueLocator = "//div[@class='error-text']"; //сообщение при некорректном значении поля «Email» или «Password»: «Sorry, there was a problem. Email/Login or Password is incorrect. Please try again» («Извините, возникла проблема. Электронная почта/логин или пароль неверны. Пожалуйста, попробуйте еще раз »)

    //Конструктор для передачи в команду log имени теста
    public LoginPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - кнопку логина
    // Метод необходим для проверок - попали на станицу логина при выходе из приложения
    public SelenideElement loginButton() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - кнопку 'Log In'");
        return $(By.xpath(loginButtonLocator));
    }

    //Метод, возвращающий элемент - сообщение об ошибке при пустом логине или пароле
    public SelenideElement errorMessageWhenValueIsEmpty() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - сообщение об ошибке при пустом значении логина или пароля");
        return $(By.xpath(errorMessageWhenValueIsEmptyLocator));
    }

    //Метод, возвращающий элемент - сообщение об ошибке при некорректном логине или пароле
    public SelenideElement errorMessageForInvalidValue() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - сообщение об ошибке при некорректном значении логина или пароля");
        return $(By.xpath(errorMessageForInvalidValueLocator));
    }

    //В соответствии с паттерном Fluent/Chain of Invocations метод возвращает страницу LoginPage и появляется команда "return this;"
    @Step("Открыть стартовую страницу приложения")
    public LoginPage openPage(String loginUrl) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина " + BASE_URL + loginUrl);
        open(BASE_URL + loginUrl);
        return this; //возвращаем текущую страницу
    }

    //В соответствии с паттерном "Page Element/Wrappers" создаются элементы Input
    @Step("Войти в приложение, введя логин и пароль")
    public HeaderPage login(String userName, String userPassword) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода логина значение '" + userName + "'");
        $(By.xpath(loginInputLocator)).clear(); //сначала очищаем поле
        $(By.xpath(loginInputLocator)).setValue(userName); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода пароля значение '" + userPassword + "'");
        $(By.xpath(passwordInputLocator)).clear(); //сначала очищаем поле
        $(By.xpath(passwordInputLocator)).setValue(userPassword); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": снять флажок, предлагающий остаться залогиненным");
        $(By.xpath(checboksLocator)).click(); //щелкаем по чекбоксу (снимаем флажок 'Keep me logged in')

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Log In' для входа в приложение");
        loginButton().click(); //нажимаем на кнопку Log In

        return new HeaderPage(context); //Инициализуем страницу, на которую переходим
    }
}
