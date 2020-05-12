Feature: To Test Price Conversion to Bolivian Boliviano

  Scenario Outline: To retrive Crypto converted Prices to Bolivian Boliviano
    Given I want to retrive the ID of "<Currency>" using map Api
    When I have the IDs I can request the Tools API to covert them to Bolivian Boliviano
    Then I want to print the Boliviano Value

    Examples:
      | Currency |
      | BTC      |
      | USDT     |
      | ETH      |