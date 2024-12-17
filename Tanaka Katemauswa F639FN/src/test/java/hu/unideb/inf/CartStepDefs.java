package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartStepDefs extends AbstractStepDefs {

    @Given("multiple items are added to the cart")
    public void addMultipleItemsToCart() {
        homePage.addItemToCart("Sauce Labs Backpack");
        homePage.addItemToCart("Sauce Labs Bolt T-Shirt");
    }

    @Given("items are added to the cart")
    public void addItemsToCart() {
        homePage.addItemToCart("Sauce Labs Backpack");
    }

    @When("the {string} button is clicked for an item")
    public void clickItemButton(String buttonName) {
        homePage.clickItemButton(buttonName);
    }

    @When("the user logs out")
    public void userLogsOut() {
        homePage.logout();
    }

    @When("logs back in with the same credentials")
    public void loginWithSameCredentials() {
        homePage.login();
    }

    @Then("the item should be removed from the cart")
    public void verifyItemRemoved() {
        assertTrue(homePage.isItemRemovedFromCart());
    }

    @Then("the cart count should decrease")
    public void verifyCartCountDecreased() {
        assertTrue(homePage.hasCartCountDecreased());
    }

    @Then("the cart should be empty")
    public void verifyCartEmpty() {
        assertEquals(0, homePage.getCartCount());
    }

    @Then("the inventory page should be displayed")
    public void verifyInventoryPageDisplayed() {
        assertTrue(homePage.isInventoryPageDisplayed());
    }

    @Then("the cart items should be preserved")
    public void verifyCartItemsPreserved() {
        assertTrue(homePage.areCartItemsPreserved());
    }
}
