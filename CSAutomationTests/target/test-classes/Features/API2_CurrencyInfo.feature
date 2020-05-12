Feature: To validate Cryptocurrency details

  Scenario Outline: To retrive Cryptocurrency details
    Given I want to retrive the ID of "<Currency>" using map Api
    When I have the IDs I can request the info API to retrive the currency details
    Then I want to validate the CryptoCurrency Details

    Examples: 
      | Currency |
      | ETH      |
