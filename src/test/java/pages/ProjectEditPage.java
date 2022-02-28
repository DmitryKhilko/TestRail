package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class ProjectEditPage extends BasePage{

    public static final String EDIT_PROJECT_PAGE_TITLE = "Edit Project";
    //String addProgectButtonLocator = "//a[@id='sidebar-projects-add']"; // кнопка Add Project на странице Dashbord (на слайдбаре)
    String iconEditLocator = "//table[@class='grid']//a[contains(text(),'%s')]/ancestor::tr//div[@class='icon-small-edit']"; // карандаш для перехода к редактированию проекта на странице ProjectPage
    String nameLocator = "//input[@id='name']";
    String announcementLocator = "//textarea[@id='announcement']";
    String showAnnouncementLocator = "//input[@id='show_announcement']";
    String suiteModeLocator = "//input[@name='suite_mode']"; // группа элементов Suite Mode
    String saveProjectButtonLocator = "//button[@id='accept']";

    public ProjectEditPage(ITestContext context) {
        super(context);
    }

    //В соответствии с паттерном "Page Element/Wrappers" изменяется проект
    @Step("Изменить проект")
    public AdminProjectsPage updateProject(String oldProjectName, String newProjectName, String newAnnouncement, int newSuiteModeNumber) {
        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Карандаш' для перехода к редактированию проекта");
        $(By.xpath(String.format(iconEditLocator, oldProjectName))).click();

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Name новое имя проекта");
        $(By.xpath(nameLocator)).clear(); //сначала очищаем поле
        $(By.xpath(nameLocator)).setValue(newProjectName); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": ввести в поле ввода Announcement новое значение");
        $(By.xpath(announcementLocator)).clear(); //сначала очищаем поле
        $(By.xpath(announcementLocator)).setValue(newAnnouncement); //потом вводим текст

        log.debug("Тест " + context.getAttribute("testName") + ": снять флажок, отображающий текст из поля Announcement на странице ProjectPage");
        $(By.xpath(showAnnouncementLocator)).click(); //щелкаем по чекбоксу (снимаем флажок)

        log.debug("Тест " + context.getAttribute("testName") + ": выбираем нужный Suite Mode");
        $$(By.xpath(suiteModeLocator)).get(newSuiteModeNumber).click(); //выбираем первую радиокнопку

        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Save Project' для сохранения изменений в проекте");
        $(By.xpath(saveProjectButtonLocator)).click(); //нажимаем на кнопку Add Project

        return new AdminProjectsPage(context); //Инициализуем страницу, на которую переходим
    }
}
