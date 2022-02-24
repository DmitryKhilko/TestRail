package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ErrorMessage {

    //Подумать, как лучше сделать?

    String errorMessageLocator = "//div[@class='error-text']|//div[@class='message message-error']"; //сообщение об ошибке при неверном логине или пароле (//div[@class='error-text']) или при пустом значении name сущностей TestRail (//div[@class='message message-error'])

    //Метод проверки вывода сообщения об ошибке
    public String getErrorMessage() {
        return $(By.xpath(errorMessageLocator)).getText(); //выводим сообщение об ошибке
    }
}
