package pages;

import org.testng.ITestContext;
import utils.PropertyReader;

public abstract class BasePage {

    public static final String BASE_URL = System.getenv().getOrDefault("TESTRAIL_BASEURL", PropertyReader.getProperty("testrail.baseurl")); //команда, берущая значение для переменной или с настроек CI (TESTRAIL_BASEURL) или из настройки testrail.authorization файла config.baseurl (вместо команды public static final String BASE_URL = "https://hdn.testrail.io/index.php?"; - dынесли по причине того, что адрес может изменится и изменим только в одном месте. Также это важно, чтобы быстро переключаться на разные окружения (test, prod и т.п.)
    ITestContext context;

    public BasePage(ITestContext context) {
        this.context = context;
    }









}
