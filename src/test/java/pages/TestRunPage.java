package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestRunPage extends BasePage{

    String projectTabRunLocator = "//a[@id='navigation-runs']"; //вкладка проекта "Test Runs & Results"
    String addTestRunButtonLocator = "//div[@class= 'sidebar-inner']/a[@id='navigation-runs-add']"; //кнопка добавления тест рана

    public TestRunPage(ITestContext context) {
        super(context);
    }

    @Step("Переход к созданию нового тест-рана")
    public TestRunAddPage addNewTestRun() {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти на вкладку 'Test Runs & Results' проекта для добавления нового тест-рана");
        $(By.xpath(projectTabRunLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку '+Add Case' для перехода к созданию нового тест-кейса");
        $(By.xpath(addTestRunButtonLocator)).click();

        return new TestRunAddPage(context); //Инициализуем страницу, на которую переходим
    }

}


