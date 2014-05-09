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
}
