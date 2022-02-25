package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DashbordPage extends BasePage {

    String addProgectButtonLocator = "//a[@id='sidebar-projects-add']"; // кнопка Add Project на странице Dashbord (на слайдбаре)


    public DashbordPage(ITestContext context) {
        super(context);
    }

    @Step("Нажать кнопку '+Add Project' на странице Dashbord")
    public AddProjectPage addProgect() {
        log.debug("Тест " + context.getAttribute("testName") + ": нажать кнопку 'Log In' для входа в приложение");
        $(By.xpath(addProgectButtonLocator)).click();
        return new AddProjectPage(context); //Инициализуем страницу, на которую переходим
    }
}


