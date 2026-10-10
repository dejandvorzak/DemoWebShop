package com.codeTribeDemoWebShop.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By homePageContainer = By.cssSelector("div.page.home-page");

    public boolean isDisplayed() {
        return isVisible(homePageContainer);
    }
}
