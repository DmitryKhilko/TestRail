package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DashbordPage extends BasePage {

    public String nameProjectsLocator = "//div[contains(@class, 'summary-title')]//a[contains(@href,'index')]"; //имя проекта

    public DashbordPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - пункт меню с именем текущего пользователя
    //Метод нужен для проверки наличия элемента при входе в приложение
    public SelenideElement nameProjects() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - имя проекта");
        return $(By.xpath(nameProjectsLocator));
    }
}


