package com.demoqa.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


// page_url = https://demoqa.com/forms
public class DemoqaFormsPage extends BasePage{

    private final String baseUrl = "https://demoqa.com/forms";
    private final By spnPracticeFormItem = By.cssSelector(".show .text");

    public DemoqaFormsPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public DemoqaFormsPage goToFormsPage(){
        driver.get(baseUrl);
        return new DemoqaFormsPage(driver);
    }

    public DemoqaAutomationPracticeFPage clickPracticeForm(){
        click(spnPracticeFormItem);
        return new DemoqaAutomationPracticeFPage(driver);
    }
}