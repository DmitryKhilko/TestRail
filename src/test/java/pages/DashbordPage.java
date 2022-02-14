package pages;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class DashbordPage extends BasePage {
    public static final By HEADER_MENU_ITEM = By.xpath("//a[@id='navigation-dashboard");

    public DashbordPage(ITestContext context) {
        super(context);
    }
}
