package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.DeleteBoardRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class DeleteBoardSteps {

    private final DeleteBoardRequest deleteBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("User deletes existing board")
    public void user_deletes_existing_board() {

        String boardId = context.getBoards().get(CommonValues.BOARDNAME);

        requestHandler.setEndpoint(TrelloUrls.BOARDS);
        requestHandler.addPathParam("id", boardId);

        responseHandler.setResponse(deleteBoardRequest.deleteBoard(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}
