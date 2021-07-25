@Login
Feature: Login scenarios

  Scenario Outline: Verify login Functionality of Swag Lags
    Given User has already launched the application
    When User enters valid "<username>" and "<password>"
    And User clicks on Login button
    Then User should be succesfully logged in

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Verify the error message when user gives incorrect username and password
    Given User has already launched the application
    When User enters valid "<username>" and "<password>"
    And User clicks on Login button
    Then User should get the error message "<ErrorMessage>"

    Examples: 
      | username      | password   | ErrorMessage                                                              |
      | standard_user | secret_tt1 | Epic sadface: Username and password do not match any user in this service |
