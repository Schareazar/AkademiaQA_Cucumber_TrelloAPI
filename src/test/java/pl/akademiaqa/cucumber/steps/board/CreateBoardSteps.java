package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class CreateBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;

    @When("User creates a new board")
    public void user_creates_a_new_board() {

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addQueryParam("name", CommonValues.BOARDNAME);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
