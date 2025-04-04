package pl.akademiaqa.handlers.trello.api;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class ResponseHandler {

    private Response response;

    public String getId() {
        return response.getBody().jsonPath().getString("id");
    }

    public String getMessage() {
        return response.getBody().jsonPath().getString("message");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
