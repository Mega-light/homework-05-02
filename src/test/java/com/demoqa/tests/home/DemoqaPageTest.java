package com.demoqa.tests.home;

import com.demoqa.pages.DemoqaFormsPage;
import com.demoqa.pages.DemoqaPage;
import com.demoqa.tests.base.BaseTest;
import org.testng.annotations.*;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

class DemoqaPageTest extends BaseTest {

    private DemoqaPage demoqaPage;

    @BeforeClass
    public void setUp(){
        // Given
        demoqaPage = new DemoqaPage(driver);
        demoqaPage.goToHomePage();
    }

    @Test
    public void clickFormsCardRedirectsToFormsPage() {
        // When
        DemoqaFormsPage demoqaFormsPage = demoqaPage.clickFormsCard();

        // Then
        String expectedUrl = "https://demoqa.com/forms";
        assertNotNull(demoqaFormsPage);
        assertTrue(demoqaFormsPage.getCurrentURL().contentEquals(expectedUrl));

    }

}