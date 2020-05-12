Feature: Watchlist functionality in Coin Market Cap

  @smoke @watchList
  Scenario: Verify that user can add crypto currencies to the watchlist
    Given I am on the Coin Market Cap Home Page
    When I select some currencies and add them to WatchList
    And I open Watchlist
    Then I should see all the currencies added to the watchlist
