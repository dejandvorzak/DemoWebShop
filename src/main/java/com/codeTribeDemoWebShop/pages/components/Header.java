package com.codeTribeDemoWebShop.pages.components;

import com.codeTribeDemoWebShop.pages.BasePage;
import com.codeTribeDemoWebShop.pages.CategoryPage;
import com.codeTribeDemoWebShop.pages.SearchResultsPage;
import org.openqa.selenium.By;

public class Header extends BasePage {

    private static final String TOP_MENU_CATEGORY_XPATH = "//ul[@class='top-menu']/li/a[normalize-space()='%s']";

    private final By searchInput = By.id("small-searchterms");
    private final By searchButton = By.cssSelector("input.search-box-button");

    public SearchResultsPage searchFor(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
        return new SearchResultsPage().waitUntilLoaded();
    }

    public CategoryPage openCategoryFromTopMenu(String categoryName) {
        click(By.xpath(String.format(TOP_MENU_CATEGORY_XPATH, categoryName)));
        return new CategoryPage().waitUntilLoaded();
    }
}
