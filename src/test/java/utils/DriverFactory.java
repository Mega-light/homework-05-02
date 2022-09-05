package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver getDriver(@NotNull String browserType){
        WebDriver driver;
        if (browserType.equalsIgnoreCase("Chrome")){
            driver = WebDriverManager.chromedriver().create();
        } else if (browserType.equalsIgnoreCase("Edge")) {
            driver = WebDriverManager.edgedriver().create();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
        } else {
            throw new NotFoundException("Web driver is not specified." +
                    "Make sure to add the browserType as a parameter in the testng.xml and factory it" +
                    "in the getDriver method");
        }
        return driver;
    }
}
