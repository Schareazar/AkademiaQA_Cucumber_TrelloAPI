package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.ReadBoardRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class ReadBoardSteps {

    private final RequestHandler requestHandler;
    private final ReadBoardRequest readBoardRequest;
    private final Context context;

    @Then("User can see created board details")
    public void user_can_see_created_board_details() {
        Response readBoardResponse = readBoard(CommonValues.BOARDNAME);

        Assertions.assertThat(readBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(readBoardResponse.getBody().jsonPath().getString("name"))
                .isEqualTo(CommonValues.BOARDNAME);
    }

    @Then("User can see special board details")
    public void user_can_see_special_board_details() {
        Response readBoardResponse = readBoard(CommonValues.SPECIALBOARDNAME);

        Assertions.assertThat(readBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(readBoardResponse.getBody().jsonPath().getString("name"))
                .isEqualTo(CommonValues.SPECIALBOARDNAME);
    }

    @Then("Board can't be accessed")
    public void board_can_t_be_accessed() {
        Response readBoardResponse = readBoard(CommonValues.BOARDNAME);
        Assertions.assertThat(readBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Then("An error is returned when trying to access the board")
    public void an_error_is_returned_when_trying_to_access_the_board() {
        Response readBoardResponse = readBoard(CommonValues.BOARDNAME);
        Assertions.assertThat(readBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    private Response readBoard(String boardName) {
        String boardId = context.getBoards().get(boardName);

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addPathParam("id", boardId);
        return readBoardRequest.readBoard(requestHandler);
    }

}
