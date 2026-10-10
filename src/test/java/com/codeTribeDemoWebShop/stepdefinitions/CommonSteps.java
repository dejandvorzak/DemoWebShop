package com.codeTribeDemoWebShop.stepdefinitions;

import com.codeTribeDemoWebShop.pages.HomePage;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public class CommonSteps {

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        Assert.assertTrue(new HomePage().isDisplayed(), "Home page is not displayed");
    }
}
