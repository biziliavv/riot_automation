package setup;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static setup.SeleniumDriver.driver;
import static setup.SeleniumDriver.getDriver;

/**
 * SeleniumBaseTest
 * <p>
 * Created by vitaliybizilia on 5/17/17.
 */
public class SeleniumBaseTest {


    @AfterTest
    public static void tearDown() {
        getDriver().close();
    }

    public static void waitFor(Integer seconds) {
        getDriver().manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
    }


}





