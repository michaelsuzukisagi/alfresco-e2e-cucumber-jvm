Feature: Login

Scenario: Login as admin
    Given I am on login page
    When login as "admin" with password "password"
    Then i should see the dashboard page
