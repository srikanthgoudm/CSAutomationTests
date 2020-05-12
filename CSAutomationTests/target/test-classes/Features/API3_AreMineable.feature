Feature: To validate Cryptocurrencies are minable

  Scenario Outline: To retrive Cryptocurrency details, check minable tag and print its name
    Given I want to get the details of CryptoCurrency with "<ID>"
    When I get the details from info API
    Then I want to check whether CryptoCUrrency is minable or not and print the Cryptocurrency name

    Examples: 
      | ID |
      | 1  |
      | 2  |
      | 3  |
      | 4  |
      | 5  |
      | 6  |
      | 7  |
      | 8  |
      | 9  |
      | 10 |