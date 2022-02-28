package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCaseAddCasePage extends BasePage{

    String projectTabSuitesLocator = "//a[@id='navigation-suites']"; //вкладка проекта "Test Cases"
    String addCaseButtonLocator = "//span[contains(text(),'Add Case')]"; //кнопка "Add Case"

    String titleTestCase = "//input[@id='title']"; // заголовок тест-кейса
    String dropdownLocator = "//label[contains(text(), '%s')]/ancestor::td//div[contains(@class, 'chzn-container')]"; //раскрывающийся список
    String optionLocator = "//div[contains(@class, 'chzn-container-active')]//ul/li[contains(text(), '%s')]"; //элемент раскрывающегося списка
    String preconditionsLocator="//div[@id='custom_preconds_display']";
    String stepsLocator="//div[@id='custom_steps_display']";
    String expectedResultLocator="//div[@id='custom_expected_display']";
    String addTestCaseButtonLocator = "//button[contains(text(), 'Add Test Case')]"; //кнопка добавления 'Add Tes Case'

    public TestCaseAddCasePage(ITestContext context) {
        super(context);
    }

    @Step("Создать новый тест-кейс")
    public TestCaseDetailsPage createNewTestCase(String title, String sectionName, String typeTestCase, String preconditions, String steps, String expectedResult) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти на вкладку 'Test cases' проекта для добавления нового раздела");
        $(By.xpath(projectTabSuitesLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку '+Add Case' для перехода к созданию нового тест-кейса");
        $(By.xpath(addCaseButtonLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": ввести название тест-кейса '" + title + "'");
        $(By.xpath(titleTestCase)).clear(); //сначала очищаем поле
        $(By.xpath(titleTestCase)).setValue(title); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": выбрать в раскрывающемся списке 'Section' раздел '" + sectionName + "'");
        $(By.xpath(String.format(dropdownLocator, "Section"))).click(); //щелкаем по раскрывающемуся списку
        $(By.xpath(String.format(optionLocator, sectionName))).click(); //выбираем нужный элемент списка

        log.debug("Тест " + context.getAttribute("testName") + ": выбрать в раскрывающемся списке 'Type' раздел '" + typeTestCase + "'");
        $(By.xpath(String.format(dropdownLocator, "Type"))).click(); //щелкаем по раскрывающемуся списку
        $(By.xpath(String.format(optionLocator, typeTestCase))).click(); //выбираем нужный элемент списка

        log.debug("Тест " + context.getAttribute("testName") + ": ввести предусловия для тест-кейса: " + preconditions);
        $(By.xpath(preconditionsLocator)).clear(); //сначала очищаем поле
        $(By.xpath(preconditionsLocator)).setValue(preconditions); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести шаги для тест-кейса: " + steps);
        $(By.xpath(stepsLocator)).clear(); //сначала очищаем поле
        $(By.xpath(stepsLocator)).setValue(steps); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести ожидаемый результат для тест-кейса: " + expectedResult);
        $(By.xpath(expectedResultLocator)).clear(); //сначала очищаем поле
        $(By.xpath(expectedResultLocator)).setValue(expectedResult); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Add Test Case' для создания тест-кейса");
        $(By.xpath(addTestCaseButtonLocator)).click(); //наводим курсор на название раздела

        return new TestCaseDetailsPage(context); //Инициализуем страницу, на которую переходим
    }
}


