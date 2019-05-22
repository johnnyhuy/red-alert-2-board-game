package oosd.models.game;

import oosd.models.player.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public List<Player> getPlayers() {
        Iterator iterator = getJsonArray("players").iterator();
        ArrayList<Player> players = new ArrayList<>();

        while (iterator.hasNext()) {
            JSONObject playerObject = (JSONObject) iterator.next();
            String playerName = (String) playerObject.get("name");
            players.add(new Player(playerName));
        }

        return players;
    }

    private int getNumber(String field) {
        return toInt((Long) json.get(field));
    }

    private JSONArray getJsonArray(String field) {
        return (JSONArray) json.get(field);
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
