package dto;

import lombok.Builder;
import lombok.Data;

/**
 * Класс заметки.
 */
@Data
@Builder
public class NoteDto {

    private String title;
    private String desc;
    private String date;
}
