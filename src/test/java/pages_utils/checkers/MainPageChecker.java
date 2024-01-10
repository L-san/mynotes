package pages_utils.checkers;

import dto.NoteDto;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages_utils.pages.MainPage;

import java.util.Collections;
import java.util.List;

/**
 * Класс методов по работе с главной страницей.
 */
public class MainPageChecker extends BaseChecker<MainPage> {

    public MainPageChecker(final MainPage page) {
        super(page);
    }

    public List<WebElement> getHeaderElements() {
        return List.of(page.getNotesLabel(), page.getTasksLabel(), page.getSearchFieldText());
    }

    @Step("Проверить кнопку добавления заметки основной страницы")
    public MainPage checkCreateNoteButton() {
        checkElementVisible(page.getCreateNoteButton());
        return page;
    }

    @Step("Проверить элементы хедера основной страницы")
    public MainPage checkHeaderElements(final String... texts) {
        List<WebElement> headerElements = getHeaderElements();
        Assert.assertEquals(texts.length, headerElements.size(),
            "Длина массива с текстами должна быть равна числу элементов хедера");
        for (int i = 0; i < headerElements.size(); i++) {
            checkElementVisibleAndHasText(headerElements.get(i), texts[i]);
        }
        return page;
    }

    @Step("Проверить последнюю заметку")
    public MainPage checkLastNote(final String noteTitle, final String noteDesc, final String noteDate) {
        List<NoteDto> notes = page.getNotes(page);
        NoteDto note = notes.get(notes.size() - 1);
        Assert.assertEquals(note.getTitle(), noteTitle, "Заголовок заметки должен быть равен " + noteTitle);
        Assert.assertEquals(note.getDesc(), noteDesc, "Текст заметки должен быть равен " + noteDesc);
        Assert.assertEquals(note.getDate(), noteDate, "Дата заметки должна быть равна " + noteDate);
        return page;
    }

    @Step("Проверить все заметки")
    public MainPage checkAllNotes(final List<NoteDto> notes) {
        Collections.reverse(notes);
        List<NoteDto> allNotes = page.getNotes(page);
        for (NoteDto note : notes) {
            int noteIndex = allNotes.indexOf(note);
            Assert.assertTrue(noteIndex > -1, "Заметка не обнаружена в списке");
            Assert.assertEquals(allNotes.get(noteIndex).getTitle(),
                note.getTitle(),
                "Заголовок заметки должен быть равен " + note.getTitle());
            Assert.assertEquals(allNotes.get(noteIndex).getDesc(),
                note.getDesc(),
                "Текст заметки должен быть равен " + note.getDesc());
            Assert.assertEquals(allNotes.get(noteIndex).getDate(),
                note.getDate(),
                "Дата заметки должна быть равна " + note.getDate());
        }
        return page;
    }
}
