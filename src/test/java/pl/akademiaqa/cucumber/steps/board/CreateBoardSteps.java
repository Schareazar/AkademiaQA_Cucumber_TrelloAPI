package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.trello.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class CreateBoardSteps {

    private final CreateRequest createRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("User creates a new board")
    public void user_creates_a_new_board() {
        createBoard(CommonValues.BOARDNAME);
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    //my solution
    @When("User creates a new board with name starting with a special sign")
    public void user_creates_a_new_board_with_name_starting_with_a_special_sign() {
        createBoard(CommonValues.SPECIALBOARDNAME);
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    //kita solution
    @When("User creates a new board with name {string}")
    public void user_creates_a_new_board_with_name(String boardName) {
        createBoard(boardName);
    }

    @When("User tries to create a new board without a name")
    public void user_tries_to_create_a_new_board_without_a_name() {
        createBoard(CommonValues.EMPTY);
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        Assertions.assertThat(responseHandler.getMessage()).isEqualTo("invalid value for name");
    }

    @When("User tries to create a new board when not authenticated")
    public void user_tries_to_create_a_new_board_when_not_authenticated() {
        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addQueryParam("name", CommonValues.BOARDNAME);

        responseHandler.setResponse(createRequest.create(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    @Given("the board exists")
    public void the_board_exists() {
        createBoard(CommonValues.BOARDNAME);
    }

    private void createBoard(String boardName) {
        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addQueryParam("name", boardName);

        responseHandler.setResponse(createRequest.create(requestHandler));

        context.addBoard(boardName, responseHandler.getId());
    }

}
