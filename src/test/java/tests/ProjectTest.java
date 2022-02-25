package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;

@Log4j2
//Работа с Projects (CRUD)
public class ProjectTest extends BaseTest {

    @Description("после входа на страницу наличие заголовка страницы - 'Add Project'") //описание теста
    @Test(priority = 1, description = "Создать проект (валидные значения)")//название теста
    public void addProjectValidValue(ITestContext context) {
        loginPage
                .openPage("/auth/login/")
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        dashbordPage.addProgect(); //создаем пустой проект
        log.debug("Тест " + context.getAttribute("testName") + ": проверить, открылась ли страница 'Add Project'");
        addProjectPage.pageTitle().shouldHave(exactText("Add Project"));
        addProjectPage.createNewProject("Проект 1", "Ссылка на базу знаний...");
        projectPage.projectAnnouncement().shouldHave(exactText("Ссылка на базу знаний...")); //на открывшейся странице текст пункта меню должен иметь точный текст "Dima Hilko" (переменная берущая значение из файла config.properties)


    }

}