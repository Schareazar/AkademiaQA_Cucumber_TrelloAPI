package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.trello.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class UpdateCardSteps {

    private final Context context;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final UpdateRequest updateRequest;

    @When("User moves {string} to {string}")
    public void user_moves_to(String cardName, String listName) {

        String listId = context.getLists().get(listName);
        String cardId = context.getCards().get(cardName);

        requestHandler.setEndpoint(TrelloUrls.CARDS);
        requestHandler.addPathParam("id", cardId);
        requestHandler.addQueryParam("idList", listId);

        responseHandler.setResponse(updateRequest.update(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
