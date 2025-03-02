package pl.akademiaqa.handlers.trello.authentication;

import groovy.lang.Singleton;
import lombok.SneakyThrows;

import java.util.Properties;

@Singleton
public class TrelloAuthentication {

    private final static Properties PROPERTIES = new Properties();
    private final static String KEY = "key";
    private final static String TOKEN = "token";

    @SneakyThrows
    public TrelloAuthentication() {
        PROPERTIES.load(getClass().getClassLoader().getResourceAsStream("trello.properties"));
    }

    public String getKey() {
        return PROPERTIES.getProperty(KEY);
    }

    public String getToken() {
        return PROPERTIES.getProperty(TOKEN);
    }

}
