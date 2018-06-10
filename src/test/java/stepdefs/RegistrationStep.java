package stepdefs;

import com.file.FileApplicationTests;
import util.AllDrivers;
import pages.RegistrationPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;



public class RegistrationStep extends FileApplicationTests{

     AllDrivers manageDriver = new AllDrivers();
     RegistrationPage registrationPage = new RegistrationPage();


    // I use Firefox but there is a flexibility in the code to use chrome, ie, safari etc.

      @Before
      public void startSession () {
            manageDriver.setDrivers("firefox");
        }

     @Given("^that I am on the Home page$")
     public void thatIamOnTheHomePage () throws Throwable {
          registrationPage = new RegistrationPage();
          registrationPage.launchUrl();
          registrationPage.assertHomePage();
        }

    @When("^I click \"([^\"]*)\" button$")
    public void i_click_button(String obj) throws Exception {
        if(obj.equalsIgnoreCase("startNow")){
            registrationPage.clickStartNowButton();
        } else if (obj.equalsIgnoreCase("continue")){
            registrationPage.clickRegPageContinueButton();
        }
    }
    @When("^I enter vehicle registration details$")
    public void iEnterVehicleRegistrationDetails () throws Throwable {
          registrationPage.enterRegDetailsTextField();
        }
     @Then("^the make and colour is displayed$")
     public void theMakeAndColourIsDisplayed () throws Throwable {
          registrationPage.verifyMake();
          registrationPage.verifyColour();
        }


        @After
        public void closeSession () throws IOException {
          manageDriver.takeScreenShot();
          manageDriver.closeBrowser();
        }

    }
