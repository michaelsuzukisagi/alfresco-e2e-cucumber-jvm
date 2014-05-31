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
package org.alfresco.share;

import org.alfresco.po.share.DashBoardPage;
import org.alfresco.po.share.LoginPage;
import org.alfresco.webdrone.WebDrone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Steps to enable login feature.
 * @author Michael Suzuki
 *
 */
public class LoginSteps
{
    WebDrone drone;
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
    
    @Given("^I am admin$")
    public void iAmAdmin() throws Throwable 
    {
        //Do nothing as admin user comes with default installation of alfresco.
    }

    @When("^I navigate to alfresco$")
    public void navigateToLogin() throws Throwable
    {
        drone.navigateTo("http://localhost:8080/share");
    }

    @And("^I login as \"(.*?)\" with password \"(.*?)\"$")
    public void loginAsUsernameWitPassword(String username, String password) throws Throwable
    {
        LoginPage loginPage = drone.getCurrentPage().render();
        loginPage.loginAs(username, password);
    }

    @Then("^i should see the dashboard page$")
    public void iShouldSeeTheDashboard_page() throws Throwable
    {
        DashBoardPage result = drone.getCurrentPage().render();
        Assert.assertNotNull(result);
    }
    @Then("^i should see the login page$")
    public void i_should_see_the_login_page() throws Throwable 
    {
        LoginPage result = drone.getCurrentPage().render();
        Assert.assertNotNull(result);
    }
}
