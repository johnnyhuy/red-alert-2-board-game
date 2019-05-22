package oosd.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class GameSetupReader {
    private JSONObject json;

    public GameSetupReader() {
        json = getJsonObject();
    }

    public int getBoardColumns() {
        return (int) json.get("board_columns");
    }

    public int getBoardRows() {
        return (int) json.get("board_rows");
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
}
