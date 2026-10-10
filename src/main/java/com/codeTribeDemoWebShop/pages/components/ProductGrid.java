package com.codeTribeDemoWebShop.pages.components;

import com.codeTribeDemoWebShop.pages.BasePage;
import com.codeTribeDemoWebShop.pages.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductGrid extends BasePage {

    private final By productItems = By.cssSelector("div.product-grid div.product-item");
    private final By productTitleLink = By.cssSelector("h2.product-title a");
    private final By productPrice = By.cssSelector("span.price.actual-price");
    private final By addToCartButton = By.cssSelector("input.product-box-add-to-cart-button");

    public int getProductCount() {
        return getProductItems().size();
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement item : getProductItems()) {
            productNames.add(item.findElement(productTitleLink).getText().trim());
        }
        return productNames;
    }

    public List<String> getProductPrices() {
        List<String> productPrices = new ArrayList<>();
        for (WebElement item : getProductItems()) {
            productPrices.add(item.findElement(productPrice).getText().trim());
        }
        return productPrices;
    }

    public List<String> getProductUrls() {
        List<String> productUrls = new ArrayList<>();
        for (WebElement item : getProductItems()) {
            productUrls.add(item.findElement(productTitleLink).getAttribute("href"));
        }
        return productUrls;
    }

    public String getProductNameAt(int index) {
        return getProductItem(index).findElement(productTitleLink).getText().trim();
    }

    public String getProductPriceAt(int index) {
        return getProductItem(index).findElement(productPrice).getText().trim();
    }

    public boolean hasAddToCartButtonAt(int index) {
        return !getProductItem(index).findElements(addToCartButton).isEmpty();
    }

    public int indexOfFirstProductWithoutAddToCartButton() {
        int count = getProductCount();
        for (int i = 0; i < count; i++) {
            if (!hasAddToCartButtonAt(i)) {
                return i;
            }
        }
        throw new IllegalStateException("Every product has an \"Add to cart\" button");
    }

    public ProductDetailsPage openProductAt(int index) {
        getProductItem(index).findElement(productTitleLink).click();
        return new ProductDetailsPage().waitUntilLoaded();
    }

    private List<WebElement> getProductItems() {
        return driver.findElements(productItems);
    }

    private WebElement getProductItem(int index) {
        return getProductItems().get(index);
    }
}
