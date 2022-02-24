package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MySettingsPage extends BasePage{

    String contentHeaderTitleLocator = "//div[contains(@class,'content-header-title')]";

    public MySettingsPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - заголовок страницы - My Settings
    //Метод нужен для проверки наличия элемента при открытии страницы
    public SelenideElement pageTitle() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - заголовок страницы - My Settings");
        return $(By.xpath(contentHeaderTitleLocator));
    }
}
