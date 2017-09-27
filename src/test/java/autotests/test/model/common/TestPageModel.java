package autotests.test.model.common;

import autotests.test.pageobjects.TestPageObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.MatcherAssert;
import static autotests.test.step_definitions.Hooks.driver;

public class TestPageModel {

    public void enterSearchText(String Text)
    {
        TestPageObject.googleSearchBar.sendKeys("adasdasdadasd");
    }

    public void checkCurrentURl()
    {
      System.out.println(driver.getCurrentUrl());
    }
}
