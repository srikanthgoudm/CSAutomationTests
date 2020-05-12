package stepdefs;


import com.pages.HomePage;
import com.pages.WatchListPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefeinitionsFrontEnd {
	HomePage homePage;
	WatchListPage watchListPage;
	List<String> itemsAddedToWatchList;
	List<String> itemsInToWatchList;
	List<String> currenciesBeforeFilter;
	List<String> currenciesAfterFilter;

	@Given("I am on the Coin Market Cap Home Page")
	public void verifyUserOnHomePage() {
		homePage = new HomePage();
		homePage.isUserOnHomePage();
		homePage.clearPrompts();
	}

	@When("I click on ViewAll")
	public void clickViewAll() {
		homePage.click_ViewAll();
		homePage.verifyBackTo100isDisplayed();
	}

	@Then("I want to check \"([^\"]*)\" results are displayed")
	public void verifyResults(Integer int1) {
		homePage.verify100Currenies();
	}

	@When("I select some currencies and add them to WatchList")
	public void addCurrenciesToWatchList() {
		homePage.scrollDown("200");
		itemsAddedToWatchList = homePage.addCurrenciesToWatchList();
	}

	@When("I open Watchlist")
	public void openWatchlist() {
		watchListPage = homePage.clickWatchList();
	}

	@Then("I should see all the currencies added to the watchlist")
	public void verifyCurrenciesAreAdded() {
		itemsInToWatchList = watchListPage.validateWatchListItems();
		assertEquals(itemsAddedToWatchList, itemsInToWatchList);
	}


	@When("I select Full List of Coins only from crypto currencies DropDown")
	public void selectFullListOfCryptoCurrenciesDropDown() {
		homePage.clickCryptoCurrenciesDropDownAndSelectOption();
	}

	@Given("I should see the results of coins only Currencies")
	public void verifyCoinsOnlyResults() {
		System.out.println("<<----Currencies before Applying any Filters---->>");
		currenciesBeforeFilter = homePage.getAllCurrenciesDisplayed();
	}

	@When("I apply some filters to modify the results")
	public void applyFiltersAndViewResults() {
		homePage.applyFilters();
		System.out.println("<<----Currencies After Applying Filter---->>");
		currenciesAfterFilter = homePage.getAllCurrenciesDisplayed();
	}

	@Then("I should see the results as per the filters")
	public void compareResults() {

		currenciesBeforeFilter.retainAll(currenciesAfterFilter);
		for(String str: currenciesBeforeFilter) {
			System.out.println(str);
		}
	}




}
