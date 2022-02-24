package tests;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;

@Log4j2
//Проверка входа в приложение
public class LoginTest extends BaseTest {
    @TmsLink("8537") //ссылка на тест-кейс в TMS
    @Issue("1988") //ссылка на баг-репорт
    @Description("Проверить после входа в приложение наличие пункта меню с именем текущего пользователя") //описание теста
    @Test(priority = 1, description = "Войти в приложение (валидные логин и пароль)")//название теста, название группы
    public void loginValidUsernameAndPassword(ITestContext context) {
        ScreenShooter.captureSuccessfulTests = true; //команда, разрешающая делать скриншоты для зеленых тестов (только скрины с проверок shouldHave и shouldBe
        loginPage
                .openPage("/auth/login/")
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, вошли ли в приложение - в меню должен отображаться текущий пользователь - '"+ userName + "'");
        menuPage.menuItemUserName().shouldHave(exactText(userName)); ////на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko" (переменная берущая значение из файла config.properties)
    }

    @Description("Проверить, появится ли сообщение об ошибке при вводе правильного логина и неправильного пароля") //описание теста
    @Test(priority = 2, description = "Войти в приложение (логин валидный, пароль не валидный)")//название теста
    public void loginValidUsernameAndInvalidPassword(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, "123"); //email - переменная, берущая значение из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": при вводе некорректного пароля проверить появление сообщения об ошибке 'Email/Login or Password is incorrect'");
        loginPage.errorMessageForInvalidValue().shouldHave(exactText("Email/Login or Password is incorrect. Please try again.")); //при попытке входа с некорректным паролем выдается сообщение об ошибке
    }

    @Description("Проверить, появится ли сообщение об ошибке при вводе некорректного логина и правильного пароля") //описание теста
    @Test(priority = 3, description = "Войти в приложение (логин невалидный, пароль валидный)")//название теста
    public void loginInvalidUsernameAndValidPassword(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login("123", password); //password - переменная, берущая значение из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": при вводе некорректного логина проверить появление сообщения об ошибке 'Email/Login or Password is incorrect'");
        loginPage.errorMessageForInvalidValue().shouldHave(exactText("Email/Login or Password is incorrect. Please try again."));
    }

    @Description("Проверить, появится ли сообщение об ошибке при вводе корректного логина и пустого пароля") //описание теста
    @Test(priority = 4, description = "Войти в приложение (логин валидный, пароль пустой)")//название теста
    public void loginValidUsernameAndIsEmptyPassword(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, ""); //email - переменная, берущая значение из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": при вводе пустого пароля проверить появление сообщения об ошибке 'Password is required'");
        loginPage.errorMessageWhenValueIsEmpty().shouldHave(exactText("Password is required."));
    }

    @Description("Проверить, появится ли сообщение об ошибке при вводе пустого логина и корректного пароля") //описание теста
    @Test(priority = 5, description = "Войти в приложение (логин пустой, пароль корректный)")//название теста
    public void loginIsEmptyUsernameAndValidPassword(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login("", password); //password - переменная, берущая значение из файла config.properties
        log.debug("Тест " + context.getAttribute("testName") + ": при вводе пустого логина проверить появление сообщения об ошибке 'Email/Login is required'");
        loginPage.errorMessageWhenValueIsEmpty().shouldHave(exactText("Email/Login is required."));
    }
}