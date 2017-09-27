Feature: Test log in to google.com

  Scenario: Check the landing page of google
    Given I add the test case IDs to the shared storage for test rail reporting
      | project      | suite  | section        | test-case-id |
      | Test Project | Master | Auto Test Case | 443          |
    When I navigate to the google landing page
      | landing page   |
      | www.google.com |
    And I enter text "Test works !" to search