package com.demoqa.tests.automationpracticeform;

import com.demoqa.component.DemoqaAutomationPracticeFPagePopup;
import com.demoqa.pages.DemoqaAutomationPracticeFPage;
import com.demoqa.tests.base.BaseTest;
import org.testng.annotations.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class DemoqaAutomationPracticeFPageTest extends BaseTest {
    private DemoqaAutomationPracticeFPage demoqaAutomationPracticeFPage;

    @BeforeMethod
    public void setUp() {
        demoqaAutomationPracticeFPage = new DemoqaAutomationPracticeFPage(driver);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.goToAutomationPracticeForm();
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
        Set<DemoqaAutomationPracticeFPage.Hobby> hobbies = new HashSet<>();
        hobbies.add(DemoqaAutomationPracticeFPage.Hobby.READING);
        hobbies.add(DemoqaAutomationPracticeFPage.Hobby.MUSIC);
        Set<String> subjects = new LinkedHashSet<>();
        subjects.add("Maths");
        subjects.add("Computer Science");
        LocalDate dob = LocalDate.of(2000, Month.JANUARY, 1);
        String picturePath = new File("screenshots/demoqa_homepage.png").getAbsolutePath();
        DemoqaAutomationPracticeFPage.State state = DemoqaAutomationPracticeFPage.State.NCR;
        String city = "Delhi";
        // --------------------------------------------------------------------


        // Fill the inputs for Name, Email, Mobile, Subjects and
        // Current Address using the correct method and assert that each input
        // contains the correct text.
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setFirstName(firstName);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setLastName(lastName);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setEmail(email);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setGender(gender);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setMobileNumber(mobile);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setDob(dob);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.addSubjects(subjects);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.checkHobbies(hobbies);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setPicture(picturePath);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setCurrentAddress(currentAddress);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setState(state);
        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPage.setCity(city);

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
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getDobValue(), dob,
                "Date of Birth is not correct");
        softAssert.assertEquals(demoqaAutomationPracticeFPage.getState().toUpperCase(), state.toString().toUpperCase(),
                "State is not correct");

        softAssert.assertAll();
        // Click on "Submit" button
        DemoqaAutomationPracticeFPagePopup demoqaAutomationPracticeFPagePopup
                = demoqaAutomationPracticeFPage.submitForm();

        // Once submitted validate that the pop-up is shown and the information
        // is correct, then close the pop-up
        assertTrue(demoqaAutomationPracticeFPagePopup.isDisplayed());
        assertEquals(demoqaAutomationPracticeFPagePopup.getStudentName(), firstName + " " + lastName,
                "Name is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getStudentEmail(), email,
                "Email is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getGender().toUpperCase(), gender.toString(),
                "Gender is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getMobile(), mobile,
                "Mobile number is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getDob(), dob,
                "Date of birth is not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getSubjects().size(), subjects.size(),
                "Subjects are not correct");
        assertEquals(demoqaAutomationPracticeFPagePopup.getHobbies().size(), hobbies.size(),
                "Hobbies are not correct");
        String pictureResult = demoqaAutomationPracticeFPagePopup.getPicturePath();
        assertTrue(picturePath.contains(pictureResult), "Student picture is not uploaded");
        assertEquals(demoqaAutomationPracticeFPagePopup.getAddress(), currentAddress,
                "Current address is not correct");

        demoqaAutomationPracticeFPage = demoqaAutomationPracticeFPagePopup.close();
    }

}