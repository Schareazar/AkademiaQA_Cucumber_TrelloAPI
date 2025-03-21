package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.trello.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class UpdateBoardSteps {

    private final RequestHandler requestHandler;
    private final UpdateRequest updateRequest;
    private final Context context;
    private final ResponseHandler responseHandler;

    @When("User changes board name to {string}")
    public void user_changes_board_name_to(String boardName) {

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addPathParam("id", context.getBoards().get(CommonValues.BOARDNAME));
        requestHandler.addQueryParam("name", boardName);

        responseHandler.setResponse(updateRequest.update(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

    }

}
