package com.demoqa;

import com.demoqa.component.DemoqaAutomationPracticeFPagePopup;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// page_url = https://demoqa.com/automation-practice-form
public class DemoqaAutomationPracticeFPage {
    private final WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userNumber")
    private WebElement userNumber;

    @FindBy(id = "dateOfBirthInput")
    private WebElement userDob;

    // FIXME WebElement userSubjectsContainer
    @FindBy(id = "subjectsInput")
//    @FindBy(className = "subjects-auto-complete__value-container")
    private WebElement subjectsInput;

    @FindBy(className= "subjects-auto-complete__value-container")
    private WebElement subjectsAutoComplete;

    @FindBy(css = ".subjects-auto-complete__menu")
    private List<WebElement> menuSubjects;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureFormControlFile;

    @FindBy(id = "currentAddress")
    private WebElement userCurrentAddress;

    @FindBy(id = "state")
    private WebElement stateSelect;

    @FindBy(id = "city")
    private WebElement citySelect;

    @FindBy(id = "submit")
    private WebElement submit;

    // ----------- Date Picker -----------------------------
    @FindBy(className = "react-datepicker__month-select")
    private WebElement monthSelect;

    @FindBy(className = "react-datepicker__year-select")
    private WebElement yearSelect;

    private final String daySelectorPrefix = "react-datepicker__day--0";
    private By dayBy;
    // ---------------------------------------------------

    public DemoqaAutomationPracticeFPage(@NotNull WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contentEquals("https://demoqa.com/automation-practice-form")){
            throw new IllegalStateException("This is not Automation Practice Form Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public DemoqaAutomationPracticeFPage typeFirstName(String firstName){
        this.firstName.sendKeys(firstName);
        return this;
    }

    public DemoqaAutomationPracticeFPage typeLastName(String lastName){
        this.lastName.sendKeys(lastName);
        return this;
    }

    public DemoqaAutomationPracticeFPage typeEmail(String email){
        this.userEmail.sendKeys(email);
        return this;
    }

    public DemoqaAutomationPracticeFPage chooseGender(@NotNull Gender gender){
        driver.findElement(gender.getBy()).click();
        return this;
    }

    public DemoqaAutomationPracticeFPage typeMobileNumber(String mobileNumber){
        this.userNumber.sendKeys(mobileNumber);
        return this;
    }

    public DemoqaAutomationPracticeFPage chooseDob(@NotNull LocalDate localDate){
        userDob.click();
        Select selectMonth = new Select(monthSelect);
        selectMonth.selectByValue(String.valueOf(localDate.getMonth().ordinal()));
        Select selectYear = new Select(yearSelect);
        selectYear.selectByValue(String.valueOf(localDate.getYear()));
        int day = localDate.getDayOfMonth();
        String name = daySelectorPrefix + (day < 10? "0" + day : day);
        dayBy = By.className(name);
        driver.findElement(dayBy).click();
        return this;
    }

    public DemoqaAutomationPracticeFPage addSubject(String subject){
        subjectsInput.sendKeys(subject);
        driver.findElement(By.id("react-select-2-option-0")).click();
        return this;
    }

    public DemoqaAutomationPracticeFPage checkHobby(@NotNull Hobby hobby){
        WebElement option = driver.findElement(hobby.getBy());
        if (!option.isSelected()){
            option.click();
        }
        return this;
    }

    public DemoqaAutomationPracticeFPage uncheckHobby(@NotNull Hobby hobby){
        WebElement option = driver.findElement(hobby.getBy());
        if (option.isSelected()){
            option.click();
        }
        return this;
    }

    public DemoqaAutomationPracticeFPage uploadPicture(String picturePath){
        this.uploadPictureFormControlFile.sendKeys(picturePath);
        return this;
    }

    public DemoqaAutomationPracticeFPage typeCurrentAddress(String currentAddress){
        this.userCurrentAddress.sendKeys(currentAddress);
        return this;
    }

    // FIXME: setStateSelect(String keys)
    public DemoqaAutomationPracticeFPage setStateSelect(String keys) {
//        findFromOptionFromDropdown(stateSelect, keys);
        return this;
    }

    // TODO: findFromOptionFromDropdown(WebElement dropdown, String keys)
    private @NotNull WebElement findFromOptionFromDropdown(WebElement dropdown, String keys){
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdown).click().sendKeys(keys)
                .perform();
//        List<WebElement> options = dropdown.findElements(By.xpath(
//                "//div[contains(@class, starts-with(@class, 'css-')));
//        for (WebElement option :
//                options) {
//            if (option.getText().trim().toUpperCase().contains(keys.toUpperCase())){
//                return option;
//            }
//        }
        throw new NotFoundException("Option not found");
    }

    // FIXME: setCitySelect(String keys)
    public DemoqaAutomationPracticeFPage setCitySelect(String keys) {
//        findFromOptionFromDropdown(citySelect, keys).click();

        return this;
    }

    public DemoqaAutomationPracticeFPagePopup submitForm(){
        submit.click();
        return new DemoqaAutomationPracticeFPagePopup(driver);
    }

    public String getFirstNameText() {
        return firstName.getAttribute("value");
    }

    public String getLastNameText() {
        return lastName.getAttribute("value");
    }

    public String getEmailText() {
        return userEmail.getAttribute("value");
    }

    public String getMobileNumberText() {
        return userNumber.getAttribute("value");
    }

    public LocalDate getDobValue(){
        return LocalDate.parse(userDob.getAttribute("value"), DateTimeFormatter.ofPattern("d MMM uuuu"));
    }

    // FIXME: getUserSubjectsContainerText()
    public String getUserSubjectsContainerText() {
        return subjectsInput.getAttribute("value");
    }

    public List<String > getMenuSubjects() {
        List<WebElement> subjects = driver.findElements(By.cssSelector(".subjects-auto-complete__multi-value__label"));
        List<String> subjectsText = new ArrayList<>();
        for (WebElement subject : subjects) {
            subjectsText.add(subject.getText());
        }
        return subjectsText;
    }

    public String getCurrentAddressText() {
        return userCurrentAddress.getAttribute("value");
    }

    public enum Gender {
        MALE(By.cssSelector(".custom-radio:nth-child(1) > .custom-control-label")),
        FEMALE(By.cssSelector(".custom-radio:nth-child(2) > .custom-control-label")),
        OTHER(By.cssSelector(".custom-radio:nth-child(3) > .custom-control-label"));

        private final By by;
        Gender(By by){
            this.by = by;
        }

        public By getBy() {
            return by;
        }

    }

    public enum Hobby {
        SPORTS(By.cssSelector(".custom-checkbox:nth-child(1) > .custom-control-label")),
        READING(By.cssSelector(".custom-checkbox:nth-child(2) > .custom-control-label")),
        MUSIC(By.cssSelector(".custom-checkbox:nth-child(3) > .custom-control-label"));

        private final By by;
        Hobby(By by){
            this.by = by;
        }

        public By getBy() {
            return by;
        }
    }

}