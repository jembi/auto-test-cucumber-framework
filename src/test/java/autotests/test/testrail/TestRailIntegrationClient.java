package autotests.test.testrail;

import autotests.test.helpers.PropertiesUtil;
import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;

import java.util.Date;
import java.util.List;

public class TestRailIntegrationClient {

    private static TestRailIntegrationClient myself = new TestRailIntegrationClient();

    //Projects
    private static final int AUTOMATION_PROJECT_ID = 8;

    //Suites
    private static final int AUTOMATION_SUITE_ID = 14;


    //Section
    private static final int AUTOMATION_SECTION = 14;


    private static final int AUTOMATION_CASE = 31;

    //Test Case Status IDs
    private static final int TEST_FAILED_STATUS_ID = 5;
    private static final int TEST_PASSED_STATUS_ID = 1;

    //Test Rail Authorization
    private static final String TEST_RAIL_USERNAME = "jono.gebers@jembi.org";
    private static final String TEST_RAIL_PASSWORD = "jembi123";

    TestRail.Cases cases;
    TestRail testRail;
    Project project;
    TestRail.Suites suites;
    TestRail.Sections sections;
    Run genericTestRun;

    List<ResultField> customResultFields;

    private TestRailIntegrationClient() {
        this.testRail = TestRail.builder("https://jembi.testrail.net", TEST_RAIL_USERNAME, TEST_RAIL_PASSWORD).applicationName("playground").build();
        this.project = testRail.projects().get(AUTOMATION_PROJECT_ID).execute();
        this.suites = testRail.suites();
        this.sections = testRail.sections();
        this.cases = testRail.cases();
        this.genericTestRun = testRail.runs().add(project.getId(), new Run().setSuiteId(AUTOMATION_SUITE_ID).setName("Auto-test Test Run : Env = " + PropertiesUtil.getPropertyAsString("environment") + " - " + new Date().toString())).execute();

    }

    public static TestRailIntegrationClient getInstance() {
        if (myself == null) {
            myself = new TestRailIntegrationClient();
        }

        return myself;
    }

    private void addResultToRun(Integer testCaseId, boolean passed, Run run) {
        if (passed) {
            List<ResultField> customResultFields = testRail.resultFields().list().execute();
            testRail.results().addForCase(run.getId(), testCaseId, new Result().setStatusId(TEST_PASSED_STATUS_ID), customResultFields).execute();
        } else {
            List<ResultField> customResultFields = testRail.resultFields().list().execute();
            testRail.results().addForCase(run.getId(), testCaseId, new Result().setStatusId(TEST_FAILED_STATUS_ID), customResultFields).execute();
        }
    }

    public void addResultToSuite(String suite, Integer testCaseId, boolean passed) {

        switch (suite) {
            case "Master":
                addResultToRun(testCaseId, passed, genericTestRun);
                break;
                default: {
                System.out.println("Suite " + suite + " not recognised within Test Rail");
            }
        }
    }

    public void finishRun() {
        // close the run
        testRail.runs().close(genericTestRun.getId()).execute();

        // complete the project - supports partial updates
        testRail.projects().update(project.setCompleted(true)).execute();
    }


}
