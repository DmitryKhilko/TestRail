package elements;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MenuItem {
    //String menuItemLocator = "//a[contains(@class,'dropdown')][contains(text(),'%s')]";
    String menuItemLocator = "//a[@title = '%s']|//a[contains(@class,'dropdown')][contains(text(),'%s')]"; ////a[@title = 'Dima Hilko']|//a[contains(@class,'dropdown')][contains(text(),'My Setting')]
    String menuItemName1; //переменная часть локатора (%s) - название пункта меню
    String menuItemName2; //переменная часть локатора (%s) - название пункта меню

    public MenuItem(String label) {
        this.menuItemName1 = label;
        this.menuItemName2 = label;
    }

    //Создаем метод выбора пункта меню
    public void select() {
        $(By.xpath(String.format(menuItemLocator, this.menuItemName1, this.menuItemName2))).click();
    }
}
