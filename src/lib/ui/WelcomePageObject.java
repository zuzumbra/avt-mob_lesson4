package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEXT_BUTTON = "id:Next",
            STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFFERED_LANGUAGES ="id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
            STEP_GET_STARTED = "id:Get started",
            SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(STEP_NEXT_BUTTON, "Cannot find and click 'Next' link", 10);
    }

    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLanguages()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFFERED_LANGUAGES, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollected()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(STEP_GET_STARTED, "Cannot find and click 'Get started' link", 10);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 10);
    }

}

