package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static com.codeborne.selenide.Condition.exactText;
import static pages.AdminProjectsPage.ADMIN_PROJECT_PAGE_URL;
import static pages.LoginPage.LOGIN_PAGE_URL;

@Log4j2
//Удаляем проекты заглушки
public class PostconditionTest extends BaseTest {

    @Description("Произвести удаление проекта. При успешном удалении проекта будет выведено сообщение 'Successfully deleted the project.'")
    //описание теста
    @Test(priority = 11, description = "Удалить проект-заглушку")//название теста
    public void deleteProject(ITestContext context) {
        loginPage
                .openPage(LOGIN_PAGE_URL)//открываем страницу логина
                .login(email, password); //email и password - переменные, берущие значения из файла config.properties
        adminProjectsPage
                .openPage(ADMIN_PROJECT_PAGE_URL) //открываем сразу нужную страницу, чтобы избежать нажатий на кнопки (это проверялось раньше)
                .deleteProject("Проект-заглушка") //удаляем проект
                .projectActionResultMessage().shouldHave(exactText("Successfully deleted the project.")); //проверяем - при спешном удалении выводится эта надпись
    }
}