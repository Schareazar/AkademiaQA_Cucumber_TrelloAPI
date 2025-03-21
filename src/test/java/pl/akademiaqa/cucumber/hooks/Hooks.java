package pl.akademiaqa.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.DeleteRequest;
import pl.akademiaqa.handlers.trello.api.RequestHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.handlers.trello.authentication.TrelloAuthentication;
import pl.akademiaqa.urls.TrelloUrls;

@RequiredArgsConstructor
public class Hooks {

    private final Context context;
    private final RequestHandler requestHandler;
    private final DeleteRequest deleteRequest;
    private final TrelloAuthentication trelloAuthentication;

    @BeforeStep(value = "@authenticated")
    public void clearRequest() {
        requestHandler.getQueryParams().clear();
        requestHandler.getPathParams().clear();
        requestHandler.setEndpoint(null);
        setAuthDetails();
    }

    @BeforeStep(value = "@not_authenticated")
    public void clearRequestAndAuth() {
        requestHandler.getQueryParams().clear();
        requestHandler.getPathParams().clear();
        requestHandler.setEndpoint(null);
    }

    @After(value = "@cleanup")
    public void afterScenario() {
        context.getBoards().values()
                .forEach(boardId ->
                {
                    requestHandler.setEndpoint(TrelloUrls.BOARDS);
                    requestHandler.addPathParam("id", boardId);
                    Response response = deleteRequest.delete(requestHandler);
                    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                });
    }

    private void setAuthDetails() {
        requestHandler.getQueryParams().put("key", trelloAuthentication.getKey());
        requestHandler.getQueryParams().put("token", trelloAuthentication.getToken());
    }
}
