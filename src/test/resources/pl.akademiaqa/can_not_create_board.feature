Feature:

  Scenario:
    Given User is not authenticated on Trello
    When User tries to create a new board when not authenticated
    Then An error is returned when trying to access the board

  Scenario:
    Given User is authenticated on Trello
    When  User tries to create a new board without a name
    Then An error is returned when trying to access the board