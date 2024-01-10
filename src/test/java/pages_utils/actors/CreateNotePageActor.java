package pages_utils.actors;

import io.qameta.allure.Step;
import pages_utils.pages.CreateNotePage;

public interface CreateNotePageActor {

    @Step("Нажать кнопку сохранения заметки")
    default CreateNotePage clickCreateNoteButton(final CreateNotePage page) {
        page.getCreateNoteButton().click();
        return page;
    }

    @Step("Нажать кнопку OK у окна с ошибкой")
    default CreateNotePage clickErrorOkButton(final CreateNotePage page) {
        page.getErrorOkButton().click();
        return page;
    }

    @Step("Ввести в поле ввода заголовка текст {text}")
    default CreateNotePage sendToNoteTitleInput(final CreateNotePage page, final String text) {
        page.getNoteTitleInput().sendKeys(text);
        return page;
    }

    @Step("Ввести в поле ввода заголовка текст {text}")
    default CreateNotePage sendToNoteDescInput(final CreateNotePage page, final String text) {
        page.getNoteDescInput().sendKeys(text);
        return page;
    }
}
