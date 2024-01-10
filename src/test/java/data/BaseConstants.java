package data;

import helpers.ParametersProvider;

public interface BaseConstants {

    String DEVICE_NAME = ParametersProvider.getPropertyByName("deviceName");
    String PLATFORM_NAME = ParametersProvider.getPropertyByName("platformName");
    String PLATFORM_VERSION = ParametersProvider.getPropertyByName("platformVersion");
    String NEW_COMMAND_TIMEOUT = ParametersProvider.getPropertyByName("newCommandTimeout");
    String AUTOMATION_NAME = ParametersProvider.getPropertyByName("automationName");
    String ADB_EXEC_TIMEOUT = ParametersProvider.getPropertyByName("adbExecTimeout");
    String APPIUM_URL = ParametersProvider.getPropertyByName("appiumUrl");
    int IMPLICIT_TIMEOUT = 10;
    String BUNDLE_ID = ParametersProvider.getPropertyByName("bundleId");
    String APP_NAME = ParametersProvider.getPropertyByName("app");
    String DRIVER_NOT_EXIST_ERROR = "Driver not exist";
    String DRIVER_NOT_CREATED_ERROR = "Driver not created";
}
