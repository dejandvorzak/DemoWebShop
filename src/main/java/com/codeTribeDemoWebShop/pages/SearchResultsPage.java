package com.codeTribeDemoWebShop.pages;

import com.codeTribeDemoWebShop.pages.components.ProductGrid;
import org.openqa.selenium.By;

public class SearchResultsPage extends BasePage {

    private final By searchPageContainer = By.cssSelector("div.page.search-page");

    public SearchResultsPage waitUntilLoaded() {
        waitForVisible(searchPageContainer);
        return this;
    }

    public ProductGrid productGrid() {
        return new ProductGrid();
    }
}
