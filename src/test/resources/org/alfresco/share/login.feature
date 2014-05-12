Feature: Login

Scenario: Login as admin
    Given I am admin
    When I navigate to alfresco
    And I login as "admin" with password "admin"
    Then i should see the dashboard page

Scenario: Login as admin with incorrect password
    Given I am admin
    When I navigate to alfresco
    And I login as "admin" with password "fake"
    Then i should see the login page