package com.codeTribeDemoWebShop.stepdefinitions;

import com.codeTribeDemoWebShop.context.ScenarioContext;
import com.codeTribeDemoWebShop.pages.ProductDetailsPage;
import com.codeTribeDemoWebShop.pages.SearchResultsPage;
import com.codeTribeDemoWebShop.pages.components.Header;
import com.codeTribeDemoWebShop.pages.components.ProductGrid;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchSteps {

    private final ScenarioContext context;

    public SearchSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String keyword) {
        new Header().searchFor(keyword);
    }

    @When("the user opens the first product from the search results")
    public void theUserOpensTheFirstProductFromTheSearchResults() {
        openProductAt(0);
    }

    @When("the user opens the first product from the search results that has no \"Add to cart\" button")
    public void theUserOpensTheFirstProductWithoutAddToCartButton() {
        openProductAt(new SearchResultsPage().productGrid().indexOfFirstProductWithoutAddToCartButton());
    }

    @Then("at least one product should be displayed in the search results")
    public void atLeastOneProductShouldBeDisplayedInTheSearchResults() {
        Assert.assertTrue(new SearchResultsPage().productGrid().getProductCount() > 0, "No products are displayed in the search results");
    }

    @Then("every product name in the search results should contain {string}")
    public void everyProductNameInTheSearchResultsShouldContain(String keyword) {
        List<String> productNames = new SearchResultsPage().productGrid().getProductNames();
        context.getScenario().log("Products found for \"" + keyword + "\":\n" + String.join("\n", productNames));

        List<String> irrelevantProducts = new ArrayList<>();
        for (String name : productNames) {
            if (!name.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                irrelevantProducts.add(name);
            }
        }
        Assert.assertTrue(irrelevantProducts.isEmpty(),
                "Products not containing \"" + keyword + "\": " + irrelevantProducts);
    }

    @Then("every product in the search results should display a product name")
    public void everyProductInTheSearchResultsShouldDisplayAProductName() {
        List<String> productNames = new SearchResultsPage().productGrid().getProductNames();
        Assert.assertFalse(productNames.isEmpty(), "No products are displayed in the search results");
        for (String name : productNames) {
            Assert.assertFalse(name.isBlank(), "At least one product has no name: " + productNames);
        }
    }

    @Then("every product in the search results should display a valid price")
    public void everyProductInTheSearchResultsShouldDisplayAValidPrice() {
        List<String> productPrices = new SearchResultsPage().productGrid().getProductPrices();
        Assert.assertFalse(productPrices.isEmpty(), "No products are displayed in the search results");

        List<String> invalidPrices = new ArrayList<>();
        for (String price : productPrices) {
            if (!price.matches("(From )?\\d+\\.\\d{2}")) {
                invalidPrices.add(price);
            }
        }
        Assert.assertTrue(invalidPrices.isEmpty(), "Invalid prices: " + invalidPrices);
    }

    @Then("every in-stock product from the search results should display an \"Add to cart\" button on the details page")
    public void everyInStockProductFromTheSearchResultsShouldDisplayAnAddToCartButtonOnTheDetailsPage() {
        List<String> productUrls = new SearchResultsPage().productGrid().getProductUrls();
        Assert.assertFalse(productUrls.isEmpty(), "No products are displayed in the search results");

        List<String> inStockProductsWithoutAddToCartButton = new ArrayList<>();
        for (String productUrl : productUrls) {
            ProductDetailsPage productDetailsPage = new ProductDetailsPage().open(productUrl);
            if (productDetailsPage.isInStock() && !productDetailsPage.isAddToCartButtonDisplayed()) {
                inStockProductsWithoutAddToCartButton.add(productDetailsPage.getProductTitle());
            }
        }

        Assert.assertTrue(inStockProductsWithoutAddToCartButton.isEmpty(),
                "In-stock products without an \"Add to cart\" button on the details page: "
                        + inStockProductsWithoutAddToCartButton);
    }

    private void openProductAt(int index) {
        ProductGrid productGrid = new SearchResultsPage().productGrid();
        context.setSelectedProductName(productGrid.getProductNameAt(index));
        context.setSelectedProductPrice(productGrid.getProductPriceAt(index));
        context.setSelectedProductHasAddToCartButton(productGrid.hasAddToCartButtonAt(index));
        productGrid.openProductAt(index);
    }
}
