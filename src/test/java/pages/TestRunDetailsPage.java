package pages;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;

@Log4j2
public class TestRunDetailsPage extends BasePage{

    //Successfully added the new test case
    //String messageSuccessLocator = "//div[contains(@class, 'message-success')]"; //сообщение об успешной операции с сущностью (проектом, тест-кейсом)

    public TestRunDetailsPage(ITestContext context) {
        super(context);
    }

}
