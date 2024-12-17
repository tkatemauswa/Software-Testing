package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.sql.SQLException;
import hu.unideb.inf.BrowserManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingStepDefs extends AbstractStepDefs {

    @Given("a valid user is logged in")
    public void a_valid_user_is_logged_in() throws SQLException {
        HomePage homePage = new HomePage(BrowserManager.getDriver());
        homePage.openPage();
        homePage.login();
    }

    @When("the sort dropdown is set to {string}")
    public void selectSortDropdown(String sortOption) {
        homePage.selectSortOption(sortOption);
    }

    @Then("the products should be displayed in ascending price order")
    public void verifyAscendingPriceOrder() {
        assertTrue(homePage.isProductListSortedByPriceAscending());
    }

    @Then("the products should be displayed in descending price order")
    public void verifyDescendingPriceOrder() {
        assertTrue(homePage.isProductListSortedByPriceDescending());
    }

    @Then("the products should be displayed in alphabetical order")
    public void verifyAlphabeticOrder() {
        assertTrue(homePage.isProductListSortedAlphabetically());
    }

    }


