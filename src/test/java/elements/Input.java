package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    String inputLocator = "//input[@id='%s']";
    String label; //переменная часть локатора (%s)

    public Input(String label) {
        this.label = label;
    }

    //Создаем метод записи в поле ввода какого-то значения с понятным для человека названием "write"
    public void write(String text) {
        $(By.xpath(String.format(inputLocator,this.label))).setValue(text);
    }
}
