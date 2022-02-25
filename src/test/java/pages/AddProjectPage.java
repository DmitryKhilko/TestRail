package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class AddProjectPage extends BasePage{

    String nameLocator = "//input[@id='name']";
    String announcementLocator = "//textarea[@id='announcement']";
    String showAnnouncementLocator = "//input[@id='show_announcement']";
    String suiteModeLocator = "//input[@name='suite_mode']"; // группа элементов Suite Mode
    String addProjectButtonLocator = "//button[@id='accept']";

    public AddProjectPage(ITestContext context) {
        super(context);
    }

    //В соответствии с паттерном "Page Element/Wrappers" создаются проект
    @Step("Создать проект")
    public ProjectPage createNewProject(String projectName, String announcement) {
        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Name имя нового проекта '" + projectName + "'");
        $(By.xpath(nameLocator)).clear(); //сначала очищаем поле
        $(By.xpath(nameLocator)).setValue(projectName); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Announcement значение '" + announcement + "'");
        $(By.xpath(announcementLocator)).clear(); //сначала очищаем поле
        $(By.xpath(announcementLocator)).setValue(announcement); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": установить флажок, отображающий текст из поля Announcement на странице ProjectPage");
        $(By.xpath(showAnnouncementLocator)).click(); //щелкаем по чекбоксу (устанавливаем флажок)

        log.debug("Тест " + context.getAttribute("testName") + ": выбираем нужный Suite Mode");
        $$(By.xpath(suiteModeLocator)).get(1).click(); //выбираем первую радиокнопку

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Log In' для входа в приложение");
        $(By.xpath(addProjectButtonLocator)).click(); //нажимаем на кнопку Add Project

        return new ProjectPage(context); //Инициализуем страницу, на которую переходим
    }
}
