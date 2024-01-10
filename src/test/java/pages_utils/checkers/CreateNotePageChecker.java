package pages_utils.checkers;

import helpers.DateHelper;
import io.qameta.allure.Step;
import pages_utils.pages.CreateNotePage;

/**
 * Класс методов по работе со страницей по созданию заметок.
 */
public class CreateNotePageChecker extends BaseChecker<CreateNotePage> {

    public CreateNotePageChecker(final CreateNotePage page) {
        super(page);
    }

    @Step("Проверить элементы страницы")
    public CreateNotePageChecker checkPageElements(final String notesTitlePlaceholder, final String notesDescPlaceholder) {
        checkElementVisible(page.getReturnButton());
        checkElementVisible(page.getCreateNoteButton());
        checkElementVisibleAndHasText(page.getDateLabel(), DateHelper.getCurrentFormattedDate());
        checkElementVisibleAndHasText(page.getNoteTitleInput(), notesTitlePlaceholder);
        checkElementVisibleAndHasText(page.getNoteDescInput(), notesDescPlaceholder);
        return this;
    }

    @Step("Проверить, что всплыло сообщение об ошибке с текстом {text}")
    public CreateNotePageChecker checkErrorMessageIsPresentWithText(final String text) {
        checkElementVisibleAndHasText(page.getErrorMessage(), text);
        checkElementVisible(page.getErrorOkButton());
        return this;
    }
}
