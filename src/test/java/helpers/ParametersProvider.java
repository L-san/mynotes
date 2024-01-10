package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Класс по работе с параметрами.
 */
public class ParametersProvider {

    private static final String PATH_CONFIG = "./src/test/resources/config.properties";
    private static Properties properties = null;
    private static FileInputStream fileInputStream = null;
    private static ParametersProvider instance;

    /**
     * Чтение параметров из файла.
     */
    private ParametersProvider() {
        try {
            properties = new Properties();
            fileInputStream = new FileInputStream(PATH_CONFIG);
            properties.load(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.err.println("Файл свойств отсутствует! " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Создание нового объекта.
     */
    private static ParametersProvider getInstance() {
        if (instance == null) {
            instance = new ParametersProvider();
        }
        return instance;
    }

    /**
     * Получение значения параметра по его названию.
     *
     * @param name название параметра
     * @return возвращает значение параметра в формате строки
     */
    public static String getPropertyByName(final String name) {
        getInstance();
        String property = properties.getProperty(name);
        if (property == null) {
            System.err.println("Константа " + name + " отсутствует в файле и не была инициализирована.");
        }
        return property;
    }
}
