package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Checboks {
    String locatorChecboks = "//label[contains(text(),'%s')]/span[@class='loginpage-checkmark']";
    String checkboksLabel; //переменная часть локатора (%s) - лэйбл чекбокса
    //Конструктор
    public Checboks(String checkboksLabel) {
        this.checkboksLabel = checkboksLabel;
    }

    //Создаем метод клика по чекбоксу
    public void click(String text) {
        $(By.xpath(String.format(locatorChecboks,this.checkboksLabel))).click(); //щелкаем по чекбоксу
    }
}
