package org.alfresco.share;

import cucumber.api.PendingException;
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

    @Given("^I am on login page$")
    public void navigateToLogin() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^login as \"(.*?)\" with password \"(.*?)\"$")
    public void loginAsUserWitPassword(String username, String password) throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    @Then("^i should see the dashboard page$")
    public void iShouldSeeTheDashboard_page() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
