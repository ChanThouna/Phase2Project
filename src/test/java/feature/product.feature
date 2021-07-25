@Product
Feature: Product Feature

  Background: 
    Given User has launched the SwagLags Application
    When User enters "standard_user" and "secret_sauce"
    And click on Login button

  Scenario Outline: Verify user is able to add single product in the cart
    Given User should be in the home page
    When User click on Add to Cart button of the item "<Product>"
    Then User should be able to see the added product "<Product>" in checkout page

    Examples: 
      | Product             |
      | Sauce Labs Backpack |

  Scenario: Verify if user is able to abb multiple products
    Given User should be in the home page
    When User clicks on Add to Cart button of multiple products
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Test.allTheThings() T-Shirt (Red) |
    Then All the added product should be in the checkout page
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Test.allTheThings() T-Shirt (Red) |
