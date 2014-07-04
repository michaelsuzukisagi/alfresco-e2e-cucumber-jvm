Feature: Faceted search
Search should display results and facets
Empty search should not display results and facets

Scenario: Search
    Given I am a user 
    When I search for "ipsum"
    Then I should see search results
    And There should be a count of the number of results
    And the results should show the correct data
    And Clicking on a result takes me to the document details page for that node.
    
Scenario: Selecting facet from search
    Given I am a user 
    When I search for "ipsum"
    And I am viewing faceted search results
    When I click on "Microsoft Word" facet
    Then the search results are filtered to show only those with the appropriate facet.