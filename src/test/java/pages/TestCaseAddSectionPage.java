package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCaseAddSectionPage extends BasePage{

    String projectTabSuitesLocator = "//a[@id='navigation-suites']"; //вкладка проекта "Test Cases"
    String addSectionButtonLocator = "//a[@id='addSectionInline']"; //кнопка "Add Section"
    String nameSectionDialogLocator = "//div[@id='dialog-ident-editSectionDialog']//input[@id='editSectionName']"; //поле ввода Name диалогового окна 'Add Section'
    //String descriptionSectionDialogLocator = "//div[@id='dialog-ident-editSectionDialog']//div[contains(@class,'textarea')]"; //поле ввода Description диалогового окна 'Add Section'
    String descriptionSectionDialogLocator = "//div[@id= 'editSectionDescription_display']"; //поле ввода Description диалогового окна 'Add Section'
    String addSectionButtonDialogLocator = "//div[@id='dialog-ident-editSectionDialog']//button[@id='editSectionSubmit']"; //кнопка 'Add Section' диалогового окна 'Add Section'

    public TestCaseAddSectionPage(ITestContext context) {
        super(context);
    }

    @Step("Создать новый раздел")
    public TestCasePage createNewSection(String sectionName, String description) {
        log.debug("Тест " + context.getAttribute("testName") + ": перейти на вкладку 'Test cases' проекта для добавления нового раздела");
        $(By.xpath(projectTabSuitesLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку '+Add Section' для перехода к созданию нового раздела");
        $(By.xpath(addSectionButtonLocator)).click();

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Name имя нового раздела '" + sectionName + "'");
        $(By.xpath(nameSectionDialogLocator)).clear(); //сначала очищаем поле
        $(By.xpath(nameSectionDialogLocator)).setValue(sectionName); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Description значение '" + description + "'");
        $(By.xpath(descriptionSectionDialogLocator)).clear(); //сначала очищаем поле
        //div[@contenteditable]
        $(By.xpath(descriptionSectionDialogLocator)).setValue(description); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Add Section' для создания раздела");
        $(By.xpath(addSectionButtonDialogLocator)).click(); //наводим курсор на название раздела

        return new TestCasePage(context); //Инициализуем страницу, на которую переходим
    }
}


