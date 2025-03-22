Feature: Reading board

  Scenario: User can read existing board data
    Given User is authenticated on Trello
    And the board exists
    When User opens board
    Then User can see created board details