package com.codeTribeDemoWebShop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {

    private final By productDetailsContainer = By.cssSelector("div.page.product-details-page");
    private final By productTitle = By.cssSelector("div.product-name h1[itemprop='name']");
    private final By productPrice = By.cssSelector("div.product-price span[itemprop='price']");
    private final By addToCartButton = By.cssSelector("div.product-essential input.add-to-cart-button");
    private final By availability = By.cssSelector("div.stock span.value");

    public ProductDetailsPage open(String productUrl) {
        driver.get(productUrl);
        return waitUntilLoaded();
    }

    public ProductDetailsPage waitUntilLoaded() {
        waitForVisible(productDetailsContainer);
        return this;
    }

    public boolean isDisplayed() {
        return isVisible(productDetailsContainer);
    }

    public String getProductTitle() {
        return getText(productTitle).trim();
    }

    public String getProductPrice() {
        return getText(productPrice).trim();
    }

    public boolean isAddToCartButtonDisplayed() {
        for (WebElement button : driver.findElements(addToCartButton)) {
            if (button.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    public String getAvailability() {
        return getText(availability).trim();
    }

    public boolean isInStock() {
        for (WebElement value : driver.findElements(availability)) {
            if (value.getText().trim().equals("In stock")) {
                return true;
            }
        }
        return false;
    }
}
