/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package nicebank;

import cucumber.api.java.en.*;

import org.junit.Assert;
import org.junit.Test;
import support.KnowsTheDomain;

public class TellerSteps {

  KnowsTheDomain helper;

  public TellerSteps(KnowsTheDomain helper) {
      this.helper = helper;
  }
      
  @When("^I withdraw \\$(\\d+)$")
  public void iWithdraw$(int amount) throws Throwable {
      try {
          helper.getTeller().withdrawFrom(helper.getMyAccount(), amount);
      } catch (IllegalStateException ex) {
          Assert.assertEquals("Nagative Balance -100", ex.getMessage());
          helper.setEx(ex);
          helper.setExMessage(ex.getMessage());
      }
  }

    @Then("^I should be told that I have insufficient funds in my account$")
    public void iShouldBeToldThatIHaveInsufficientFundsInMyAccount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(IllegalStateException.class, helper.getEx().getClass());
        Assert.assertEquals("Nagative Balance -100", helper.getEx().getMessage());
    }

}