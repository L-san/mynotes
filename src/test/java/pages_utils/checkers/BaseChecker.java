package pages_utils.checkers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages_utils.pages.BasePage;

/**
 * Класс методов работы с элементами страниц.
 */
public abstract class BaseChecker<PAGE extends BasePage> {

    public PAGE page;

    public BaseChecker(PAGE page){
        this.page = page;
    }

    @Step("Проверить видимость элемента")
    void checkElementVisible(final WebElement element) {
        Assert.assertTrue(element.isDisplayed(),
            "Элемент должен быть виден.");
    }

    @Step("Проверить текст {text} у элемента")
    void checkElementHasText(final WebElement element, final String text) {
        Assert.assertEquals(element.getText(), text,
            "Элемент должен иметь текст: " + text);
    }

    @Step("Проверить текст {text} и видимость элемента")
    void checkElementVisibleAndHasText(final WebElement element, final String text) {
        checkElementVisible(element);
        checkElementHasText(element, text);
    }
}
