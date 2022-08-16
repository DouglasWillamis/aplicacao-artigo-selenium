package saucedemo.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static saucedemo.helpers.Constants.CHROME_DRIVER_PATH;

public class DriverManager {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            System.setProperty("webdriver.chrome.silentOutput", "true");
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            driver = new ChromeDriver();
        }

        return driver;
    }

    public static WebDriverWait getDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return wait;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}
