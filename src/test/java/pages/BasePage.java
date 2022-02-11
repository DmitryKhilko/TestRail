package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    public static final String BASE_URL = "https://hdn.testrail.io/index.php?"; //Вынесли по причине того, что адрес может изменится и изменим только в одном месте. Также это важно, чтобы быстро переключаться на разные окружения (test, prod и т.п.)

    public abstract boolean isOpenPage(); //метод для определения, загрузилась ли страница по какому-то признаку (паттерн Loadable Page/Component)

    protected boolean isExist(By locator) {
        try {
            return $(locator).isDisplayed();
        } catch (NoSuchElementException ex) {
            System.out.println(">>> !!! " + ex.getMessage());
            return false;
        }
    }
}
