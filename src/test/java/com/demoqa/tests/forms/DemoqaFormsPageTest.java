package com.demoqa.tests.forms;

import com.demoqa.pages.DemoqaAutomationPracticeFPage;
import com.demoqa.pages.DemoqaFormsPage;
import com.demoqa.tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


class DemoqaFormsPageTest extends BaseTest {

    private DemoqaFormsPage demoqaFormsPage;

    @BeforeMethod
    public void setUp() {
        demoqaFormsPage = new DemoqaFormsPage(driver);
        demoqaFormsPage.goToFormsPage();
    }

    @Test
    void clickPracticeFormRedirectsToAutomationFormPage() {
        // When
        DemoqaAutomationPracticeFPage demoqaAutomationPracticeFPage = demoqaFormsPage.clickPracticeForm();

        // Then
        String expectedUrl = "https://demoqa.com/automation-practice-form";
        assertNotNull(demoqaFormsPage);
        assertTrue(demoqaFormsPage.getCurrentURL().contentEquals(expectedUrl));
    }

}