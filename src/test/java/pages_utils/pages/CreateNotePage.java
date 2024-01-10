package pages_utils.pages;

import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages_utils.actors.CreateNotePageActor;
import pages_utils.checkers.CreateNotePageChecker;

/**
 * Класс страницы создания заметок.
 */
@Getter
public class CreateNotePage extends BasePage<CreateNotePage, CreateNotePageChecker> implements CreateNotePageActor {

    /**
     * Кнопка возвращения на главную страницу.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/imgBack")
    private WebElement returnButton;

    /**
     * Кнопка подтверждения создания заметки.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/imgDone")
    private WebElement createNoteButton;

    /**
     * Заголовок текущей даты.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/tvDateTime")
    private WebElement dateLabel;

    /**
     * Поле ввода заголовка заметки.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/etNoteTitle")
    private WebElement noteTitleInput;

    /**
     * Поле ввода описания заметки.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/etNoteDesc")
    private WebElement noteDescInput;

    /**
     * Текст сообщения об ошибки.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/snackbar_text")
    private WebElement errorMessage;

    /**
     * Кнопка ОК алерта с ошибкой.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/snackbar_action")
    private WebElement errorOkButton;

    /**
     * Конструктор.
     *
     * @param driver driver
     */
    public CreateNotePage(final AndroidDriver driver) {
        super(driver);
        setChecker(new CreateNotePageChecker(this));
    }
}
