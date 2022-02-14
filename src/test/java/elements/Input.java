package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    String inputLocator = "//label[text()='%s']/ancestor::div[@class='login-inputx']/input"; //через названии лэйбла веб-элемента, находим сам инпут
    String label; //переменная часть локатора (%s) - подпись поля ввода, например, "Email"

    //Конструктор
    public Input(String label) {
        this.label = label;
    }

    //Создаем метод записи в поле ввода какого-то значения с понятным для человека названием "write"
    public void write(String text) {
        $(By.xpath(String.format(inputLocator,this.label))).clear(); //сначала очищаем поле
        $(By.xpath(String.format(inputLocator,this.label))).setValue(text); //потом вводим текст
    }
}
