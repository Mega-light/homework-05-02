package com.demoqa.tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utils.DriverFactory;

public abstract class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(String browserName){
        softAssert = new SoftAssert();
        driver = DriverFactory.getDriver(browserName);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
