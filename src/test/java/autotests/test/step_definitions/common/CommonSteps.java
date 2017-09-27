package autotests.test.step_definitions.common;

import autotests.test.model.common.CommonModel;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class CommonSteps {

    private CommonModel commonModel = new CommonModel();

    @Given("^I add the test case IDs to the shared storage for test rail reporting$")
    public void i_add_the_test_case_IDs_to_the_shared_storage_for_test_rail_reporting(DataTable dataTable) throws Throwable {
        String suite = dataTable.asMaps(String.class, String.class).get(0).get("suite");
        String testCaseId = dataTable.asMaps(String.class, String.class).get(0).get("test-case-id");
        commonModel.addTestCaseIdsToSharedStorage(suite,testCaseId);
    }
}
