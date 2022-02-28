package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage{

    String projectTabSuitesLocator = "//a[@id='navigation-suites']"; //вкладка проекта "Test Cases"
    String nameSectionTreeLocator = "//div[@id='groupTreeContent']//span[contains(@id,'sectionName')]"; //локатор элемента раздела в дереве разделов

    String nameSectionGroupsLocator = "//div[@id='groups']//span[contains(text(),'%s')]"; //локатор элемента раздела с конкретным именем на странице Test Cases
    String iconDeleteSectionLocator = "//div[@id='groups']//span[contains(text(),'%s')]/ancestor::div[@class='grid-title']//div[contains(@class,'icon-small-delete')]"; // кружочeк с крестиком для удаления раздела на странице
    String deleteCheckboxLocator = "//div[@id='deleteDialog']//input[@name='deleteCheckbox']"; // чекбокс на даилоговом окне подтверждения удаления проекта
    String okButtonLocator = "//div[@id='deleteDialog']//a[contains(@class, 'button-ok')]"; // кнопка Ok на даилоговом окне подтверждения удаления проекта

    public TestCasePage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - элемент дерева разделов с определенным именем для последующей проверки добавления раздела
    public SelenideElement sectionNameInSectionTree() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - раздел дерева разделов");
        return $(By.xpath(nameSectionTreeLocator));
    }

    @Step("Удалить раздел")
    public TestCasePage deleteSection(String sectionName) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти на вкладку 'Test cases' проекта для удаления раздела");
        $(By.xpath(projectTabSuitesLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": навести курсор над разделом '" + sectionName + "'");
        $(By.xpath((String.format(nameSectionGroupsLocator, sectionName)))).hover();

        log.debug("Тест " + context.getAttribute("testName") + ": нажать на появившуюся кнопку удаления раздела");
        $(By.xpath((String.format(iconDeleteSectionLocator, sectionName)))).click();

        log.debug("Тест " + context.getAttribute("testName") + ": в диалоговом окне подтверждения удаления поставить флажок");
        $(By.xpath(deleteCheckboxLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": в даилоговом окне удаления проекта нажать кнопку 'Ок'");
        $(By.xpath(okButtonLocator)).click();

        return this; //Инициализуем страницу, на которую переходим
    }

    //Метод, возвращающий веб-элемент - раздел с определенным именем для последующей проверки его удаления
    public SelenideElement sectionNameInPage(String sectionName) {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - раздел на странице");
        return $(By.xpath((String.format(nameSectionGroupsLocator, sectionName))));
    }
}


