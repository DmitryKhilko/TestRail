package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Button {
    String buttonLocator = "//span[contains(text(),'%s')]|//a[contains(text(),'%s')]"; //на разных страницах у разных кнопок разные xpath. Чтобы создать единый элемент, объединяем xpath нескольких кнопок ("|" - вертикальная черта)
    String buttonLocatorName1; //переменная часть локатора (%s) - название кнопки для первого xpath
    String buttonLocatorName2; //переменная часть локатора (%s) - название кнопки для второго xpath

    //Конструктор
    public Button(String label) {
        this.buttonLocatorName1 = label;
        this.buttonLocatorName2 = label;
    }

    //Создаем метод клика по кнопке
    public void click(String text) {
        $(By.xpath(String.format(buttonLocator,this.buttonLocatorName1,this.buttonLocatorName2))).click(); //нажимаем кнопки
    }
}
