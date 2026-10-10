@sorting
Feature: Product Sorting
  As a user
  I want to sort the products in a category
  So that I can find products in the order that suits me

  @TS-10
  Scenario: Products are sorted by "Price: Low to High" across all pages
    Given the user is on the "Apparel & Shoes" category page
    When the user sorts the products by "Price: Low to High"
    Then "Price: Low to High" should be the selected sort option
    And the product prices should be in ascending order across all pages

