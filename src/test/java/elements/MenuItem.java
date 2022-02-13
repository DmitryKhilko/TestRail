package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MenuItem {
    String menuItemLocator = "//a[contains(@class,'dropdown')][contains(text(),'%s')]";
    String label; //переменная часть локатора (%s) - название пункта меню

    public MenuItem(String label) {
        this.label = label;
    }

    //Создаем метод выбора пункта меню
    public void select() {
        $(By.xpath(String.format(menuItemLocator, this.label))).click();
    }
}
