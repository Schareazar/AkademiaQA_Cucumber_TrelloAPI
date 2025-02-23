package pl.akademiaqa.api.trello.boards;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {

    TrelloAuthentication trelloAuthentication = new TrelloAuthentication();

    public Response createBoard() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Cucumber Board");
        parameters.put("key", trelloAuthentication.getKey());
        parameters.put("token", trelloAuthentication.getToken());

        return given()
                .queryParams(parameters)
                .contentType(ContentType.JSON)
                .when()
                .post("https://api.trello.com/1/boards/")
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
