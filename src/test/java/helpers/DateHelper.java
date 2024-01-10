package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс работы с датами.
 */
public class DateHelper {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String getCurrentFormattedDate() {
        return LocalDateTime.now().format(format);
    }
}
