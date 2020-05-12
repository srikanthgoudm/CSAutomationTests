Feature: View all functionality in Coin Market Cap

  @smoke @viewAll
  Scenario: Verify that user can view all Crypto currencies wiht view all options
    Given I am on the Coin Market Cap Home Page
    When I click on ViewAll
    Then I want to check "100" results are displayed
