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
package org.alfresco.calculator;

import org.junit.Assert;

import org.alfresco.calculator.Calculator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    
    @Given("^I have a calculator$")
    public void iHaveACalculator() throws Throwable 
    {
        calculator = new Calculator();
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void iAddAnd(int number1, int number2) throws Throwable 
    {
        result = calculator.addition(number1, number2);
    }

    @Then("^outcome should be (\\d+)$")
    public void outcomeShouldBe(int expected) throws Throwable 
    {
        Assert.assertEquals(result, expected);
    }
    @When("^I subtract (\\d+) from (\\d+)$")
    public void iSubtractFrom(int arg1, int arg2) throws Throwable 
    {
        result = calculator.subtraction(arg1, arg2);
    }
}
