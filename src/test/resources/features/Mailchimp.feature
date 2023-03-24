Feature: Mailchimp

  Scenario Outline: Sign up
    Given I use "<browser>"
    When I entered "<email>" address
    When I have "<username>"
    When I have entered "<password>"
    When I click Sign Up
    Then I can "<create>" an account


  Examples:
    | browser | email                    | username | password      | create |
    | chrome  | arianasinisalu@gmail.com | newName  | marianaJunx5! | yes    |
    | safari  | arianasinisalu@gmail.com | long     | marianaJunx5! | no     |
    | safari  | arianasinisalu@gmail.com | taken    | marianaJunx5! | no     |
    | chrome  |                          | newName  | marianaJunx5! | no     |