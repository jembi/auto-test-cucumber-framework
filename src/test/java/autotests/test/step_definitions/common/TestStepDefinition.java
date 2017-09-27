package autotests.test.step_definitions.common;

import autotests.test.helpers.AdditionalConditions;
import autotests.test.helpers.PropertiesUtil;
import autotests.test.model.common.TestPageModel;
import autotests.test.step_definitions.Hooks;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.endsWith;

public class TestStepDefinition {

    private TestPageModel testPageModel = new TestPageModel();

    @When("^I navigate to the google landing page$")
    public void i_navigate_to_the_google_landing_page(DataTable arg1) throws Throwable {
        AdditionalConditions.waitOnAngular(Hooks.driver);
        System.out.println(PropertiesUtil.getPropertyAsString("test-url"));
        System.out.println(Hooks.driver);
        Hooks.driver.get(PropertiesUtil.getPropertyAsString("test-url"));
    }

    @When("^I enter text \"(.*?)\" to search$")
    public void i_enter_text_to_search(String search) throws Throwable {
        AdditionalConditions.waitOnAngular(Hooks.driver);
    //    testPageModel.enterSearchText(search);
       testPageModel.checkCurrentURl();
    }
}
