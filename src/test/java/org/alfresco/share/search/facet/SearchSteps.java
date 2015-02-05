/*
 * Copyright (C) 2005-2015 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

package org.alfresco.share.search.facet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.po.share.SharePage;
import org.alfresco.po.share.search.FacetedSearchFacetGroup;
import org.alfresco.po.share.search.FacetedSearchFacetGroup.FacetedSearchFacet;
import org.alfresco.po.share.search.FacetedSearchPage;
import org.alfresco.po.share.search.SearchResult;
import org.alfresco.po.share.site.document.DocumentDetailsPage;
import org.alfresco.share.StepsUtil;
import org.alfresco.webdrone.WebDrone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
/**
 * Steps to enable search feature.
 * 
 * @author Michael Suzuki
 *
 */
public class SearchSteps
{
    private WebDrone drone;
    private FacetedSearchPage resultPage;
    private List<FacetedSearchFacetGroup> facetGroups;
    ApplicationContext ctx;
    
    @Before
    public void beforeScenario()
    {
        ctx = new ClassPathXmlApplicationContext("share-po-context.xml");
        drone = (WebDrone) ctx.getBean("webDrone");
    }
    @After
    public void afterScenario()
    {
        if(drone != null)
        {
            drone.quit();
        }
    }
    @Given("^I am a user$")
    public void iAmAUser() throws Throwable 
    {
        StepsUtil.login(drone, "admin", "admin");
    }
    @When("^I search for \"(.*?)\"$")
    public void iSearchFor(final String term) throws Throwable 
    {
        SharePage page = drone.getCurrentPage().render();
        resultPage = page.getNav().getSearch().search(term).render();
    }
    
    @Then("^I should see search results$")
    public void iShouldSeeSearchResults() throws Throwable
    {
        Assert.assertNotNull(resultPage);
        resultPage = drone.getCurrentPage().render();
        Assert.assertNotNull(resultPage.getFacetGroups());
    }
    
    @Then("^There should be a count of the number of results$")
    public void thereShouldBeACountOfTheNumberOfResults() throws Throwable 
    {
        Assert.assertEquals(resultPage.getResultCount(),resultPage.getResults().size());
    }
    
    @Then("^the results should show the correct data$")
    public void theResultsShouldShowTheCorrectData() throws Throwable 
    {
        Assert.assertEquals(resultPage.getResults().size(), 6);
        Assert.assertEquals(resultPage.getResults().get(0).getTitle(),"(Project Overview.ppt)");
        Assert.assertEquals(resultPage.getResults().get(5).getTitle(),"(Meeting Notes 2011-01-27.doc)");
    }
    
    @Then("^Clicking on a result takes me to the document details page for that node\\.$")
    public void clickingOnAResultTakesMeToTheDocumentDetailsPageForThatNode() throws Throwable 
    {
        DocumentDetailsPage page = resultPage.getResults().get(0).clickLink().render();
        Assert.assertEquals(page.isDocumentDetailsPage(), true);
    }
    
    @Given("^I am viewing faceted search results$")
    public void iAmViewingFacetedSearchResults() throws Throwable 
    {
        iAmAUser();
        iSearchFor("ipsum");
        iShouldSeeSearchResults();
    }
    
    @When("^I click on \"(.*?)\" facet$")
    public void iClickOnFacet(String facetName) throws Throwable 
    {
        resultPage = resultPage.selectFacet(facetName).render();
    }
    
    @Then("^the search results are filtered to show only those with the appropriate facet\\.$")
    public void theSearchResultsAreFilteredToShowOnlyThoseWithTheAppropriateFacet() throws Throwable
    {
        for(SearchResult result:resultPage.getResults())
        {
            Assert.assertTrue(result.getTitle().endsWith(".doc)"));
        }
    }
    
    @When("^I look in the facet panel I should see facet categories \"(.*?)\"$")
    public void iLookInTheFacetPanelIShouldSeeFacetCategories(List<String> categories) throws Throwable 
    {
        facetGroups = resultPage.getFacetGroups();
        Assert.assertNotNull(facetGroups);
        List<String> actualCategories = new ArrayList<String>();
        for(FacetedSearchFacetGroup group : facetGroups)
        {
            System.out.println("adding:"+group.getGroupLabel());
            actualCategories.add(group.getGroupLabel());
        }
        Assert.assertEquals(actualCategories, categories);
    }
    @When("^those facets have accurate counts:$")
    public void thoseFacetsHaveAccurateCounts(DataTable expectedData) throws Throwable 
    {
        Map<String,Integer> facetHit = new LinkedHashMap<String,Integer>();
        for(FacetedSearchFacetGroup facetGroup : facetGroups)
        {
            List<FacetedSearchFacet> facets = facetGroup.getFacets();
            for(FacetedSearchFacet facet: facets)
            {
                String label = facet.getLabel();
                Integer count = Integer.valueOf(facet.getHits());
                facetHit.put(label, count);
            }
        }
        Assert.assertEquals(facetHit, expectedData.asMap(String.class, Integer.class));
    }
}
