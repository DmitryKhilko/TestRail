package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
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
    public TestCaseDetailsPage createNewTestCase(TestCase testCase) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести название тест-кейса '" + testCase.getTitle() + "'");
        $(By.xpath(titleTestCase)).clear(); //сначала очищаем поле
        $(By.xpath(titleTestCase)).setValue(testCase.getTitle()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": выбрать в раскрывающемся списке 'Section' раздел '" + testCase.getSection() + "'");
        $(By.xpath(String.format(dropdownLocator, "Section"))).click(); //щелкаем по раскрывающемуся списку
        $(By.xpath(String.format(optionLocator, testCase.getSection()))).click(); //выбираем нужный элемент списка

        log.debug("Тест " + context.getAttribute("testName") + ": выбрать в раскрывающемся списке 'Type' раздел '" + testCase.getType() + "'");
        $(By.xpath(String.format(dropdownLocator, "Type"))).click(); //щелкаем по раскрывающемуся списку
        $(By.xpath(String.format(optionLocator, testCase.getType()))).click(); //выбираем нужный элемент списка

        log.debug("Тест " + context.getAttribute("testName") + ": ввести предусловия для тест-кейса: " + testCase.getPreconditions());
        $(By.xpath(preconditionsLocator)).clear(); //сначала очищаем поле
        $(By.xpath(preconditionsLocator)).setValue(testCase.getPreconditions()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести шаги для тест-кейса: " + testCase.getSteps());
        $(By.xpath(stepsLocator)).clear(); //сначала очищаем поле
        $(By.xpath(stepsLocator)).setValue(testCase.getSteps()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести ожидаемый результат для тест-кейса: " + testCase.getExpectedResult());
        $(By.xpath(expectedResultLocator)).clear(); //сначала очищаем поле
        $(By.xpath(expectedResultLocator)).setValue(testCase.getExpectedResult()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Add Test Case' для создания тест-кейса");
        $(By.xpath(addTestCaseButtonLocator)).click(); //наводим курсор на название раздела

        return new TestCaseDetailsPage(context); //Инициализуем страницу, на которую переходим
    }
}


