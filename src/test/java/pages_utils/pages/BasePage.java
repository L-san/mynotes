package pages_utils.pages;

import data.BaseConstants;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages_utils.checkers.BaseChecker;

import java.time.Duration;
import java.util.function.Consumer;

@Getter
public abstract class BasePage<PAGE extends BasePage, CHECKER extends BaseChecker> implements BaseConstants {

    /**
     * Чекер.
     */
    @Setter
    private CHECKER checker;

    /**
     * Android драйвер.
     */
    private final AndroidDriver driver;

    /**
     * Разрешение экрана.
     */
    private final Dimension window;

    /**
     * Ожидание.
     */
    private final WebDriverWait wait;

    /**
     * Конструктор класса.
     *
     * @param driver драйвер
     */
    public BasePage(final AndroidDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.window = driver.manage().window().getSize();
    }

    /**
     * Метод ожидания невидимости элемента.
     */
    public void waitUntilInvisibilityOf(final WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Вызов блока проверок для страницы. В качестве класса с проверками используется проинициализированный
     * по умолчанию класс в Page
     *
     * @param consumers - содержит методы проверок, которые нужно вызвать
     * @return текущая страница
     */
    @SafeVarargs
    public final PAGE checkPage(Consumer<CHECKER>... consumers) {
        CHECKER checker = getChecker();
        for (Consumer<CHECKER> consumer : consumers) {
            consumer.accept(checker);
        }
        return (PAGE) this;
    }

    protected CHECKER getChecker() {
        return checker;
    }
}
