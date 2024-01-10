package pages_utils.pages;

import data.NoteFactory;
import dto.NoteDto;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages_utils.actors.MainPageActor;
import pages_utils.checkers.MainPageChecker;

import java.util.List;

/**
 * Класс основной страницы приложения.
 */
@Getter
public class MainPage extends BasePage<MainPage, MainPageChecker> implements MainPageActor {

    /**
     * Заголовок 'Notes'.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/textView3")
    private WebElement notesLabel;

    /**
     * Заголовок 'Tasks'.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/textView4")
    private WebElement tasksLabel;

    /**
     * Поле поиска.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/search_src_text")
    private WebElement searchFieldText;

    /**
     * Кнопка очистки поля поиска.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/search_close_btn")
    private WebElement searchCloseButton;

    /**
     * Кнопка создания заметки.
     */
    @FindBy(id = "com.akshatbhuhagal.mynotes:id/fabCreateNoteBtn")
    private WebElement createNoteButton;

    /**
     * Селектор заголовка заметки.
     */
    private final By noteTitleSelector = By.id("com.akshatbhuhagal.mynotes:id/tvTitle");

    /**
     * Селектор описания заметки.
     */
    private final By noteDescSelector = By.id("com.akshatbhuhagal.mynotes:id/tvDesc");

    /**
     * Селектор даты создания заметки.
     */
    private final By noteDateSelector = By.id("com.akshatbhuhagal.mynotes:id/tvDateTime");

    /**
     * Конструктор.
     *
     * @param driver driver
     */
    public MainPage(final AndroidDriver driver) {
        super(driver);
        setChecker(new MainPageChecker(this));
    }

    @Step("Добавить в приложение {count} случайных заметок")
    public List<NoteDto> addRandomNotesToApp(final MainPage page, int count) {
        List<NoteDto> notes = NoteFactory.getNotes(count);
        for (int i = 0; i < count; i++) {
            CreateNotePage createNotePage = clickCreateNoteButton(page);
            createNotePage.sendToNoteTitleInput(createNotePage, notes.get(i).getTitle())
                .sendToNoteDescInput(createNotePage, notes.get(i).getDesc())
                .clickCreateNoteButton(createNotePage);
        }
        return notes;
    }
}
