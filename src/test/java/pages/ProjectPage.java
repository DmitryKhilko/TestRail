package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectPage extends BasePage{

    public ProjectPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - заголовок страницы
    //Метод нужен для проверки наличия элемента при открытии страницы
    //Метод вынесли в BasePage, так как данный элемент присутствует на многих страницах
    public SelenideElement pageTitle() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - заголовок страницы");
        return $(By.xpath(contentHeaderTitleLocator));
    }
}
