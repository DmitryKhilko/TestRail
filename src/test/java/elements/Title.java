package elements;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Title {
    String contentHeaderTitleLocator = "//div[contains(@class,'content-header-title')]";


    //Метод, возвращающий веб-элемент - заголовок страницы
    //Метод нужен для проверки наличия элемента при открытии страницы
    //Метод вынесли в BasePage, так как данный элемент присутствует на многих страницах
    public SelenideElement getPageTitle() {
        return $(By.xpath(contentHeaderTitleLocator));
    }
}
