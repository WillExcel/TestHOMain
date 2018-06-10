$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/resources/Registration.feature");
formatter.feature({
  "name": "As a user,",
  "description": "  I want to be able to\n  see registration details",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Vehicle Reg Details",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@TestCompleted"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "that I am on the Home page",
  "keyword": "Given "
});
formatter.match({
  "location": "RegistrationStep.thatIamOnTheHomePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click \"startNow\" button",
  "keyword": "And "
});
formatter.match({
  "location": "RegistrationStep.i_click_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter vehicle registration details",
  "keyword": "When "
});
formatter.match({
  "location": "RegistrationStep.iEnterVehicleRegistrationDetails()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click \"continue\" button",
  "keyword": "And "
});
formatter.match({
  "location": "RegistrationStep.i_click_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the make and colour is displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "RegistrationStep.theMakeAndColourIsDisplayed()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});