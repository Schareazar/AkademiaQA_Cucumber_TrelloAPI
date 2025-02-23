package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;

public class CreateBoardSteps {

    CreateBoardRequest createBoardRequest = new CreateBoardRequest();

    @When("User creates a new board")
    public void user_creates_a_new_board() {

        Response createBoardResponse = createBoardRequest.createBoard();

        System.out.println(createBoardResponse.getStatusCode());
        //POST do API
        //sprawdzenie że response status code jest 201

    }
    @Then("User can see board created on the board list")
    public void user_can_see_board_created_on_the_board_list() {

        //GET do API listy boardów /boards
        //sprawdzenie że dodany board jest na liście

    }

}
