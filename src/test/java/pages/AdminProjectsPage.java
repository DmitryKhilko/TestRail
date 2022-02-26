package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class AdminProjectsPage extends BasePage{

    public static final String ADMIN_PROJECT_PAGE_URL = "/admin/projects/overview";
    String navigationSubProjectsLocator = "//a[@id='navigation-sub-projects']"; //пункт меню Projects
    String iconDeleteLocator = "//table[@class='grid']//a[contains(text(),'%s')]/ancestor::tr//div[@class='icon-small-delete']"; // кружочeк с крестиком для удаления проекта на странице
    String deleteCheckboxLocator = "//div[@id='deleteDialog']//input[@name='deleteCheckbox']"; // чекбокс на даилоговом окне подтверждения удаления проекта
    String okButtonLocator = " //div[@id='deleteDialog']//a[contains(@class, 'button-ok')]"; // кнопка Ok на даилоговом окне подтверждения удаления проекта
    String messageSuccessLocator = "//div[contains(@class, 'message-success')]"; //сообщение об успешном удалении проекта

    public AdminProjectsPage(ITestContext context) {
        super(context);
    }

    //Для дальнейших тестов, без всяких кликов переходим на нужную страницу
    @Step("Открыть страницу приложения 'Projects'")
    public AdminProjectsPage openPage(String loginUrl) {
        log.debug("Тест " + context.getAttribute("testName") + ": открыть страницу логина " + BASE_URL + loginUrl);
        open(BASE_URL + loginUrl);
        return this; //возвращаем текущую страницу
    }

    //Создаем метод выбора пункта меню 'Dashboard'
    @Step("Выбрать пункт меню 'Projects'")
    public AdminProjectsPage selectMenuItemProjects() {
        log.debug("Тест " + context.getAttribute("testName") + ": выбрать пункт меню 'Projects'");
        $(By.xpath(navigationSubProjectsLocator)).click();
        return this;
    }

    //Метод удаления одного проекта
    @Step("Удалить проект на странице 'Projects'")
    public AdminProjectsPage deleteProject(String projectName){
        log.debug("Тест " + context.getAttribute("testName") + ": нажать на кнопку удаления напротив проекта");
        $(By.xpath((String.format(iconDeleteLocator, projectName)))).click();
        log.debug("Тест " + context.getAttribute("testName") + ": в диалоговом окне подтверждения удаления поставить флажок");
        $(By.xpath(deleteCheckboxLocator)).click();
        log.debug("Тест " + context.getAttribute("testName") + ": в даилоговом окне удаления проекта нажать кнопку 'Ок'");
        $(By.xpath(okButtonLocator)).click();
        return this;
    }

    //Метод возвращает вэб-элемент - надпись об успешном удалении проекта
    public SelenideElement projectActionResultMessage() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - пункт меню 'Dashboard'");
        return $(By.xpath(messageSuccessLocator));
    }
}
