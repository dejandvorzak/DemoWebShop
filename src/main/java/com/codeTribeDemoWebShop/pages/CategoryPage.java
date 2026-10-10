package com.codeTribeDemoWebShop.pages;

import com.codeTribeDemoWebShop.pages.components.ProductGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CategoryPage extends BasePage {

    private static final String PAGER_LINK_XPATH = "//div[@class='pager']//a[normalize-space()='%s']";

    private final By categoryPageContainer = By.cssSelector("div.page.category-page");
    private final By pageTitle = By.cssSelector("div.category-page div.page-title h1");
    private final By breadcrumbCurrentItem = By.cssSelector("div.breadcrumb strong.current-item");
    private final By currentPage = By.cssSelector("div.pager li.current-page span");
    private final By nextPageLink = By.cssSelector("div.pager li.next-page a");
    private final By sortBySelect = By.id("products-orderby");

    public CategoryPage waitUntilLoaded() {
        waitForVisible(categoryPageContainer);
        return this;
    }

    public ProductGrid productGrid() {
        return new ProductGrid();
    }

    public String getPageTitle() {
        return getText(pageTitle).trim();
    }

    public String getLastBreadcrumbItem() {
        return waitForVisible(breadcrumbCurrentItem).getDomProperty("textContent").trim();
    }

    public CategoryPage goToPage(int pageNumber) {
        return clickPagerLink(String.valueOf(pageNumber));
    }

    public CategoryPage clickPagerLink(String linkText) {
        clickAndWaitForReload(By.xpath(String.format(PAGER_LINK_XPATH, linkText)));
        return this;
    }

    public boolean hasNextPage() {
        return !driver.findElements(nextPageLink).isEmpty();
    }

    public CategoryPage goToNextPage() {
        clickAndWaitForReload(nextPageLink);
        return this;
    }

    public int getCurrentPageNumber() {
        if (driver.findElements(currentPage).isEmpty()) {
            return 1;
        }
        return Integer.parseInt(getText(currentPage).trim());
    }

    public CategoryPage sortBy(String sortOption) {
        WebElement select = waitForVisible(sortBySelect);
        new Select(select).selectByVisibleText(sortOption);
        wait.until(ExpectedConditions.stalenessOf(select));
        return this;
    }

    public String getSelectedSortOption() {
        return new Select(waitForVisible(sortBySelect)).getFirstSelectedOption().getText().trim();
    }

    private void clickAndWaitForReload(By locator) {
        WebElement element = waitForClickable(locator);
        element.click();
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}
