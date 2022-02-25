package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectPage extends BasePage{

    String textAnnouncementLocator = "//p[contains(text(), 'Ссылка на базу знаний...')]"; //локатор объявления (под названием проекта)

    public ProjectPage(ITestContext context) {
        super(context);
    }

    //Возвращаем элемент, который отображает объявление под названием проекта, чтобы сделать проверку, есть он или нет
    public SelenideElement projectAnnouncement() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - заголовок страницы");
        return $(By.xpath(textAnnouncementLocator));
    }


}
