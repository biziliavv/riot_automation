package setup;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.awt.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Selenium driver wrapper
 * <p>
 * Created by vitaliybizilia on 5/17/17.
 */
public class SeleniumDriver {

    public static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
            getDriver().manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);


        }
        return driver;
    }

}
