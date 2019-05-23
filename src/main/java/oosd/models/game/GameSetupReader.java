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
import java.lang.reflect.Constructor;
import java.util.*;

import static oosd.helpers.NumberHelper.toInt;
import static oosd.helpers.ObjectHelper.isNull;

/**
 * Design pattern: facade pattern used here to abstract the JSON logic
 * to fetch game setup details such as board sizes.
 */
public class GameSetupReader {
    private JSONObject root;

    private Map<String, String> unitMap;

    public GameSetupReader() {
        root = getJsonObject();
        unitMap = Map.of(
            "conscript", Conscript.class.getName(),
            "kirov_airship", KirovAirship.class.getName(),
            "rhino_tank", RhinoTank.class.getName(),
            "gi_soldier", GISoldier.class.getName(),
            "grizzly_tank", GrizzlyTank.class.getName(),
            "harrier", Harrier.class.getName()
        );
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

            for (Unit unit : getUnits(playerObject, board, player))
                players.add(player);
        }

        return players;
    }

    private List<Unit> getUnits(JSONObject playerObject, Board board, Player player) {
        Class<?> className;

        for (Object unitObject : getJsonArray(playerObject, "units")) {
            JSONObject unit = (JSONObject) unitObject;

            for (String unitName : unitMap.keySet()) {
                JSONArray unitLocations = (JSONArray) unit.get(unitName);

                if (isNull(unitLocations)) {
                    continue;
                }

                for (Object unitLocationObject : unitLocations) {
                    JSONObject unitLocation = (JSONObject) unitLocationObject;
                    int column = Integer.parseInt((String) unitLocation.get("column"));
                    int row = Integer.parseInt((String) unitLocation.get("row"));

                    try {
                        className = Class.forName(unitMap.get(unitName));
                        Constructor<?> constructor = className.getConstructor(Player.class);
                        Object object = constructor.newInstance(player);
                        board.getPiece(column, row).setUnit(object);
                    } catch (Exception ignored) {
                        return new ArrayList<>();
                    }

                }

            }
        }

        return new ArrayList<>();
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
