package hu.unideb.inf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HomePage {

    private static final String PAGE_URL = "https://www.saucedemo.com/";

    private final WebDriver driver;

    @FindBy(css = "#login_button_container > div > form > div.error-message-container.error > h3")
    private WebElement errorMessage;
    @FindBy(css = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
    private WebElement priceLabel;
    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;


    private static final Map<String, By> textFields = Map.of(
       "Username", By.id("user-name"),
       "Password", By.id("password"),
       "First Name", By.id("first-name"),
       "Last Name", By.id("last-name"),
       "Zip Code", By.id("postal-code")
    );

    private static final Map<String, By> itemButtons = Map.of(
       "Sauce Labs Backpack", By.id("add-to-cart-sauce-labs-backpack"),
       "Sauce Labs Bike Light", By.id("add-to-cart-sauce-labs-bike-light"),
       "Sauce Labs Bolt T-Shirt", By.id("add-to-cart-sauce-labs-bolt-t-shirt"),
       "Sauce Labs Fleece Jacket", By.id("add-to-cart-sauce-labs-fleece-jacket"),
       "Sauce Labs Onesie", By.id("add-to-cart-sauce-labs-onesie"),
       "Test.allTheThings() T-Shirt (Red)", By.id("add-to-cart-test.allthethings()-t-shirt-(red)")
    );

    private static final Map<String, By> navigationButtons = Map.of(
        "Login", By.id("login-button"),
        "Cart", By.className("shopping_cart_link"),
        "Checkout", By.id("checkout"),
        "Continue", By.id("continue")
    );

    private int previousCartCount;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void closePage() {
        driver.quit();
    }

    public void fillOutField(String field, String text) {
        driver.findElement(textFields.get(field)).sendKeys(text);
    }

    public void clickButton(String button) {
        driver.findElement(navigationButtons.get(button)).click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void addItemToCart(String item) {
        driver.findElement(itemButtons.get(item)).click();
    }

    public String getTotal() {return priceLabel.getText();

    }


    public void selectSortOption(String sortOption) {
        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText(sortOption);
    }

    public boolean isProductListSortedByPriceAscending() {
        List<Double> prices = getProductPrices();
        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }

    public boolean isProductListSortedByPriceDescending() {
        List<Double> prices = getProductPrices();
        List<Double> sortedPrices = prices.stream()
                .sorted((a,b) -> b.compareTo(a))
                .collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }

    public boolean isProductListSortedAlphabetically() {
        List<String> names = productNames.stream()
                .map(WebElement :: getText)
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());
        return names.equals(sortedNames);
    }

    private List<Double> getProductPrices() {
        return productPrices.stream()
                .map(element -> element.getText().replace("$", ""))
                .map(Double :: parseDouble)
                .collect(Collectors.toList());
    }

    public void clickItemButton(String buttonName) {
        previousCartCount = getCartCount();
        driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-backpack']")).click();
    }

    public boolean isItemRemovedFromCart() {
        return getCartCount() < previousCartCount;
    }

    public boolean hasCartCountDecreased() {
        return getCartCount() < previousCartCount;
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void logout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    public void login() {
        fillOutField("Username", "standard_user");
        fillOutField("Password", "secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    public boolean isInventoryPageDisplayed() {
        return driver.getCurrentUrl().endsWith("/inventory.html");
    }

    public boolean areCartItemsPreserved() {
        return getCartCount() > 0;
    }

}
