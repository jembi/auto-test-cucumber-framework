package autotests.test.model.common;

import autotests.test.helpers.SharedStorage;

public class CommonModel {

    public void addTestCaseIdsToSharedStorage(String suite, String testCaseId) {
        SharedStorage.getInstance().addData("suite", suite);
        SharedStorage.getInstance().addData("test-case-id", testCaseId);
    }
}
