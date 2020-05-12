package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class HomePage extends BasePage {
    @FindBy(xpath = "(//a[text()='View All'])[1]")
    WebElement viewAll;

    @FindBys(@FindBy(xpath = "//div[text()='?']"))
    List<WebElement> closePromptsBtn;

    @FindBy(xpath = "//button[contains(@class,'openStateToggle')]")
    WebElement BTCprompt;

    @FindBy(xpath = "(//a[contains(text(),'Back to Top 100')])[1]")
    WebElement backTo100;

    @FindBys(@FindBy(xpath = "//*[@data-icon='ellipsis-h']"))
    List<WebElement> ellipsis;

    List<String> watchListItemNames;

    @FindBy(xpath = "//span[text()='Add to Watchlist']")
    WebElement addToWatchListLink;

    @FindBy(xpath = "//a[text()='Watchlist']")
    WebElement watchListLink;

    @FindBy(xpath = "(//span[text()='Cryptocurrencies'])[3]")
    WebElement cryptoCurrenciesDropDown;

    @FindBy(xpath = "//li[text()='All Cryptocurrencies']/following::a[text()='Full List'][1]")
    WebElement fullListInCryptoCurreciesDropDown;

    @FindBy(xpath = "//li[text()='All Cryptocurrencies']/following::a[text()='Full List'][2]")
    WebElement coinsOnlyFullListInCryptoCurreciesDropDown;

    @FindBy(xpath = "//li[text()='All Cryptocurrencies']/following::a[text()='Full List'][3]")
    WebElement tokensOnlyFullListInCryptoCurreciesDropDown;

    @FindBy(xpath = "//li[text()='All Cryptocurrencies']/following::a[text()='Market Cap by Circulating Supply'][1]")
    WebElement circulatingSupplyInCryptoCurreciesDropDown;

    @FindBy(xpath = "//button[text()='Filters']")
    WebElement filters;

    @FindBy(xpath = "//button[text()='Price']")
    WebElement priceFilter;

    @FindBy(xpath = "//h6[text()='Price range']/following::input[1]")
    WebElement lowerRangePrice;

    @FindBy(xpath = "//h6[text()='Price range']/following::input[2]")
    WebElement upperRangePrice;


    @FindBy(xpath = "//h6[text()='Circulating Supply range']/following::input[1]")
    WebElement lowerRangeCirculatingSupply;

    @FindBy(xpath = "//h6[text()='Circulating Supply range']/following::input[2]")
    WebElement upperRangeCirculatingSupply;

    @FindBy(xpath = "//h6[text()='Circulating Supply range']")
    WebElement circulatingSupply;

    @FindBy(xpath = "//h6[text()='Price range']")
    WebElement rangePrice;

    @FindBy(xpath = "//button[text()='Apply']")
    WebElement applyFilterBtn;

    @FindBy(xpath = "//button[text()='Circulating Supply']")
    WebElement circulatingSupplyFilter;

    public HomePage() {
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void isUserOnHomePage() {
        assertEquals(true, driver.getTitle().equalsIgnoreCase("Cryptocurrency Market Capitalizations | CoinMarketCap"));
    }

    public void click_ViewAll() {
        //Click on View All
        if (viewAll.isDisplayed()) {
            viewAll.click();

        } else {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            viewAll.click();
        }
    }

    public void clearPrompts() {

        for (int i = 0; i < closePromptsBtn.size(); i++) {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            closePromptsBtn.get(i).click();

        }
    }

    public void toggleBTCprompt() {
        if (BTCprompt.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            BTCprompt.click();
        }

    }

    public void verifyBackTo100isDisplayed() {
        if (backTo100.isDisplayed()) {
            System.out.println("PASS");

        }
    }

    public void verify100Currenies() {
        for (int i = 1; i <= 100; i++) {
            String text = driver.findElement(By.xpath("(//img[contains(@class,'cmc-static-icon cmc-static-icon-')])[" + i + "]/following::a[1]")).getText();
            System.out.println(i + ". " + text);

            this.scrollDown("50");
        }
    }

    public void scrollDown(String pixels) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public void scrollToTopOfThePage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public List<String> addCurrenciesToWatchList() {
        ellipsis.remove(0);
        watchListItemNames = new ArrayList<>();
        for (int i = 2; i <= 14; i = i + 2) {
            String text = driver.findElement(By.xpath("(//img[contains(@class,'cmc-static-icon cmc-static-icon-')])[" + i + "]/following::a[1]")).getText();


            ellipsis.get(i).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (addToWatchListLink.isDisplayed()) {
                addToWatchListLink.click();

                watchListItemNames.add(text);
            }
            this.scrollDown("150");
        }
        return watchListItemNames;
    }

    public WatchListPage clickWatchList() {
        this.scrollToTopOfThePage();
        //Mac OS
        watchListLink.sendKeys(Keys.COMMAND.toString() + Keys.ENTER.toString());
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        return new WatchListPage();
    }

    public void clickCryptoCurrenciesDropDownAndSelectOption() {
        this.clickCryptoCurrenciesDropDown();
        scrollDown("100");
        if (coinsOnlyFullListInCryptoCurreciesDropDown.isDisplayed()) {
            coinsOnlyFullListInCryptoCurreciesDropDown.click();
        }
    }

    public void clickCryptoCurrenciesDropDown() {
        if (cryptoCurrenciesDropDown.isDisplayed()) {
            cryptoCurrenciesDropDown.click();
        }
    }

    public List<String> getAllCurrenciesDisplayed() {
        List<String> currencies = new ArrayList<>();
        driver.manage().timeouts().implicitlyWait(160, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(By.xpath("(//img[contains(@class,'cmc-static-icon cmc-static-icon-')])"));
        for (int i = 1; i <= elements.size(); i++) {
            String text = driver.findElement(By.xpath("(//img[contains(@class,'cmc-static-icon cmc-static-icon-')])[" + i + "]/following::a[1]")).getText();
            System.out.println(i + ". " + text);
            currencies.add(text);
            System.out.println(text);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.scrollDown("65");
        }
        return currencies;
    }

    public void clickCryptoCurrenciesDropDownAndSelectCirculating() {
        this.scrollToTopOfThePage();
        this.scrollDown("200");
        this.clickCryptoCurrenciesDropDown();
        if (circulatingSupplyInCryptoCurreciesDropDown.isDisplayed()) {
            circulatingSupplyInCryptoCurreciesDropDown.click();
        }
    }

    public void applyFilters() {
        if (filters.isDisplayed()) {
            filters.click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            priceFilter.click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            if (rangePrice.isDisplayed()) {
                //lowerRangeCirculatingSupply.clear();
                lowerRangePrice.sendKeys("0");
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                //upperRangeCirculatingSupply.clear();
                upperRangePrice.sendKeys("1000000");
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                applyFilterBtn.click();
            }
            circulatingSupplyFilter.click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            if (circulatingSupply.isDisplayed()) {
                lowerRangeCirculatingSupply.clear();
                lowerRangeCirculatingSupply.sendKeys("0");
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                upperRangeCirculatingSupply.clear();
                upperRangeCirculatingSupply.sendKeys("10000000");
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                applyFilterBtn.click();
            }
        }
    }
}
