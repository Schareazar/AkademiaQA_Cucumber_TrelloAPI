Feature: Create new board

  @cleanup
  Scenario: User can create a new board with valid data

    Given User is authenticated on Trello
    When User creates a new board
    Then User can see created board details

  @cleanup
  Scenario: User can create a new board with name starting with a special sign

    Given User is authenticated on Trello
    When User creates a new board with name starting with a special sign
    Then User can see special board details

