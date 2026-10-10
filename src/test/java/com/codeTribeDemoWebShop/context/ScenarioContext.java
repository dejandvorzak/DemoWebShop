package com.codeTribeDemoWebShop.context;

import io.cucumber.java.Scenario;

public class ScenarioContext {

    private Scenario scenario;
    private String selectedProductName;
    private String selectedProductPrice;
    private boolean selectedProductHasAddToCartButton;

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public String getSelectedProductPrice() {
        return selectedProductPrice;
    }

    public void setSelectedProductPrice(String selectedProductPrice) {
        this.selectedProductPrice = selectedProductPrice;
    }

    public boolean selectedProductHasAddToCartButton() {
        return selectedProductHasAddToCartButton;
    }

    public void setSelectedProductHasAddToCartButton(boolean selectedProductHasAddToCartButton) {
        this.selectedProductHasAddToCartButton = selectedProductHasAddToCartButton;
    }
}
