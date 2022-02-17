package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    String inputLocator = "//label[text()='%s']/ancestor::div[@class='login-inputx']/input"; //через названии лэйбла веб-элемента, находим сам инпут
    String inputLabel; //переменная часть локатора (%s) - подпись поля ввода, например, "Email"

    //Конструктор
    public Input(String inputLabel) {
        this.inputLabel = inputLabel;
    }

    //Создаем метод записи в поле ввода какого-то значения с понятным для человека названием "write"
    public void write(String text) {
        $(By.xpath(String.format(inputLocator,this.inputLabel))).clear(); //сначала очищаем поле
        $(By.xpath(String.format(inputLocator,this.inputLabel))).setValue(text); //потом вводим текст
    }
}