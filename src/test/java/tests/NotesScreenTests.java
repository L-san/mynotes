package tests;

import data.CreateNotePageConstants;
import data.MainPageConstants;
import data.NoteDataProvider;
import dto.NoteDto;
import helpers.DateHelper;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import pages_utils.checkers.MainPageChecker;
import pages_utils.pages.CreateNotePage;
import pages_utils.pages.MainPage;

import java.util.List;

/**
 * Класс с тестами приложения
 */
@Epic("My Notes Tests")
public class NotesScreenTests extends BaseTest implements MainPageConstants, CreateNotePageConstants {

    @Test(description = "Mob-1: Установка и открытие приложения")
    public void openAppTest() {
        MainPage page = openMainNotesPage();
        page.checkPage(checker ->
                checker.checkHeaderElements(HEADER_ELEMENTS_TEXTS))
            .checkPage(MainPageChecker::checkCreateNoteButton);
    }

    @Test(description = "Mob-2: Создание новой заметки", dataProvider = "notesTitlesAndDescriptions",
        dataProviderClass = NoteDataProvider.class)
    public void createNoteTest(final String title, final String description) {
        MainPage page = openMainNotesPage();
        CreateNotePage createNotePage = page.clickCreateNoteButton(page);
        createNotePage.checkPage(checker ->
                checker.checkPageElements(NOTES_TITLE_PLACEHOLDER, ENTER_NOTES_PLACEHOLDER))
            .clickCreateNoteButton(createNotePage)
            .checkPage(checker ->
                checker.checkErrorMessageIsPresentWithText(TITLE_REQUIRED_MESSAGE))
            .clickErrorOkButton(createNotePage)
            .sendToNoteTitleInput(createNotePage, title)
            .clickCreateNoteButton(createNotePage)
            .checkPage(checker ->
                checker.checkErrorMessageIsPresentWithText(NOTES_DESCRIPTION_REQUIRED_MESSAGE))
            .clickErrorOkButton(createNotePage)
            .sendToNoteDescInput(createNotePage, description)
            .clickCreateNoteButton(createNotePage);

        page.checkPage(checker ->
            checker.checkLastNote(title, description, DateHelper.getCurrentFormattedDate()));
    }

    @Test(description = "Mob-3: Поле поиска")
    public void searchTest() {
        MainPage page = openMainNotesPage();
        List<NoteDto> notes;
        if (page.getNotes(page).isEmpty()) {
            notes = page.addRandomNotesToApp(page, 3);
        } else {
            notes = page.getNotes(page);
        }
        page.sendSearchFieldInput(page, notes.get(0).getTitle())
            .checkPage(checker ->
                checker.checkLastNote(
                    notes.get(0).getTitle(),
                    notes.get(0).getDesc(),
                    notes.get(0).getDate()))
            .clickSearchCloseButton(page)
            .checkPage(checker ->
                checker.checkAllNotes(notes));
    }
}
