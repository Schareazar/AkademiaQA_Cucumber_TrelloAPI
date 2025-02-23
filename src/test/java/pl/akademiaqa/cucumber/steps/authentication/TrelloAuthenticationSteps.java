package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Given;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;

public class TrelloAuthenticationSteps {

    TrelloAuthentication trelloAuthentication = new TrelloAuthentication();

    @Given("User is authenticated on Trello")
    public void user_is_authenticated_on_trello() {
    }

}
