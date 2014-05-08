Feature: Calculator

Scenario: Adding numbers
    Given I have a calculator
    When I add 2 and 4
    Then outcome should be 6

Scenario: Subtracting numbers
    Given I have a calculator
    When I subtract 4 from 2
    Then outcome should be 2
