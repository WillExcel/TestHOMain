Feature: As a user,
  I want to be able to
  see registration details


  @TestCompleted
  Scenario: Vehicle Reg Details
    Given that I am on the Home page
    And I click "startNow" button
    When I enter vehicle registration details
    And I click "continue" button
    Then the make and colour is displayed