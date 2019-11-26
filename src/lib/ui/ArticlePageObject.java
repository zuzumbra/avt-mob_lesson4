package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.MyListsPageObjectFactory;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_CHANGE_LANGUAGE,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            POPUP_CLOSE_BUTTON;
/*            CONTENT_TABLE_BUTTON,
            CONTENT_TABLE_ELEMENT_BY_SUBSTRING_TPL,
            CONTENT_TABLE;*/


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

   /* private static String getResultContentTableElement(String substring)
    {
        return CONTENT_TABLE_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForContentTableElement(String substring)
    {
        String SearchResultXpath = getResultContentTableElement(substring);
        return this.waitForElementPresent(SearchResultXpath, "Cannot find content table element " + substring);
    }

    public void clickContentTableElement(String substring)
    {
        String SearchResultXpath = getResultContentTableElement(substring);
        this.waitForElementAndClick(SearchResultXpath, "Cannot find and click content table element " + substring, 10);
}

    public String getContentTableElement(String substring)
    {
        WebElement title_element = waitForContentTableElement(substring);
        if (Platform.getInstance().isAndroid())
        {
            this.waitForElementPresent(CONTENT_TABLE, "Cannot find parent element of search element " + substring);
            return title_element.getAttribute("text");
        }
        else
        {
            return title_element.getAttribute("name");
        }
    }*/

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid())
        {
            return title_element.getAttribute("text");
        }
        else
        {
            return title_element.getAttribute("name");
        }
    }


/*    public void clickContentTable()
    {
        this.waitForElementPresent(ACTIONS_TAB,"Cannot find actions  tab element", 5);
        this.waitForElementAndClick(CONTENT_TABLE_BUTTON, "Cannot find and click content table button", 5);
    }

    public WebElement getSearchContentTableElement(String content_table_element)
    {
        return this.waitForElementPresent(content_table_element, "Cannot find element " + content_table_element +  " in table of content", 15);
    }*/

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article", 100);
        }
    }


    public void addArticleToMyListToNewFolder(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_CHANGE_LANGUAGE,
                "Cannot find button Change language",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button 'Got it'",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                0
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button",
                5
        );
    }

    public void addArticleToMyListToSavedFolder(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_CHANGE_LANGUAGE,
                "Cannot find option to add article to reading list",
                5
        );


        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

       MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
       myListsPageObject.openFolderByName(name_of_folder);
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void closePopUp()
    {
        this.waitForElementAndClick(
                POPUP_CLOSE_BUTTON,
                "Cannot close PopUp",
                5
                );
    }

    public void assertTitleIsFound()
    {
        this.assertElementPresent(TITLE,"We found any results by request"
        );
    }




}
