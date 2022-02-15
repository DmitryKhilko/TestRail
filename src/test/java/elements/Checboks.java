package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Checboks {
    String locatorChecboks = "//label[contains(text(),'%s')]/span[@class='loginpage-checkmark']";
    String label; //переменная часть локатора (%s) - лэйбл чекбокса
    //Конструктор
    public Checboks(String label) {
        this.label = label;
    }

    //Создаем метод клика по чекбоксу
    public void click(String text) {
        $(By.xpath(String.format(locatorChecboks,this.label))).click(); //щелкаем по чекбоксу
    }
}
