package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15L));
    }

    /**
     * Wait wrapper method
     */
    protected void waitVisibility(By elementBy){
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    protected WebElement find(By elementBy){
        return driver.findElement(elementBy);
    }

    protected List<WebElement> findAll(By elementBy){
        return driver.findElements(elementBy);
    }

    protected void click(By elementBy){
        waitVisibility(elementBy);
        find(elementBy).click();
    }

    protected void submitForm(By formElementBy){
        find(formElementBy).submit();
    }

    protected void typeText(By elementBy, String text){
        waitVisibility(elementBy);
        WebElement webElement = find(elementBy);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected String readText(By elementBy){
        waitVisibility(elementBy);
        return find(elementBy).getText();
    }

    protected void scrollIntoView(By elementBy){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String scrollIntoView = "arguments[0].scrollIntoView();";
        javascriptExecutor.executeScript(scrollIntoView, find(elementBy));
    }

    protected boolean isDisplayed(By elementBy){
        boolean result;
        try {
            result = find(elementBy).isDisplayed();
        } catch (NoSuchElementException e){
            result = false;
        }
        return result;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    protected String getAttributeValue(By elementBy){
        return find(elementBy).getAttribute("value");
    }

    protected boolean removeElement(By elementBy){
        boolean result;
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript(
                    "let iframe = arguments[0];"
                            + "iframe.parentNode.removeChild(iframe)",
                    find(elementBy));
            result = true;
        } catch (NoSuchElementException e){
            result = false;
        }
        return result;
    }

    protected String getMessageAlert(){
        String parent = driver.getWindowHandle();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().window(parent);
        return text;
    }

    protected void visit(String url){
        driver.get(url);
    }

}
