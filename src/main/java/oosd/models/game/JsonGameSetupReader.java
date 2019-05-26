package oosd.models.game;

import oosd.models.board.Board;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import oosd.models.units.allied.GrizzlyTank;
import oosd.models.units.allied.Harrier;
import oosd.models.units.soviet.Conscript;
import oosd.models.units.soviet.KirovAirship;
import oosd.models.units.soviet.RhinoTank;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;
import java.util.concurrent.Callable;

import static oosd.helpers.NumberHelper.toInt;
import static oosd.helpers.ObjectHelper.isNull;

/**
 * Design pattern: facade pattern used here to abstract the JSON logic
 * to fetch game setup details such as board sizes.
 */
public class JsonGameSetupReader {
    private JSONObject root;

    public JsonGameSetupReader() {
        root = getJsonObject();
    }

    public int getBoardColumns() {
        return getNumber("board_columns");
    }

    public int getBoardRows() {
        return getNumber("board_rows");
    }

    public List<Player> getPlayers(Board board) {
        Iterator iterator = getJsonArray(root, "players").iterator();
        List<Player> players = new ArrayList<>();

        while (iterator.hasNext()) {
            JSONObject playerObject = (JSONObject) iterator.next();
            String playerName = getString(playerObject, "name");
            Player player = new Player(playerName);

            players.add(player);
            setupUnits(playerObject, board, player);
        }

        return players;
    }

    private void setupUnits(JSONObject playerObject, Board board, Player player) {
        for (Object unitObject : getJsonArray(playerObject, "units")) {
            JSONObject playerUnit = (JSONObject) unitObject;
            Map<String, Callable<Unit>> unitMap = getUnitMap(player);

            for (String unitName : unitMap.keySet()) {
                JSONArray unitLocations = (JSONArray) playerUnit.get(unitName);

                if (isNull(unitLocations)) {
                    continue;
                }

                try {
                    Unit newUnit = unitMap.get(unitName).call();
                    player.addUnit(newUnit);

                    for (Object unitLocationObject : unitLocations) {
                        JSONObject unitLocation = (JSONObject) unitLocationObject;
                        int column = Integer.parseInt((String) unitLocation.get("column"));
                        int row = Integer.parseInt((String) unitLocation.get("row"));

                        board.getPiece(column, row).setUnit(newUnit);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Map<String, Callable<Unit>> getUnitMap(Player player) {
        return Map.of(
            "conscript", () -> new Conscript(player),
            "kirov_airship", () -> new KirovAirship(player),
            "rhino_tank", () -> new RhinoTank(player),
            "gi_soldier", () -> new GISoldier(player),
            "grizzly_tank", () -> new GrizzlyTank(player),
            "harrier", () -> new Harrier(player)
        );
    }

    private int getNumber(String field) {
        return toInt((Long) root.get(field));
    }

    private String getString(JSONObject object, String field) {
        return (String) object.get(field);
    }

    private JSONArray getJsonArray(JSONObject object, String field) {
        return (JSONArray) object.get(field);
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
