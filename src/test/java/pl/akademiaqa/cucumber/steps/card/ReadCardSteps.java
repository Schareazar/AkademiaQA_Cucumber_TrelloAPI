package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.ReadRequest;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.trello.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class ReadCardSteps {

    private final Context context;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final ReadRequest readRequest;

    @Then("User can see {string} on {string}")
    public void user_can_see_on(String cardName, String listName) {
        String cardId = context.getCards().get(cardName);
        String listId = context.getLists().get(listName);

        requestHandler.setEndpoint(TrelloUrls.CARDS);
        requestHandler.addPathParam("id", cardId);

        responseHandler.setResponse(readRequest.read(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(responseHandler.getResponse().getBody().jsonPath().
                getString("idList")).isEqualTo(listId);
    }
}
