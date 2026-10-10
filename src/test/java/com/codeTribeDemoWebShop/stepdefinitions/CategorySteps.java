package com.codeTribeDemoWebShop.stepdefinitions;

import com.codeTribeDemoWebShop.pages.CategoryPage;
import com.codeTribeDemoWebShop.pages.components.Header;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CategorySteps {

    @Given("the user is on the {string} category page")
    public void theUserIsOnTheCategoryPage(String categoryName) {
        new Header().openCategoryFromTopMenu(categoryName);
    }

    @Given("the user is on page {int} of the {string} category")
    public void theUserIsOnPageOfTheCategory(int pageNumber, String categoryName) {
        CategoryPage categoryPage = new Header().openCategoryFromTopMenu(categoryName);
        if (categoryPage.getCurrentPageNumber() != pageNumber) {
            categoryPage.goToPage(pageNumber);
        }
    }

    @When("the user opens the {string} category from the top menu")
    public void theUserOpensTheCategoryFromTheTopMenu(String categoryName) {
        new Header().openCategoryFromTopMenu(categoryName);
    }

    @When("the user clicks the {string} pager link")
    public void theUserClicksThePagerLink(String pagerLink) {
        new CategoryPage().clickPagerLink(pagerLink);
    }

    @When("the user goes back to page {int}")
    public void theUserGoesBackToPage(int pageNumber) {
        new CategoryPage().goToPage(pageNumber);
    }

    @Then("the category page title should be {string}")
    public void theCategoryPageTitleShouldBe(String expectedTitle) {
        Assert.assertEquals(new CategoryPage().getPageTitle(), expectedTitle, "Unexpected category page title");
    }

    @Then("the breadcrumb should end with {string}")
    public void theBreadcrumbShouldEndWith(String expectedItem) {
        Assert.assertEquals(new CategoryPage().getLastBreadcrumbItem(), expectedItem, "Unexpected last breadcrumb item");
    }

    @Then("at least one product should be displayed on the category page")
    public void atLeastOneProductShouldBeDisplayedOnTheCategoryPage() {
        Assert.assertTrue(new CategoryPage().productGrid().getProductCount() > 0,
                "No products are displayed on the category page");
    }

    @Then("page {int} should be the current page")
    public void pageShouldBeTheCurrentPage(int pageNumber) {
        Assert.assertEquals(new CategoryPage().getCurrentPageNumber(), pageNumber, "Unexpected current page");
    }
}
