package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']",
            SEARCH_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_DESCRIPTION = "//*[@resource-id='org.wikipedia:id/page_list_item_description']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private  static String getResultSearchElementByTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/


    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button",5);

    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present",5);

    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button",10);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking init element");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"Cannot find and type into search input", 5);
    }


    public void waitForSearchResult(String substring)
    {
        String SearchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(SearchResultXpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String SearchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(SearchResultXpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String SearchResultXpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(By.xpath(SearchResultXpath), "Cannot find search result with title " + title + " and description " +  description, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                5
        );

        return  this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),"Cannot find empty results label",5);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We supposed to not find any results"
        );
    }

    public void assertElementPresent()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We found any results by request"
        );
    }

    public void waitForTitleAndDescription()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elements1 = driver.findElements(By.xpath(SEARCH_TITLE));
        List<WebElement> elements2 = driver.findElements(By.xpath(SEARCH_DESCRIPTION));
        for (int i = 0; i < 3; i++) {
            String title = elements1.get(i).getText();
            String description = elements2.get(i).getText();
            waitForElementByTitleAndDescription(title, description);
        }
    }
}
