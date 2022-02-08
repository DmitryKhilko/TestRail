import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @Test
    public void login(){
        Configuration.browser="firefox";
        open("https://hdn.testrail.io");
        $(byXpath("//input[@name='name']")).sendKeys("hdn_tms@mail.ru");
        $(byXpath("//input[@name='password']")).sendKeys("pVui0CaU1AsUDIXrPMws");
        $(byXpath("//span[@class='loginpage-checkmark']")).click();
        $(byXpath("//button[@id='button_primary']")).click();
        $(byXpath("//span[contains(text(),'Dima Hilko')]")).click();
        $(byXpath("//a[@id='navigation-user-logout']")).click();
    }
}
