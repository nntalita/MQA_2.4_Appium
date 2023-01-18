package ru.netology.qa;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreenUIAutomator;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class AppTest {

    private AppiumDriver driver;
    private String textToSet = "Netology";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testInstallEmptyString() {
        MainScreenUIAutomator mainScreenUIAutomator = new MainScreenUIAutomator(driver);
        mainScreenUIAutomator.userInput.isDisplayed();
        mainScreenUIAutomator.userInput.sendKeys(textToSet);
        mainScreenUIAutomator.buttonChange.isDisplayed();
        mainScreenUIAutomator.buttonChange.click();
        mainScreenUIAutomator.textToBeChanged.isDisplayed();
        Assertions.assertEquals(textToSet, mainScreenUIAutomator.textToBeChanged.getText());
        mainScreenUIAutomator.userInput.clear();
        mainScreenUIAutomator.buttonChange.click();
        Assertions.assertEquals(textToSet, mainScreenUIAutomator.textToBeChanged.getText());
    }

    @Test
    public void testInstallSpace() {
        MainScreenUIAutomator mainScreenUIAutomator = new MainScreenUIAutomator(driver);
        mainScreenUIAutomator.userInput.isDisplayed();
        mainScreenUIAutomator.userInput.sendKeys(textToSet);
        mainScreenUIAutomator.buttonChange.isDisplayed();
        mainScreenUIAutomator.buttonChange.click();
        mainScreenUIAutomator.textToBeChanged.isDisplayed();
        Assertions.assertEquals(textToSet, mainScreenUIAutomator.textToBeChanged.getText());
        mainScreenUIAutomator.userInput.sendKeys(textToSet);
        mainScreenUIAutomator.userInput.clear();
        mainScreenUIAutomator.userInput.sendKeys("   ");
        mainScreenUIAutomator.buttonChange.click();
        Assertions.assertEquals(textToSet, mainScreenUIAutomator.textToBeChanged.getText());
    }

    @Test
    public void testOpenTextInNewActivity() throws InterruptedException {
        MainScreenUIAutomator mainScreenUIAutomator = new MainScreenUIAutomator(driver);
        mainScreenUIAutomator.userInput.isDisplayed();
        mainScreenUIAutomator.userInput.sendKeys(textToSet);
        mainScreenUIAutomator.buttonActivity.isDisplayed();
        mainScreenUIAutomator.buttonActivity.click();
        Thread.sleep(3000);
        mainScreenUIAutomator.textInNewActivity.isDisplayed();
        Assertions.assertEquals(textToSet, mainScreenUIAutomator.textInNewActivity.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
