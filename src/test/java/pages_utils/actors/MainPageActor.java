package pages_utils.actors;

import dto.NoteDto;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages_utils.pages.CreateNotePage;
import pages_utils.pages.MainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface MainPageActor {

    @Step("Ввести в поле поиска {text}")
    default MainPage sendSearchFieldInput(final MainPage page, final String text) {
        page.getSearchFieldText().click();
        page.getSearchFieldText().sendKeys(text);
        page.getDriver().hideKeyboard();
        return page;
    }

    @Step("Нажать кнопку отмены поиска")
    default MainPage clickSearchCloseButton(final MainPage page) {
        page.getSearchCloseButton().click();
        page.waitUntilInvisibilityOf(page.getSearchCloseButton());
        return page;
    }

    @Step("Нажать кнопку добавления заметки основной страницы")
    default CreateNotePage clickCreateNoteButton(final MainPage page) {
        page.getCreateNoteButton().click();
        return new CreateNotePage(page.getDriver());
    }

    default List<NoteDto> getNotes(final MainPage page) {
        List<NoteDto> notes = new ArrayList<>();
        List<String> titles = page.getDriver().findElements(page.getNoteTitleSelector())
            .stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> descriptions = page.getDriver().findElements(page.getNoteDescSelector())
            .stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> dates = page.getDriver().findElements(page.getNoteDateSelector())
            .stream().map(WebElement::getText).collect(Collectors.toList());

        for (int i = 0; i < titles.size(); i++) {
            notes.add(NoteDto.builder()
                .title(titles.get(i))
                .desc(descriptions.get(i))
                .date(dates.get(i))
                .build());
        }
        return notes;
    }
}
