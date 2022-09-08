package com.demoqa.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class DatePicker {
    private final WebDriver driver;
    private final By selMonth = By.className("react-datepicker__month-select");
    private final By selYear = By.className("react-datepicker__year-select");
    private final String daySelectorPrefix = "react-datepicker__day--0";
    private By divDay;

    public DatePicker(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(LocalDate date){
        Select selectMonth = new Select(driver.findElement(selMonth));
        selectMonth.selectByValue(String.valueOf(date.getMonth().ordinal()));
        Select selectYear = new Select(driver.findElement(selYear));
        selectYear.selectByValue(String.valueOf(date.getYear()));
        int day = date.getDayOfMonth();
        String name = daySelectorPrefix + (day < 10? "0" + day : day);
        divDay = By.className(name);
        driver.findElement(divDay).click();
    }
}
