Feature: Mailchimp

  Scenario Outline: Sign up
    Given I entered "<email>" address
    Given I have "<username>"
    Given I have entered "<password>"
    When I click Sign Up
    Then I can create an account


  Examples:
    | email                |   username        | password        |
    |marianasini@gmail.com |   marianajunx     | marianaJunx5!   |