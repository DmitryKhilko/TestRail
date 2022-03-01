package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Section;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCaseAddSectionPage extends BasePage{

    String titleDialogLocator = "//span[@class='ui-dialog-title']"; // заголовок диалогового окна создания раздела
    String nameSectionDialogLocator = "//div[@id='dialog-ident-editSectionDialog']//input[@id='editSectionName']"; //поле ввода Name диалогового окна 'Add Section'
    String descriptionSectionDialogLocator = "//div[@id= 'editSectionDescription_display']"; //поле ввода Description диалогового окна 'Add Section'
    String addSectionButtonDialogLocator = "//div[@id='dialog-ident-editSectionDialog']//button[@id='editSectionSubmit']"; //кнопка 'Add Section' диалогового окна 'Add Section'

    public TestCaseAddSectionPage(ITestContext context) {
        super(context);
    }

    //Метод, возвращающий веб-элемент - заголовок страницы (проверка, что зашли в диалог)
    public SelenideElement dialogTitle() {
        log.debug("Тест " + context.getAttribute("testName") + ": возвратить элемент - заголовок диалогового окна");
        return $(By.xpath(titleDialogLocator));
    }

    @Step("Создать новый раздел")
    public TestCasePage createNewSection(Section section) {

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Name имя нового раздела '" + section.getName() + "'");
        $(By.xpath(nameSectionDialogLocator)).clear(); //сначала очищаем поле
        $(By.xpath(nameSectionDialogLocator)).setValue(section.getName()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Description значение '" + section.getDescription() + "'");
        $(By.xpath(descriptionSectionDialogLocator)).clear(); //сначала очищаем поле
        $(By.xpath(descriptionSectionDialogLocator)).setValue(section.getDescription()); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Add Section' для создания раздела");
        $(By.xpath(addSectionButtonDialogLocator)).click(); //наводим курсор на название раздела

        return new TestCasePage(context); //Инициализуем страницу, на которую переходим
    }

    //span[@class='ui-dialog-title']
}


