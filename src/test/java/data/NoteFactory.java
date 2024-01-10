package data;

import dto.NoteDto;
import helpers.DateHelper;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Фабрика заметок.
 */
public class NoteFactory {

    public static NoteDto getNote() {
        return NoteDto.builder()
            .title(RandomStringUtils.randomAlphabetic(6))
            .desc(RandomStringUtils.randomAlphabetic(8))
            .date(DateHelper.getCurrentFormattedDate())
            .build();
    }

    public static List<NoteDto> getNotes(int size) {
        List<NoteDto> notes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            notes.add(getNote());
        }
        return notes;
    }
}
