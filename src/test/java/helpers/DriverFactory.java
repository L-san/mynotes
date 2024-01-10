package helpers;

import data.BaseConstants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

/**
 * Фабрика драйверов.
 */
public class DriverFactory {

    private DriverFactory() {}

    /**
     * Метод создания драйвера.
     *
     * @throws IOException когда файл конфигурации недоступен
     */
    public static AndroidDriver createDriver() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", BaseConstants.DEVICE_NAME);
        capabilities.setCapability("platformName", BaseConstants.PLATFORM_NAME);
        capabilities.setCapability("platformVersion", BaseConstants.PLATFORM_VERSION);

        capabilities.setCapability("noSign", true);
        capabilities.setCapability("newCommandTimeout", BaseConstants.NEW_COMMAND_TIMEOUT);
        capabilities.setCapability("automationName", BaseConstants.AUTOMATION_NAME);
        capabilities.setCapability("appium:appWaitForLaunch", false);
        capabilities.setCapability("appium:adbExecTimeout", BaseConstants.ADB_EXEC_TIMEOUT);
        capabilities.setCapability("noReset", "false");

        return new AndroidDriver(new URL(BaseConstants.APPIUM_URL), capabilities);
    }
}
