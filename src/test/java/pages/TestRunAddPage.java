package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestRunAddPage extends BasePage{

    String nameTestRunLocator = "//input[@id='name']"; // поле Name
    String referencesTestRunLocator = "//input[@id='refs']"; // поле References
    String addTestRunButtonLocator = "//button[@id='accept']"; //кнопка 'Add Test Run'


    public TestRunAddPage(ITestContext context) {
        super(context);
    }

    @Step("Создать новый тест-ран")
    public TestRunDetailsPage createNewTestRun(TestRun testRun) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести название тест-рана '" + testRun.getName() + "'");
        $(By.xpath(nameTestRunLocator)).clear(); //сначала очищаем поле
        $(By.xpath(nameTestRunLocator)).setValue(testRun.getName()); //потом вводим текст
        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Add Test Run' для создания тест-рана");
        $(By.xpath(addTestRunButtonLocator)).click(); //нажимаем на кнопку добавления тест рана
        return new TestRunDetailsPage(context); //Инициализуем страницу, на которую переходим
    }
}
