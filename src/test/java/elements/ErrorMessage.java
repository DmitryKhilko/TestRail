package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ErrorMessage {

    //Подумать, как лучше сделать?

    String errorMessageLocator = "//div[@class='error-text']|//div[@class='message message-error']"; //сообщение об ошибке при неверном логине или пароле (//div[@class='error-text']) или при пустом значении name сущностей TestRail (//div[@class='message message-error'])

    //Метод, возвращающий веб-элемент - сообщение об ошибке
    public SelenideElement getErrorMessageElement() {
        return $(By.xpath(errorMessageLocator));
    }
}
