package com.demoqa;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


class DemoqaFormsPageTest {

    private DemoqaFormsPage demoqaFormsPage;
    private WebDriver driver;

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    @Parameters({"URL", "BrowserType"})
    public void setUp(String url, String browserType) {
        driver = DriverFactory.getDriver(browserType);
        driver.get(url);
        driver.manage().window().maximize();
        demoqaFormsPage = new DemoqaFormsPage(driver);
    }

    @Test
    void clickPracticeFormRedirectsToAutomationFormPage() {
        // When
        DemoqaAutomationPracticeFPage demoqaAutomationPracticeFPage = demoqaFormsPage.clickPracticeForm();

        // Then
        String expectedUrl = "https://demoqa.com/automation-practice-form";
        assertNotNull(demoqaFormsPage);
        assertTrue(driver.getCurrentUrl().contentEquals(expectedUrl));
    }

}