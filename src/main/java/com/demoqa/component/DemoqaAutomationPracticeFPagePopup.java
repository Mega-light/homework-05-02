package com.demoqa.component;

import com.demoqa.pages.DemoqaAutomationPracticeFPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


// Popup of https://demoqa.com/automation-practice-form
public class DemoqaAutomationPracticeFPagePopup {
    private final WebDriver driver;

    @FindBy(className = "modal-content")
    private WebElement divPopup;

    @FindBy(css = "table > tbody > tr:nth-child(1) > td:nth-child(2)")
    private WebElement tdFullName;

    @FindBy(css = "table > tbody > tr:nth-child(2) > td:nth-child(2)")
    private WebElement tdEmail;

    @FindBy(css = "table > tbody > tr:nth-child(3) > td:nth-child(2)")
    private WebElement tdGender;

    @FindBy(css = "table > tbody > tr:nth-child(4) > td:nth-child(2)")
    private WebElement tdMobileNumber;

    @FindBy(css = "table > tbody > tr:nth-child(5) > td:nth-child(2)")
    private WebElement tdDob;

    @FindBy(css = "table > tbody > tr:nth-child(6) > td:nth-child(2)")
    private WebElement tdSubjects;

    @FindBy(css = "table > tbody > tr:nth-child(7) > td:nth-child(2)")
    private WebElement tdHobbies;

    @FindBy(css = "table > tbody > tr:nth-child(8) > td:nth-child(2)")
    private WebElement tdPicturePath;

    @FindBy(css = "table > tbody > tr:nth-child(9) > td:nth-child(2)")
    private WebElement tdAddress;

    @FindBy(css = "table > tbody > tr:nth-child(10) > td:nth-child(2)")
    private WebElement tdStateAndCity;

    @FindBy(id = "closeLargeModal")
    private WebElement btnClose;

    public DemoqaAutomationPracticeFPagePopup(@NotNull WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contentEquals("https://demoqa.com/automation-practice-form")){
            throw new IllegalStateException("This is not Automation Practice Form Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
        if (!isDisplayed()){
            throw new IllegalStateException("This Popup did not appear on Automation Practice Form Page.");
        }
    }

    public DemoqaAutomationPracticeFPage close(){
        btnClose.click();
        return new DemoqaAutomationPracticeFPage(driver);
    }

    public boolean isDisplayed() {
        return divPopup.isDisplayed();
    }

    public String getFullName(){
        return tdFullName.getText();
    }

    public String getEmail(){
        return tdEmail.getText();
    }

    public String getGender(){
        return tdGender.getText();
    }

    public String getMobileNumber(){
        return tdMobileNumber.getText();
    }

    public LocalDate getDob(){
        DateTimeFormatter dobTextFormat = DateTimeFormatter.ofPattern("dd MMMM,yyyy");
        return LocalDate.parse(tdDob.getText(), dobTextFormat);
    }

    public List<String> getSubjects(){
        String[] split = tdSubjects.getText().split(", ");
        return Arrays.asList(split);
    }

    public List<String> getHobbies(){
        String[] split = tdHobbies.getText().split(", ");
        return Arrays.asList(split);
    }

    public String getPicturePath(){
        return tdPicturePath.getText();
    }

    public String getAddress(){
        return tdAddress.getText();
    }

    public String getStateAndCity(){
        return tdStateAndCity.getText();
    }

}