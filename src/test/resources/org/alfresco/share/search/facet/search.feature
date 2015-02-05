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
    Given I am viewing faceted search results
    When I click on "Microsoft Word" facet
    Then the search results are filtered to show only those with the appropriate facet.
    
Scenario: Display facets correctly
    Given I am viewing faceted search results
    When I look in the facet panel I should see facet categories "Size, Creator,File Type,Modifier"
    And those facets have accurate counts:
    |Microsoft Word|3|
    |Microsoft PowerPoint|2|
    |Adobe PDF Document|1|
    |Alice Beecher|1|
    |Mike Jackson|5|
    |Administrator|1|
    |Mike Jackson|5|
    |10 to 100KB|3|
    |100KB to 1MB|1|
    |1 to 16MB|2|