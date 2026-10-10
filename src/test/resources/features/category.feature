@category
Feature: Category Verification
  As a user
  I want to browse products by category across multiple pages
  So that I can see the whole product offer of a category

  @TS-07 @smoke
  Scenario: Category page opened from the top menu shows its title, breadcrumb and products
    Given the user is on the home page
    When the user opens the "Apparel & Shoes" category from the top menu
    Then the category page title should be "Apparel & Shoes"
    And the breadcrumb should end with "Apparel & Shoes"

  @TS-08 @smoke
  Scenario Outline: Pagination navigates to page 2 using "<pagerControl>" and back to page 1
    Given the user is on page 1 of the "Apparel & Shoes" category
    When the user clicks the "<pagerControl>" pager link
    Then page 2 should be the current page
    When the user goes back to page 1
    Then page 1 should be the current page

    Examples:
      | pagerControl |
      | Next         |
      | 2            |

  @TS-09
  Scenario Outline: Products are displayed on page <pageNumber> of the "Apparel & Shoes" category
    Given the user is on page <pageNumber> of the "Apparel & Shoes" category
    Then at least one product should be displayed on the category page

    Examples:
      | pageNumber |
      | 1          |
      | 2          |
