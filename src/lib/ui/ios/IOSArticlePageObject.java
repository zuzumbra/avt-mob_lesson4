package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        POPUP_CLOSE_BUTTON = "id:places auth close";
/*        CONTENT_TABLE_BUTTON = "id:Table of contents";
        CONTENT_TABLE_ELEMENT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";*/
    }

    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
