package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.trello.authentication.TrelloAuthentication;
import pl.akademiaqa.handlers.api.RequestHandler;

@RequiredArgsConstructor
public class TrelloAuthenticationSteps {

    private final TrelloAuthentication trelloAuthentication;
    private final RequestHandler requestHandler;

    @Given("User is authenticated on Trello")
    public void user_is_authenticated_on_trello() {
        requestHandler.addQueryParam("key", trelloAuthentication.getKey());
        requestHandler.addQueryParam("token", trelloAuthentication.getToken());
    }
}
