package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "id:More options"; //"xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
        OPTIONS_CHANGE_LANGUAGE = "xpath://*[@text='Change language']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
/*        CONTENT_TABLE_BUTTON = "xpath://*[@content-desc='Table of Contents']";
        CONTENT_TABLE_ELEMENT_BY_SUBSTRING_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        ACTIONS_TAB = "id:org.wikipedia:id/page_actions_tab_layout";
        CONTENT_TABLE = "org.wikipedia:id/page_toc_list";*/
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
