package com.demoqa.component;

import com.demoqa.DemoqaAutomationPracticeFPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


// Popup of https://demoqa.com/automation-practice-form
public class DemoqaAutomationPracticeFPagePopup {
    private final WebDriver driver;

    @FindBy(className = "modal-content")
    private WebElement popup;

    @FindBy(css = "table > tbody > tr:nth-child(1) > td:nth-child(2)")
    private WebElement studentName;

    @FindBy(css = "table > tbody > tr:nth-child(2) > td:nth-child(2)")
    private WebElement studentEmail;

    @FindBy(css = "table > tbody > tr:nth-child(3) > td:nth-child(2)")
    private WebElement gender;

    @FindBy(css = "table > tbody > tr:nth-child(4) > td:nth-child(2)")
    private WebElement mobile;

    @FindBy(css = "table > tbody > tr:nth-child(5) > td:nth-child(2)")
    private WebElement dob;

    @FindBy(css = "table > tbody > tr:nth-child(6) > td:nth-child(2)")
    private WebElement subjects;

    @FindBy(css = "table > tbody > tr:nth-child(7) > td:nth-child(2)")
    private WebElement hobbies;

    @FindBy(css = "table > tbody > tr:nth-child(8) > td:nth-child(2)")
    private WebElement picturePath;

    @FindBy(css = "table > tbody > tr:nth-child(9) > td:nth-child(2)")
    private WebElement address;

    @FindBy(css = "table > tbody > tr:nth-child(10) > td:nth-child(2)")
    private WebElement stateAndCity;

    @FindBy(id = "closeLargeModal")
    private WebElement closeBtn;

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
        closeBtn.click();
        return new DemoqaAutomationPracticeFPage(driver);
    }

    public boolean isDisplayed() {
        return popup.isDisplayed();
    }

    public String getStudentName(){
        return studentName.getText();
    }

    public String getStudentEmail(){
        return studentEmail.getText();
    }

    public String getGender(){
        return gender.getText();
    }

    public String getMobile(){
        return mobile.getText();
    }

    public String getDob(){
        return dob.getText();
    }

    public String getSubjects(){
        return subjects.getText();
    }

    public String getHobbies(){
        return hobbies.getText();
    }

    public String getPicturePath(){
        return picturePath.getText();
    }

    public String getAddress(){
        return address.getText();
    }

    public String getStateAndCity(){
        return stateAndCity.getText();
    }

}