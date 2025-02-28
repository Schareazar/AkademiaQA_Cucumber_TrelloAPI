package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class CreateBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;

    @When("User creates a new board")
    public void user_creates_a_new_board() {

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addQueryParam("name", "Cucumber board");

        Response createBoardResponse = createBoardRequest.createBoard(requestHandler);
        Assertions.assertThat(createBoardResponse.getStatusCode()).isEqualTo(200);
    }

    @Then("User can see board created on the board list")
    public void user_can_see_board_created_on_the_board_list() {

        //GET do API listy boardów /boards
        //sprawdzenie że dodany board jest na liście

    }

}
