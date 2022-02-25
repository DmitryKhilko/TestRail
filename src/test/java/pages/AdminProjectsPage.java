package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AdminProjectsPage extends BasePage{

    String navigationSubProjectsLocator = "//a[@id='navigation-sub-projects']"; //пункт меню Projects

    public AdminProjectsPage(ITestContext context) {
        super(context);
    }

    //Создаем метод выбора пункта меню 'Dashboard'
    @Step("Выбрать пункт меню 'Projects'")
    public AdminProjectsPage selectMenuItemProjects() {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Projects'");
        $(By.xpath(navigationSubProjectsLocator)).click();
        return this;
    }
}
