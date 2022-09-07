package com.demoqa.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class DatePicker {
    private final WebDriver driver;
    private final By monthSelectBy = By.className("react-datepicker__month-select");
    private final By yearSelectBy = By.className("react-datepicker__year-select");
    private final String daySelectorPrefix = "react-datepicker__day--0";
    private By dayBy;

    public DatePicker(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(LocalDate date){
        Select selectMonth = new Select(driver.findElement(monthSelectBy));
        selectMonth.selectByValue(String.valueOf(date.getMonth().ordinal()));
        Select selectYear = new Select(driver.findElement(yearSelectBy));
        selectYear.selectByValue(String.valueOf(date.getYear()));
        int day = date.getDayOfMonth();
        String name = daySelectorPrefix + (day < 10? "0" + day : day);
        dayBy = By.className(name);
        driver.findElement(dayBy).click();
    }
}
