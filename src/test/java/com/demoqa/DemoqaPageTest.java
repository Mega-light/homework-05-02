package com.demoqa;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

class DemoqaPageTest {

    private DemoqaPage demoqaPage;
    private WebDriver driver;

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeClass
    @Parameters({"URL", "BrowserType"})
    public void setUp(String url, String browserType){
        // Given
        driver = DriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.get(url);
        demoqaPage = new DemoqaPage(driver);
    }

    @Test
    public void clickFormsCardRedirectsToFormsPage() {
        // When
        DemoqaFormsPage demoqaFormsPage = demoqaPage.clickFormsCard();

        // Then
        String expectedUrl = "https://demoqa.com/forms";
        assertNotNull(demoqaFormsPage);
        assertTrue(driver.getCurrentUrl().contentEquals(expectedUrl));

    }

}