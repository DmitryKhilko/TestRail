package pages;

public abstract class BasePage {
    public static final String BASE_URL = "https://hdn.testrail.io"; //Вынесли по причине того, что адрес может изменится и изменим только в одном месте. Также это важно, чтобы быстро переключаться на разные окружения (test, prod и т.п.)

}
