package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.ResultsPage;

public class SearchTest extends BaseTest {

	String searchValue = "Selenium";
	
    @Test
    public void validSearch() {
    	String searchValue = "Selenium";
        SearchPage search = new SearchPage(driver);
        search.goToGoogle();
        ResultsPage results = search.searchFor(searchValue);
        Assert.assertTrue(results.hasResults(), "Expected results to be visible");
    }

    @Test
    public void searchResultNavigation() {
        SearchPage search = new SearchPage(driver);
        search.goToGoogle();
        ResultsPage results = search.searchFor(searchValue);
        results.clickFirstResult();
        Assert.assertTrue(driver.getCurrentUrl().contains("selenium.dev"),
                "Expected to land on selenium.dev but was: " + driver.getCurrentUrl());
    }
}
