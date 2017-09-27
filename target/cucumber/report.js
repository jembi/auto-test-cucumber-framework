$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/test/test.feature");
formatter.feature({
  "line": 1,
  "name": "Test log in to google.com",
  "description": "",
  "id": "test-log-in-to-google.com",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2451691,
  "status": "passed"
});
formatter.before({
  "duration": 3828047228,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Check the landing page of google",
  "description": "",
  "id": "test-log-in-to-google.com;check-the-landing-page-of-google",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I add the test case IDs to the shared storage for test rail reporting",
  "rows": [
    {
      "cells": [
        "project",
        "suite",
        "section",
        "test-case-id"
      ],
      "line": 5
    },
    {
      "cells": [
        "Test Project",
        "Master",
        "Auto Test Case",
        "443"
      ],
      "line": 6
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I navigate to the google landing page",
  "rows": [
    {
      "cells": [
        "landing page"
      ],
      "line": 8
    },
    {
      "cells": [
        "www.google.com"
      ],
      "line": 9
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I enter text \"Test works !\" to search",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.i_add_the_test_case_IDs_to_the_shared_storage_for_test_rail_reporting(DataTable)"
});
formatter.result({
  "duration": 85670973,
  "status": "passed"
});
formatter.match({
  "location": "TestStepDefinition.i_navigate_to_the_google_landing_page(DataTable)"
});
formatter.result({
  "duration": 6621678156,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test works !",
      "offset": 14
    }
  ],
  "location": "TestStepDefinition.i_enter_text_to_search(String)"
});
formatter.result({
  "duration": 17587045,
  "status": "passed"
});
formatter.after({
  "duration": 72206,
  "status": "passed"
});
formatter.after({
  "duration": 2891604116,
  "status": "passed"
});
formatter.after({
  "duration": 584629652,
  "status": "passed"
});
});