// src/test/java/pages/ResultsPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ResultsPage {

    // WEBDRIVER
    private final WebDriver driver;
    private final WebDriverWait wait;

    // LOCATORS
    private final By resultsContainer = By.id("search");
    private final By firstResultTitle = By.cssSelector("#search a h3");

    // CONSTRUCTOR
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // METHODS
    public boolean hasResults() {
        return !driver.findElements(resultsContainer).isEmpty();
    }

    public void clickFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstResultTitle)).click();
    }
}
