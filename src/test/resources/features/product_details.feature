@productDetails
Feature: Product Details Page
  As a user
  I want the product information to be consistent between the product list and the product details page
  So that I can trust what I see before adding a product to the cart

  Background:
    Given the user is on the home page

  @TS-04 @smoke
  Scenario Outline: Product title and price match between the search results and the details page for "<keyword>"
    When the user searches for "<keyword>"
    And the user opens the first product from the search results
    Then the product title on the details page should match the selected product
    And the product price on the details page should match the selected product
    And the "Add to cart" button should be displayed for the selected product in the search results and on the details page

    Examples:
      | keyword  |
      | computer |
      | diamond  |

  @TS-05 @smoke
  Scenario Outline: (IF FAILED: Known defect - 2) In-stock products from the search results for "<keyword>" display an "Add to cart" button on the details page
    When the user searches for "<keyword>"
    Then every in-stock product from the search results should display an "Add to cart" button on the details page

    Examples:
      | keyword   |
      | camcorder |
      | camera    |
      | computer  |
      | shoe      |

  @TS-06
  Scenario Outline: Product without an "Add to cart" button in the search results for "<keyword>" is shown as out of stock
    When the user searches for "<keyword>"
    And the user opens the first product from the search results that has no "Add to cart" button
    Then the product availability should be "Out of stock"
    And the "Add to cart" button should not be displayed on the product details page

    Examples:
      | keyword |
      | shoe    |
      | diamond |
