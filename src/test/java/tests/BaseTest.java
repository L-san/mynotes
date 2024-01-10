package tests;

import data.BaseConstants;
import helpers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import lombok.Getter;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages_utils.pages.MainPage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.Duration;

/**
 * Базовый класс теста.
 */
@Getter
public abstract class BaseTest {

    /**
     * Android драйвер.
     */
    private AndroidDriver driver;

    /**
     * Метод подготовки драйвера для тестов.
     *
     * @throws IOException когда файл конфигурации недоступен.
     */
    @Step("Подготовка драйвера для теста")
    @BeforeTest(alwaysRun = true)
    protected void setUpDriver() throws IOException {
        this.driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseConstants.IMPLICIT_TIMEOUT));
    }

    /**
     * Метод подготовки данных и драйвера для тестов.
     */
    @Step("Подготовка окружения для теста")
    @BeforeMethod(alwaysRun = true)
    protected void setUp() throws IOException {
        setUpDriver();
        if (getDriver() == null) {
            System.err.println(BaseConstants.DRIVER_NOT_CREATED_ERROR);
        }
        installApp();
        openApp();
    }

    /**
     * Метод удаления приложения с устройства.
     */
    @Step("Удаление приложения")
    @AfterMethod(alwaysRun = true)
    public final void deleteApp() {
        if (driver != null) {
            driver.terminateApp(BaseConstants.BUNDLE_ID);
            driver.removeApp(BaseConstants.BUNDLE_ID);
        } else
            System.err.println(BaseConstants.DRIVER_NOT_EXIST_ERROR);
    }

    /**
     * Метод удаления используемого драйвера.
     */
    @Step("Удаление драйвера")
    @AfterTest(alwaysRun = true)
    public final void deleteDriver(ITestContext context) {
        if (this.driver != null) {
            this.driver.quit();
        } else {
            System.err.println(BaseConstants.DRIVER_NOT_EXIST_ERROR);
        }
    }

    /**
     * Метод установки приложения.
     */
    protected final void installApp() {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        driver.installApp(
            System.getProperty("user.dir")
                + fileSeparator
                + BaseConstants.APP_NAME
        );
    }

    /**
     * Метод запуска установленного приложения.
     */
    protected final void openApp() {
        driver.activateApp(BaseConstants.BUNDLE_ID);
    }

    /**
     * Метод открытия главного экрана приложения.
     *
     * @return MainPage
     */
    protected MainPage openMainNotesPage() {
        return new MainPage(driver);
    }
}
