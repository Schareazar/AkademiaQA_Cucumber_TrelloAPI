package pl.akademiaqa.api.trello.boards;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.akademiaqa.urls.TrelloUrls;

import java.util.Map;

public class BaseRequest {

    public RequestSpecification requestSetup(Map<String, String> queryParams) {
        return new RequestSpecBuilder()
                .setBaseUri(TrelloUrls.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .build();
    }

    public RequestSpecification requestSetup(Map<String, String> queryParams, Map<String,String> pathParams) {
        return new RequestSpecBuilder()
                .setBaseUri(TrelloUrls.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .addPathParams(pathParams)
                .build();
    }

}
