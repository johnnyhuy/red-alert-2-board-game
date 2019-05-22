package oosd.models.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Objects;

import static oosd.helpers.NumberHelper.toInt;

/**
 * Design pattern: facade pattern used here to abstract the JSON logic
 * to fetch game setup details such as board sizes.
 */
public class GameSetupReader {
    private JSONObject json;

    public GameSetupReader() {
        json = getJsonObject();
    }

    public int getBoardColumns() {
        return getNumber("board_columns");
    }

    public int getBoardRows() {
        return getNumber("board_rows");
    }

    private int getNumber(String field) {
        return toInt((Long) json.get(field));
    }

    private JSONObject getJsonObject() {
        try {
            String file = Objects.requireNonNull(getClass().getClassLoader().getResource("game-setup.json")).getFile();
            return (JSONObject) new JSONParser().parse(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    public JSONObject getJson() {
        return json;
    }
}
