package edu.austral.mica.application;

import edu.austral.mica.persistence.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class WindowReader {

    public static Integer readQuantityOfPlayers() {
        try {
            Object obj = new JSONParser().parse(new FileReader(Constants.INITIAL_CONFIG_FILE_PATH));
            JSONObject initialConfigJson = (JSONObject) obj;
            JSONObject window = (JSONObject) initialConfigJson.get("window");
            Long numberOfPlayers = (Long) window.get("players");
            return Math.toIntExact(numberOfPlayers);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer readWidth() {
        try {
            Object obj = new JSONParser().parse(new FileReader(Constants.INITIAL_CONFIG_FILE_PATH));
            JSONObject initialConfigJson = (JSONObject) obj;
            JSONObject window = (JSONObject) initialConfigJson.get("window");
            Long numberOfPlayers = (Long) window.get("width");
            return Math.toIntExact(numberOfPlayers);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer readHeight() {
        try {
            Object obj = new JSONParser().parse(new FileReader(Constants.INITIAL_CONFIG_FILE_PATH));
            JSONObject initialConfigJson = (JSONObject) obj;
            JSONObject window = (JSONObject) initialConfigJson.get("window");
            Long numberOfPlayers = (Long) window.get("height");
            return Math.toIntExact(numberOfPlayers);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
