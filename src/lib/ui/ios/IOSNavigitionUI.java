package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigitionUI extends NavigationUI {
        static {
            MY_LISTS_LINK = "id:Saved";
        }

        public IOSNavigitionUI(AppiumDriver driver)
        {
            super(driver);
        }
}
