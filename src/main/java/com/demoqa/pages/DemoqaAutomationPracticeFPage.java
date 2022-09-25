package com.demoqa.pages;

import com.demoqa.component.DatePicker;
import com.demoqa.component.DemoqaAutomationPracticeFPagePopup;
import com.demoqa.component.State;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// page_url = https://demoqa.com/automation-practice-form
public class DemoqaAutomationPracticeFPage extends BasePage{
    private final String baseUrl = "https://demoqa.com/automation-practice-form";
    private final By txtFirstName = By.id("firstName");
    private final By txtLastName = By.id("lastName");
    private final By txtEmail = By.id("userEmail");
    private final By txtMobileNumber = By.id("userNumber");
    private final By txtDob = By.id("dateOfBirthInput");
    private final By txtSubjectsInput = By.id("subjectsInput");
    private final By fileInputPicture = By.id("uploadPicture");
    private final By txaCurrentAddress = By.id("currentAddress");
    private final By subjectsAutoCompleteBy = By.className("subjects-auto-complete__value-container");
    private final String stateById = "state";
    private final String cityById = "city";
    private final By divState = By.id(stateById);
    private final By divCity = By.id(cityById);
    private final By frmUserForm = By.id("userForm");

    // ----------- Date Picker -----------------------------
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM uuuu");
    private final DatePicker datePicker;
    // ---------------------------------------------------
    private final By divGoogleAd = By.id("adplus-anchor");

    public DemoqaAutomationPracticeFPage(@NotNull WebDriver driver) {
        super(driver);
        datePicker = new DatePicker(driver);
    }

    public DemoqaAutomationPracticeFPage setFirstName(String firstName){
        scrollIntoView(txtFirstName);
        typeText(txtFirstName, firstName);
        return this;
    }

    public DemoqaAutomationPracticeFPage setLastName(String lastName){
        scrollIntoView(txtLastName);
        typeText(txtLastName, lastName);
        return this;
    }

    public DemoqaAutomationPracticeFPage setEmail(String email){
        scrollIntoView(txtEmail);
        typeText(txtEmail, email);
        return this;
    }

    public DemoqaAutomationPracticeFPage setGender(@NotNull Gender gender){
        click(gender.getBy());
        return this;
    }

    public DemoqaAutomationPracticeFPage setMobileNumber(String mobileNumber){
        scrollIntoView(txtMobileNumber);
        typeText(txtMobileNumber, mobileNumber);
        return this;
    }

    public DemoqaAutomationPracticeFPage setDob(@NotNull LocalDate localDate){
        scrollIntoView(txtDob);
        click(txtDob);
        datePicker.setDate(localDate);
        return this;
    }

    public DemoqaAutomationPracticeFPage addSubject(String subject){
        scrollIntoView(txtSubjectsInput);
        typeText(txtSubjectsInput, subject);
        click(By.id("react-select-2-option-0"));
        return this;
    }

    public DemoqaAutomationPracticeFPage addSubjects(Set<String> subjects){
        for (String subject:
             subjects) {
            addSubject(subject);
        }
        return this;
    }

    public DemoqaAutomationPracticeFPage checkHobby(@NotNull Hobby hobby){
        WebElement option = find(hobby.getBy());
        if (!option.isSelected()){
            option.click();
        }
        return this;
    }
    public DemoqaAutomationPracticeFPage checkHobbies(@NotNull Set<Hobby> hobbies){
        for (Hobby hobby: hobbies) {
            checkHobby(hobby);
        }
        return this;
    }


    public DemoqaAutomationPracticeFPage uncheckHobby(@NotNull Hobby hobby){
        WebElement option = find(hobby.getBy());
        if (option.isSelected()){
            option.click();
        }
        return this;
    }

    public DemoqaAutomationPracticeFPage setPicture(String picturePath){
        scrollIntoView(fileInputPicture);
        typeText(fileInputPicture, picturePath);
        return this;
    }

    public DemoqaAutomationPracticeFPage setCurrentAddress(String currentAddress){
        scrollIntoView(txaCurrentAddress);
        typeText(txaCurrentAddress, currentAddress);
        return this;
    }

    private DemoqaAutomationPracticeFPage setState(@NotNull State state) {
        scrollIntoView(divState);
        click(divState);
        click(state.getBy());
        return this;
    }

    public DemoqaAutomationPracticeFPage setStateAndCity(@NotNull State state, String city) {
        setState(state);
        if (state.getCities().contains(city)){
            setCity(city);
        } else {
            throw new IllegalArgumentException("City is not part of " + state);
        }
        return this;
    }

    private DemoqaAutomationPracticeFPage setCity(String city) {
        scrollIntoView(divCity);
        click(divCity);
        typeText(By.id("react-select-4-input"), city);
        //WebElement menuCities = find(By.cssSelector("div#city > div:nth-child(2) > div:first-child"));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        return this;
    }

    public DemoqaAutomationPracticeFPagePopup submitForm(){
        find(frmUserForm).submit();
        return new DemoqaAutomationPracticeFPagePopup(driver);
    }

    public String getFirstNameText() {
        return getAttributeValue(txtFirstName);
    }

    public String getLastNameText() {
        return getAttributeValue(txtLastName);
    }

    public String getEmailText() {
        return getAttributeValue(txtEmail);
    }

    public String getMobileNumberText() {
        return getAttributeValue(txtMobileNumber);
    }

    public LocalDate getDobValue(){
        return LocalDate.parse(getAttributeValue(txtDob), dateTimeFormatter);
    }

    // FIXME: getUserSubjectsContainerText()
    public String getUserSubjectsContainerText() {
        return getAttributeValue(txtSubjectsInput);
    }

    public List<String > getMenuSubjects() {
        List<WebElement> subjects = findAll(By.cssSelector(".subjects-auto-complete__multi-value__label"));
        List<String> subjectsText = new ArrayList<>();
        for (WebElement subject : subjects) {
            subjectsText.add(subject.getText());
        }
        return subjectsText;
    }

    public String getCurrentAddressText() {
        return getAttributeValue(txaCurrentAddress);
    }

    public String getState() {
        By selectedState =By.cssSelector("div[id='" + stateById
                + "'] > div > div:first-child > div[class$='singleValue']");
        return readText(selectedState);
    }

    public String getCity(){
        By selectedCity = By.cssSelector("div[id='" + cityById
                + "'] > div > div:first-child > div[class$='singleValue']");
        return readText(selectedCity);
    }

    public DemoqaAutomationPracticeFPagePopup submitWith(String firstName,
                                                         String lastName,
                                                         String email,
                                                         Gender gender,
                                                         String mobileNumber,
                                                         LocalDate dob,
                                                         Set<String> subjects,
                                                         Set<Hobby> hobbies,
                                                         String picturePath,
                                                         String currentAddress,
                                                         State state,
                                                         String city){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
        setMobileNumber(mobileNumber);
        setDob(dob);
        addSubjects(subjects);
        checkHobbies(hobbies);
        setPicture(picturePath);
        setCurrentAddress(currentAddress);
        setState(state);
        setCity(city);
        submitForm(frmUserForm);
        return new DemoqaAutomationPracticeFPagePopup(driver);
    }

    public DemoqaAutomationPracticeFPage goToAutomationPracticeForm() {
        visit(baseUrl);
        removeElement(divGoogleAd);
        return new DemoqaAutomationPracticeFPage(driver);
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