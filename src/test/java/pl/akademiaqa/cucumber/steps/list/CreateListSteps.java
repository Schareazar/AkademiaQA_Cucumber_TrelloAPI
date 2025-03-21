package pl.akademiaqa.cucumber.steps.list;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.trello.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class CreateListSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;
    private final CreateRequest createRequest;

    @Given("User creates list {string} on {string}")
    public void user_creates_list_on(String listName, String boardName) {

        String idBoard = context.getBoards().get(boardName);

        requestHandler.setEndpoint(TrelloUrls.LISTS);
        requestHandler.addQueryParam("idBoard", idBoard);
        requestHandler.addQueryParam("name", listName);

        responseHandler.setResponse(createRequest.create(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addList(listName, responseHandler.getId());
    }
}
