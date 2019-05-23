package oosd.factories;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.game.JsonGameSetupReader;
import oosd.models.player.Player;

import java.util.List;

public class JsonGameSetupFactory implements GameSetupFactory {
    private final JsonGameSetupReader gameSetupReader;

    public JsonGameSetupFactory() {
        gameSetupReader = new JsonGameSetupReader();
    }

    @Override
    public Board createBoard(int boardColumns, int boardRows) {
        return new GameBoard(gameSetupReader.getBoardColumns(), gameSetupReader.getBoardRows());
    }

    @Override
    public List<Player> createPlayers(Board board) {
        return gameSetupReader.getPlayers(board);
    }
}
