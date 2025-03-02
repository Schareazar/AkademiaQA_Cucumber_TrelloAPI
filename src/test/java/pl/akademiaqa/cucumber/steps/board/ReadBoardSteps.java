package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.ReadBoardRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class ReadBoardSteps {

    private final RequestHandler requestHandler;
    private final ReadBoardRequest readBoardRequest;
    private final ResponseHandler responseHandler;

    @Then("User can see created board details")
    public void user_can_see_created_board_details() {

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addPathParam("id", responseHandler.getId());

        Response readBoardResponse = readBoardRequest.readBoard(requestHandler);

        Assertions.assertThat(readBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(readBoardResponse.getBody().jsonPath().getString("name"))
                .isEqualTo(CommonValues.BOARDNAME);
    }
}
