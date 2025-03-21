Feature: Attempting to create board when not possible

  Scenario: User can not create a new board when not authenticated
    Given User is not authenticated on Trello
    When User tries to create a new board when not authenticated
    Then An error is returned when trying to access the board

  Scenario: User can not create a new board with empty
    Given User is authenticated on Trello
    When  User tries to create a new board without a name
    Then An error is returned when trying to access the board