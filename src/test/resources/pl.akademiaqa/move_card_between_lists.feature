Feature: Moving card between lists

  @authenticated
  @cleanup
  Scenario: User should be able to move card between lists
    Given User is authenticated on Trello
    And User creates a new board with name "MY BOARD"
    And User creates list "MY FIRST LIST" on "MY BOARD"
    And User creates card "MY FIRST CARD" on "MY FIRST LIST"
    And User creates list "MY SECOND LIST" on "MY BOARD"
    When User moves "MY FIRST CARD" to "MY SECOND LIST"
    Then User can see "MY FIRST CARD" on "MY SECOND LIST"

