package com.codeTribeDemoWebShop.stepdefinitions;

import com.codeTribeDemoWebShop.pages.CategoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingSteps {

    @When("the user sorts the products by {string}")
    public void theUserSortsTheProductsBy(String sortOption) {
        new CategoryPage().sortBy(sortOption);
    }

    @Then("{string} should be the selected sort option")
    public void shouldBeTheSelectedSortOption(String sortOption) {
        Assert.assertEquals(new CategoryPage().getSelectedSortOption(), sortOption, "Unexpected selected sort option");
    }

    @Then("the product prices should be in ascending order across all pages")
    public void theProductPricesShouldBeInAscendingOrderAcrossAllPages() {
        CategoryPage categoryPage = new CategoryPage();
        List<Double> prices = new ArrayList<>();
        boolean hasNextPage;
        do {
            for (String priceText : categoryPage.productGrid().getProductPrices()) {
                prices.add(Double.parseDouble(priceText));
            }
            hasNextPage = categoryPage.hasNextPage();
            if (hasNextPage) {
                categoryPage.goToNextPage();
            }
        } while (hasNextPage);

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(prices, sortedPrices, "Product prices are not in ascending order across all pages");
    }
}
