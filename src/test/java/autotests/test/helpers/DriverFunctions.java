package autotests.test.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFunctions {

    public static void clickElementWithText(WebDriver driver, String text) {
        driver.findElement(By.xpath(CustomLocators.getXpathForAnyElementWithTextEquals(text))).click();
    }

    public static WebElement getElementWithTextContaining(WebDriver driver, String text) {
        return driver.findElement(By.xpath(CustomLocators.getXpathForAnyElementWithTextContaining(text)));
    }

    public static WebElement getElementOfTypeWithTextContaining(WebDriver driver, String elementType, String text) {
        return driver.findElement(By.xpath(CustomLocators.getXpathForElementTypeWithTextContaining(elementType, text)));
    }

    public static void clickElementTypeWithText(WebDriver driver, String elementType, String elementText) {
        driver.findElement(By.xpath(CustomLocators.getXpathForElementTypeWithTextEquals(elementType, elementText))).click();
    }

    public static WebElement getElementWithText(WebDriver driver, String text) {
        return driver.findElement(By.xpath(CustomLocators.getXpathForAnyElementWithTextEquals(text)));
    }

    public static WebElement getElementOfTypeWithTextEquals(WebDriver driver, String elementType, String text) {
        return driver.findElement(By.xpath(CustomLocators.getXpathForElementTypeWithTextEquals(elementType, text)));
    }

    public static void waitForElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
