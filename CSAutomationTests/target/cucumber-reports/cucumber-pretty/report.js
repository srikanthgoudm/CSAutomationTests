$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Filters.feature");
formatter.feature({
  "line": 1,
  "name": "Validating Filters option",
  "description": "",
  "id": "validating-filters-option",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4924344655,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Verify that user can apply filters to reduce the results",
  "description": "",
  "id": "validating-filters-option;verify-that-user-can-apply-filters-to-reduce-the-results",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@smoke"
    },
    {
      "line": 3,
      "name": "@filters"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I am on the Coin Market Cap Home Page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I select Full List of Coins only from crypto currencies DropDown",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I should see the results of coins only Currencies",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I apply some filters to modify the results",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I should see the results as per the filters",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefeinitionsFrontEnd.verifyUserOnHomePage()"
});
formatter.result({
  "duration": 100165149742,
  "status": "passed"
});
formatter.match({
  "location": "StepDefeinitionsFrontEnd.selectFullListOfCryptoCurrenciesDropDown()"
});
formatter.result({
  "duration": 462033736,
  "status": "passed"
});
formatter.match({
  "location": "StepDefeinitionsFrontEnd.verifyCoinsOnlyResults()"
});
formatter.result({
  "duration": 11944802928,
  "status": "passed"
});
formatter.match({
  "location": "StepDefeinitionsFrontEnd.applyFiltersAndViewResults()"
});
