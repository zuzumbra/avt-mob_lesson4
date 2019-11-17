package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";


    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();

    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(2);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.1");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/user/Projects/avt-mob_iOS/avt-mob_lesson4/apks/org.wikipedia.apk");
        }

        else if (platform.equals(PLATFORM_IOS))
        {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 7 Plus");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("app", "/Users/user/Projects/avt-mob_iOS/avt-mob_lesson4/apks/Wikipedia.app");
        }

        else
        {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;

    }

    private AppiumDriver getDriver() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        URL URL = new URL(APPIUM_URL);
        if (platform.equals(PLATFORM_ANDROID))
        {
            return new AndroidDriver(URL, getCapabilitiesByPlatformEnv());
        }
        else if (platform.equals(PLATFORM_IOS))
        {
            return new IOSDriver(URL, getCapabilitiesByPlatformEnv());
        }
        else
        {
            throw new Exception("Cannot get driver. Platform value " + platform);
        }

    }

}

