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
package org.alfresco.share.site;

import org.alfresco.po.share.SharePage;
import org.alfresco.po.share.site.CreateSitePage;
import org.alfresco.po.share.site.SiteDashboardPage;
import org.alfresco.share.StepsUtil;
import org.alfresco.webdrone.WebDrone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Steps to enable site creation feature.
 * 
 * @author Michael Suzuki
 *
 */
public class SiteSteps
{
    private ApplicationContext ctx;
    private WebDrone drone;
    
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

    @Given("^I am logged in as \"(.*?)\" with password \"(.*?)\"$")
    public void iAmLoggedInAsWithPassword(String username, String password) throws Throwable 
    {
        StepsUtil.login(drone, username, password);
    }

    @When("^I create a site$")
    public void iCreateASite() throws Throwable 
    {
        SharePage page = drone.getCurrentPage().render();
        CreateSitePage createSitePage = page.getNav().selectCreateSite().render();
        String name = "test" + System.currentTimeMillis();
        createSitePage.createNewSite(name);
    }

    @Then("^i should see the site dashboard page$")
    public void iShouldSeeTheSiteDashboardPage() throws Throwable 
    {
        SiteDashboardPage page = drone.getCurrentPage().render();
        Assert.assertNotNull(page);
    }

}
