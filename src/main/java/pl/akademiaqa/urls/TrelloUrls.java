package pl.akademiaqa.urls;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrelloUrls {

    public static final String BASE_URL = "https://api.trello.com/1/";
    public static final String BOARDS = "boards/";
    public static final String LISTS = "lists/";
    public static final String CARDS = "cards/";

}
