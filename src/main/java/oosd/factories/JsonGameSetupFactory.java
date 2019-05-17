package oosd.factories;

import oosd.helpers.JsonGameSetupHelper;
import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.player.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class JsonGameSetupFactory implements GameSetupFactory {
    private JSONObject json = new JSONObject();

    public JsonGameSetupFactory() {
        try {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("game-config.json")).getFile());
            this.json = (JSONObject) parser.parse(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board createBoard() {
        return new GameBoard(JsonGameSetupHelper.getBoardColumns(json), JsonGameSetupHelper.getBoardRows(json));
    }

    @Override
    public List<Player> createPlayers(Board board) {
        return null;
    }
}
