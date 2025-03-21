package pl.akademiaqa.cucumber.steps.card;

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
public class CreateCardSteps {

    private final CreateRequest createRequest;
    private final Context context;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;

    @Given("User creates card {string} on {string}")
    public void user_creates_card_on(String cardName, String listName) {

        String listId = context.getLists().get(listName);

        requestHandler.setEndpoint(TrelloUrls.CARDS);
        requestHandler.addQueryParam("idList", listId);
        requestHandler.addQueryParam("name", cardName);

        responseHandler.setResponse(createRequest.create(requestHandler));
        context.addCard(cardName, responseHandler.getId());

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

    }
}
