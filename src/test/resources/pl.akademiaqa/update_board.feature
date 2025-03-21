Feature: Updating board

  @cleanup
  Scenario: User can update board name
    Given User is authenticated on Trello
    And the board exists
    When User changes board name to "UPDATED NAME"
    Then User can see board with updated name "UPDATED NAME"