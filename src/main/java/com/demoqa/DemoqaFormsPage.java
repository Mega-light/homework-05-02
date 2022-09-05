package com.demoqa;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


// page_url = https://demoqa.com/forms
public class DemoqaFormsPage {
    private final WebDriver driver;
    @FindBy(css = ".show .text")
    private WebElement practiceFormItem;

    public DemoqaFormsPage(@NotNull WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contentEquals("https://demoqa.com/forms")){
            throw new IllegalStateException("This is not Forms Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public DemoqaAutomationPracticeFPage clickPracticeForm(){
        practiceFormItem.click();
        return new DemoqaAutomationPracticeFPage(driver);
    }
}