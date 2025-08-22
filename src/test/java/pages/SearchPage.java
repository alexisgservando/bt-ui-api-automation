package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {

	// WEBDRIVER
    private final WebDriver driver;

    // LOCATORS
    private final By searchBox = By.name("q");
    private final By searchButton = By.name("btnK");

    // CONSTRUCTOR
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // METHODS
    public void goToGoogle() {
        driver.get(utils.ConfigReader.get("google.url"));
    }

    public ResultsPage searchFor(String searchValue) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(searchValue);

        try {
            driver.findElement(searchButton).click();
        } catch (Exception e) {
            driver.findElement(searchBox).sendKeys(Keys.ENTER);
        }

        return new ResultsPage(driver);
    }
}
