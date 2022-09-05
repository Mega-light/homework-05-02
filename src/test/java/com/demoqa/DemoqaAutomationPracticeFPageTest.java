package com.demoqa;

import com.demoqa.component.DemoqaAutomationPracticeFPagePopup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.DriverFactory;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class DemoqaAutomationPracticeFPageTest {
    private WebDriver driver;
    private SoftAssert softAssert;
    private DemoqaAutomationPracticeFPage demoqaAutomationPracticeFPage;

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @BeforeClass
    void setup(){
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    @Parameters({"URL", "BrowserType"})
    public void setUp(String url, String browserType) {
        driver = DriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.get(url);
        demoqaAutomationPracticeFPage = new DemoqaAutomationPracticeFPage(driver);
    }

    @Test
    void formIsFilled() {
        // ------------------------------- Data -------------------------------
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email@example.com";
        String mobile = "0123456789";
        String currentAddress = "Current Address";
        DemoqaAutomationPracticeFPage.Gender gender = DemoqaAutomationPracticeFPage.Gender.MALE;
        DemoqaAutomationPracticeFPage.Hobby hobby = DemoqaAutomationPracticeFPage.Hobby.READING;
        List<String> subjects = new ArrayList<>();
        subjects.add("Maths");
        subjects.add("Computer Science");
        LocalDate dob = LocalDate.of(2000, Month.JANUARY, 1);
        DateTimeFormatter dobTextFormat = DateTimeFormatter.ofPattern("dd MMMM,yyyy");
        String picturePath = new File("screenshots/demoqa_homepage.png").getAbsolutePath();
        String state = "NCR";
        String city = "Delhi";
        // --------------------------------------------------------------------


        // Fill the inputs for Name, Email, Mobile, Subjects and
        // Current Address using the correct method and assert that each input
        // contains the correct text.
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.typeFirstName(firstName);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.typeLastName(lastName);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.typeEmail(email);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.typeMobileNumber(mobile);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.addSubject(subjects.get(0));
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.addSubject(subjects.get(1));
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.typeCurrentAddress(currentAddress);

        softAssert.assertEquals(demoqaAutomationPracticeFPage.getFirstNameText(), firstName,
                "First name field not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getLastNameText(), lastName,
                "Last name field is not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getEmailText(), email,
                "Email field is not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getMobileNumberText(), mobile,
                "Mobile is not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getCurrentAddressText(), currentAddress,
                "Current Address is not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getMenuSubjects().size(), subjects.size(),
                "Subjects are not correct");

        // Select any gender option
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.chooseGender(gender);

        // Complete the Date of Birth using clicks and then assert the input
        // has the correct date.
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.chooseDob(dob);
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getDobValue(), dob,
                "Date of Birth is not correct");

        // Select any hobby option
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.checkHobby(hobby);

        // Add an Image to the “Select Picture” input
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.uploadPicture(picturePath);

        // Complete the dropdown of State and City and validate the text displayed is correct.
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setStateSelect(state);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setCitySelect(city);

        softAssert.assertAll();
        // Click on "Submit" button
        DemoqaAutomationPracticeFPagePopup demoqaAutomationPracticeFPagePopup
                = demoqaAutomationPracticeFPage.submitForm();

        // Once submitted validate that the pop-up is shown and the information
        // is correct, then close the pop-up
        assertTrue(demoqaAutomationPracticeFPagePopup.isDisplayed());
        assertEquals(demoqaAutomationPracticeFPagePopup.getStudentName(), firstName + " " + lastName,
                "Student name is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getStudentEmail(), email,
                "Student email is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getGender().toUpperCase(), gender.toString(),
                "Student gender is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getMobile(), mobile,
                "Student mobile is not correct");
        assertEquals(LocalDate.parse(demoqaAutomationPracticeFPagePopup.getDob(), dobTextFormat), dob,
                "Student date of birth is not correct");


        String pictureResult = demoqaAutomationPracticeFPagePopup.getPicturePath();
        assertTrue(picturePath.contains(pictureResult), "Student picture is not uploaded");
        assertEquals(demoqaAutomationPracticeFPagePopup.getAddress(), currentAddress,
                "Student current address is not correct");

        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPagePopup.close();
    }

}