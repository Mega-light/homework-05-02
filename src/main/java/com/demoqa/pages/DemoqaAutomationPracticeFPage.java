package com.demoqa.pages;

import com.demoqa.component.DatePicker;
import com.demoqa.component.DemoqaAutomationPracticeFPagePopup;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// page_url = https://demoqa.com/automation-practice-form
public class DemoqaAutomationPracticeFPage extends BasePage{
    private final String baseUrl = "https://demoqa.com/automation-practice-form";
    private final By firstNameBy = By.id("firstName");
    private final By lastNameBy = By.id("lastName");
    private final By userEmailBy = By.id("userEmail");
    private final By userNumberBy = By.id("userNumber");
    private final By userDobBy = By.id("dateOfBirthInput");
    private final By subjectsInputBy = By.id("subjectsInput");
    private final By uploadPictureBy = By.id("uploadPicture");
    private final By currentAddressBy = By.id("currentAddress");
    private final By subjectsAutoCompleteBy = By.className("subjects-auto-complete__value-container");
    private final String stateById = "state";
    private final String cityById = "city";
    private final By stateBy = By.id(stateById);
    private final By cityBy = By.id(cityById);
    private final By userFormBy = By.id("userForm");

    // ----------- Date Picker -----------------------------
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM uuuu");
    private final DatePicker datePicker;
    // ---------------------------------------------------

    public DemoqaAutomationPracticeFPage(@NotNull WebDriver driver) {
        super(driver);
        datePicker = new DatePicker(driver);
    }

    public DemoqaAutomationPracticeFPage setFirstName(String firstName){
        typeText(firstNameBy, firstName);
        return this;
    }

    public DemoqaAutomationPracticeFPage setLastName(String lastName){
        typeText(lastNameBy, lastName);
        return this;
    }

    public DemoqaAutomationPracticeFPage setEmail(String email){
        typeText(userEmailBy, email);
        return this;
    }

    public DemoqaAutomationPracticeFPage setGender(@NotNull Gender gender){
        click(gender.getBy());
        return this;
    }

    public DemoqaAutomationPracticeFPage setMobileNumber(String mobileNumber){
        typeText(userNumberBy, mobileNumber);
        return this;
    }

    public DemoqaAutomationPracticeFPage setDob(@NotNull LocalDate localDate){
        click(userDobBy);
        datePicker.setDate(localDate);
        return this;
    }

    public DemoqaAutomationPracticeFPage addSubject(String subject){
        typeText(subjectsInputBy, subject);
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
        typeText(uploadPictureBy, picturePath);
        return this;
    }

    public DemoqaAutomationPracticeFPage setCurrentAddress(String currentAddress){
        typeText(currentAddressBy, currentAddress);
        return this;
    }

    public DemoqaAutomationPracticeFPage setState(@NotNull State state) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String scrollIntoView = "arguments[0].scrollIntoView();";
        javascriptExecutor.executeScript(scrollIntoView, find(stateBy));
        click(stateBy);
        click(state.getBy());
        return this;
    }

    // FIXME: setCitySelect(String keys)
    public DemoqaAutomationPracticeFPage setCity(String keys) {
//        findFromOptionFromDropdown(citySelect, keys).click();

        return this;
    }

    public DemoqaAutomationPracticeFPagePopup submitForm(){
        find(userFormBy).submit();
        return new DemoqaAutomationPracticeFPagePopup(driver);
    }

    public String getFirstNameText() {
        return getAttributeValue(firstNameBy);
    }

    public String getLastNameText() {
        return getAttributeValue(lastNameBy);
    }

    public String getEmailText() {
        return getAttributeValue(userEmailBy);
    }

    public String getMobileNumberText() {
        return getAttributeValue(userNumberBy);
    }

    public LocalDate getDobValue(){
        return LocalDate.parse(getAttributeValue(userDobBy), dateTimeFormatter);
    }

    // FIXME: getUserSubjectsContainerText()
    public String getUserSubjectsContainerText() {
        return getAttributeValue(subjectsInputBy);
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
        return getAttributeValue(currentAddressBy);
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
        submitForm(userFormBy);
        return new DemoqaAutomationPracticeFPagePopup(driver);
    }

    public DemoqaAutomationPracticeFPage goToAutomationPracticeForm() {
        driver.get(baseUrl);
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

    public enum State{
        NCR(By.id("react-select-3-option-0")),
        UTTAR_PRADESH(By.id("react-select-3-option-1")),
        HARYANA(By.id("react-select-3-option-2")),
        RAJASTHAN(By.id("react-select-3-option-3"));

        private final By by;
        State(By by){
            this.by = by;
        }

        public By getBy() {
            return by;
        }

        @Override
        public String toString() {
            String[] s = name().split("_");
            StringBuilder word = new StringBuilder();
            if (s.length > 1){
                for (String w : s) {
                    word.append(w.substring(0, 1)).append(w.substring(1)).append(" ");
                }
            } else {
                String w = s[0];
                word.append(w.substring(0, 1)).append(w.substring(1));
            }
            return word.toString();
        }
    }
}