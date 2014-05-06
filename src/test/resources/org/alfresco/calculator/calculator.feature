Feature: Adding number

  Scenario: Adding 2 numbers
    Given I have a calculator
    When I add 2 and 4
    Then outcome should be 6

