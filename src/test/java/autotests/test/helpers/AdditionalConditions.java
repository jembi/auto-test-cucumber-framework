package autotests.test.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdditionalConditions {

    private static String script = "var callback = arguments[arguments.length - 1];\n" +
            "var rootSelector = \'body\';\n" +
            "var el = document.querySelector(rootSelector);\n" +
            "\n" +
            "try {\n" +
            "    if (ng) {\n" +
            "        window.getTestability(el).whenStable(callback);\n" +
            "    }\n" +
            "    else {\n" +
            "        callback();\n" +
            "    }\n" +
            "} catch (err) {\n" +
            "    callback(err.message);\n" +
            "}";

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
                        "return (window.angular !== undefined) &&" +
                                " (angular.element(document).injector() !== undefined) &&" +
                                " (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString())

                        ||

                        //TODO expand on this to get it to work with angular 4
                        Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
                                "return (window.ng !== undefined)").toString());
            }
        };
    }

    public static void waitOnAngular(WebDriver driver){
//        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
//        wait.until(AdditionalConditions.angularHasFinishedProcessing());
        ((JavascriptExecutor) driver).executeAsyncScript(script, new Object[0]);

    }
}