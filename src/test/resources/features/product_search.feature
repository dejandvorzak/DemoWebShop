@search
Feature: Product Search
  As a user
  I want to search for products by keyword
  So that I can quickly find the products I am interested in

  Background:
    Given the user is on the home page

  @TS-01 @smoke
  Scenario Outline: Search returns only relevant products for "<keyword>" and prints the product names
    When the user searches for "<keyword>"
    Then at least one product should be displayed in the search results
    And every product name in the search results should contain "<keyword>"

    Examples:
      | keyword  |
      | computer |
      | shoe     |
      | diamond  |

  @TS-02
  Scenario Outline: Each search result for "<keyword>" displays a product name and a valid price
    When the user searches for "<keyword>"
    Then every product in the search results should display a product name
    And every product in the search results should display a valid price

    Examples:
      | keyword  |
      | computer |
      | shoe     |
      | diamond  |

  @TS-03
  Scenario: Product details page opens from the search results
    When the user searches for "book"
    And the user opens the first product from the search results
    Then the product details page should be displayed
