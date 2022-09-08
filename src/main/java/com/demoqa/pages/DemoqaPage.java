package com.demoqa.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// page_url = https://demoqa.com/
public class DemoqaPage extends BasePage {
    private final String baseUrl = "https://demoqa.com/";

    private final By svgFormsCard = By.cssSelector(".card:nth-child(2) svg");

    public DemoqaPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public DemoqaPage goToHomePage(){
        driver.get(baseUrl);
        return new DemoqaPage(driver);
    }

    public DemoqaFormsPage clickFormsCard(){
        click(svgFormsCard);
        return new DemoqaFormsPage(driver);
    }
}