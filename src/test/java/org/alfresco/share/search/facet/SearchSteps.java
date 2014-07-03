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

import org.alfresco.po.share.SharePage;
import org.alfresco.po.share.search.FacetedSearchPage;
import org.alfresco.po.share.site.document.DocumentDetailsPage;
import org.alfresco.share.StepsUtil;
import org.alfresco.webdrone.WebDrone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;

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
    
    @Before
    public void beforeScenario()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("share-po-context.xml");
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
    public void iSearchFor(final String term) throws Throwable {
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
        //TODO
    }

    @Then("^the results should show the correct data$")
    public void theResultsShouldShowTheCorrectData() throws Throwable 
    {
        //TODO
    }

    @Then("^Clicking on a result takes me to the document details page for that node\\.$")
    public void clickingOnAResultTakesMeToTheDocumentDetailsPageForThatNode() throws Throwable 
    {
        DocumentDetailsPage page = resultPage.getResults().get(0).clickLink().render();
        Assert.assertEquals(page.isDocumentDetailsPage(), true);
    }
}
