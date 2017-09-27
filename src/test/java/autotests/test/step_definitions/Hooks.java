package autotests.test.step_definitions;

import autotests.test.helpers.PropertiesUtil;
import autotests.test.helpers.SharedStorage;
import autotests.test.testrail.TestRailIntegrationClient;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {
    public static WebDriver driver;
    //Lab Sauce User Details for Selenium Hub & Custom Hub
    public static final String USERNAME = "Username";
    public static final String ACCESS_KEY = "access key";
    public static final String SAUCE_LABS_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String HUB_URL = "automation hub url";
    public static PropertiesUtil propertiesUtil;

    public boolean useHub = false;
    public boolean useSauceLabsHub = false;

    @Before
    public void setupEnvironment() {
        String environment = System.getProperty("env.HOME");
        System.out.println("Environment from param = " + System.getProperty("env.HOME"));

        if (environment != null) {
            Hooks.propertiesUtil = new PropertiesUtil(System.getProperty("env.HOME"));
        } else {
            Hooks.propertiesUtil = new PropertiesUtil("local");
        }

        System.out.println("Properties test you are using: " + PropertiesUtil.getPropertyAsString("environment") + " properties");
    }

    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void openBrowser() throws MalformedURLException {
        String environment = propertiesUtil.getPropertyAsString("environment");

        String hub = System.getProperty("hub");

        if (hub != null) {
            System.out.println("HUB=" + hub);
            useHub = hub.equals("hubname");
            useSauceLabsHub = hub.equals("sauce-labs");
        }

        if (useHub) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL(HUB_URL), caps);
        } else if (useSauceLabsHub) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
            driver = new RemoteWebDriver(new URL(SAUCE_LABS_URL), caps);
            driver.manage().window().maximize();
            //Sauce labs reporting
            String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
                    (((RemoteWebDriver) driver).getSessionId()).toString(), System.getenv("JOB_NAME"));
            System.out.println(message);
        }

        if (environment != null) {
            if (environment.equals("local")) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
            }
        } else {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }

    }


    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }
        driver.quit();
    }

    @After
    public void afterSuccessfulScenario(Scenario scenario) {
        if (!scenario.isFailed()) {
            TestRailIntegrationClient.getInstance().addResultToSuite(
                    (String) SharedStorage.getInstance().getData("suite"),
                    Integer.valueOf((String) SharedStorage.getInstance().getData("test-case-id")),
                    true);
        }
    }

    @After
    public void afterFailedScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            TestRailIntegrationClient.getInstance().addResultToSuite(
                    (String) SharedStorage.getInstance().getData("suite"),
                    Integer.valueOf((String) SharedStorage.getInstance().getData("test-case-id")),
                    false);
        }
    }


}