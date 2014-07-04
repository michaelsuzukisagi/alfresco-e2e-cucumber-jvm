Feature: Faceted search
Search should display results and facets
Empty search should not display results and facets

    
Scenario: Display facets correctly
    Given I am viewing faceted search results
    When I look in the facet panel I should see facet categories "File Type,Creator,Modifier,Size"
    And those facets have accurate counts

