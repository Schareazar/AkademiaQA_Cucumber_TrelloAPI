Feature:

  Scenario: User can delete existing board

    Given User is authenticated on Trello
    And the board exists
    When User deletes existing board
    Then Board can't be accessed
