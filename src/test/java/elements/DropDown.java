package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DropDown {

    String label;

    String dropdownLocator = "//label[contains(text(), '%s')]/ancestor::td//div[contains(@class, 'chzn-container')]";
    String optionLocator = "//div[contains(@class, 'chzn-container-active')]//ul/li[contains(text(), '%s')]";

    public DropDown(String label) {
        this.label = label;
    }

    public void selectOption(String option) {
        log.debug(String.format("Выбрать пункт '%s' из раскрывающегося списка '%s'", option, this.label));
        $(By.xpath(String.format(dropdownLocator, this.label))).click();
        $(By.xpath(String.format(optionLocator, option))).click();
    }
}
