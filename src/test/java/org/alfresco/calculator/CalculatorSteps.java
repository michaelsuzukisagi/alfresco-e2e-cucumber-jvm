package org.alfresco.calculator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
/**
 * Calculator feature steps.
 *
 * @author Michael Suzuki
 *
 */
public class CalculatorSteps
{
    private Calculator calculator;
    private int result;

    @Given("^I have a calculator")
    public void iHaveCalculator() throws Throwable
    {
        calculator = new Calculator();
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void calculatorAdd(int number1, int number2) throws Throwable
    {
        if(calculator != null)
        {
            result = calculator.addition(number1, number2);
        }
    }

    @Then("^outcome should be (\\d+)$")
    public void outShouldBe(int expected) throws Throwable
    {
        Assert.assertEquals(expected, result);
    }

    @When("^I subtract (\\d+) from (\\d+)$")
    public void iSubtractFrom(int arg1, int arg2) throws Throwable
    {
        result = calculator.subtraction(arg1, arg2);
    }
}
