package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    private By backpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartButton = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addTwoProductsToCart() {
        driver.findElement(backpackButton).click();
        driver.findElement(bikeLightButton).click();
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }
}