package com.demoqa;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://demoqa.com/
public class DemoqaPage {
    private final WebDriver driver;
    @FindBy(css = ".card:nth-child(2) svg")
    private WebElement formsCard;

    public DemoqaPage(@NotNull WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contentEquals("https://demoqa.com/")){
            throw new IllegalStateException("This is not Main Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public DemoqaFormsPage clickFormsCard(){
        formsCard.click();
        return new DemoqaFormsPage(driver);
    }
}