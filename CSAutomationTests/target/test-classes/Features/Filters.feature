Feature: Validating Filters option

  @smoke @filters
  Scenario: Verify that user can apply filters to reduce the results
    Given I am on the Coin Market Cap Home Page
    When I select Full List of Coins only from crypto currencies DropDown
    Then I should see the results of coins only Currencies
    When I apply some filters to modify the results
    Then I should see the results as per the filters
