package com.codeTribeDemoWebShop.stepdefinitions;

import com.codeTribeDemoWebShop.context.ScenarioContext;
import com.codeTribeDemoWebShop.pages.ProductDetailsPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ProductDetailsSteps {

    private final ScenarioContext context;

    public ProductDetailsSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the product details page should be displayed")
    public void theProductDetailsPageShouldBeDisplayed() {
        Assert.assertTrue(new ProductDetailsPage().isDisplayed(), "Product details page is not displayed");
    }

    @Then("the product title on the details page should match the selected product")
    public void theProductTitleOnTheDetailsPageShouldMatchTheSelectedProduct() {
        Assert.assertEquals(new ProductDetailsPage().getProductTitle(), context.getSelectedProductName(),
                "Product title on the details page does not match the selected product");
    }

    @Then("the product price on the details page should match the selected product")
    public void theProductPriceOnTheDetailsPageShouldMatchTheSelectedProduct() {
        Assert.assertEquals(new ProductDetailsPage().getProductPrice(), context.getSelectedProductPrice(),
                "Product price on the details page does not match the selected product");
    }

    @Then("the \"Add to cart\" button should be displayed on the product details page")
    public void theAddToCartButtonShouldBeDisplayedOnTheProductDetailsPage() {
        Assert.assertTrue(new ProductDetailsPage().isAddToCartButtonDisplayed(),
                "\"Add to cart\" button is not displayed on the product details page");
    }

    @Then("the \"Add to cart\" button should be displayed for the selected product in the search results and on the details page")
    public void theAddToCartButtonShouldBeDisplayedInTheSearchResultsAndOnTheDetailsPage() {
        Assert.assertTrue(context.selectedProductHasAddToCartButton(),
                "\"Add to cart\" button is not displayed for the selected product in the search results");
        Assert.assertTrue(new ProductDetailsPage().isAddToCartButtonDisplayed(),
                "\"Add to cart\" button is not displayed on the product details page");
    }

    @Then("the \"Add to cart\" button should not be displayed on the product details page")
    public void theAddToCartButtonShouldNotBeDisplayedOnTheProductDetailsPage() {
        Assert.assertFalse(new ProductDetailsPage().isAddToCartButtonDisplayed(),
                "\"Add to cart\" button is displayed on the product details page");
    }

    @Then("the product availability should be {string}")
    public void theProductAvailabilityShouldBe(String availability) {
        Assert.assertEquals(new ProductDetailsPage().getAvailability(), availability,
                "Unexpected product availability");
    }
}
