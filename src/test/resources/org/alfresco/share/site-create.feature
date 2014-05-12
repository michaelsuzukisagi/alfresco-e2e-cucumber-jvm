Feature: Create Site

Scenario: Create a site
    Given I am logged in as "admin" with password "admin"
    When I create a site "michaels-bdd"
    Then i should see the site dashboard page
