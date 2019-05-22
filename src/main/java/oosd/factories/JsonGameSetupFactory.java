package oosd.factories;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.game.GameSetupReader;
import oosd.models.player.Player;

import java.util.List;

public class JsonGameSetupFactory implements GameSetupFactory {
    private final GameSetupReader gameSetupReader;

    public JsonGameSetupFactory() {
        gameSetupReader = new GameSetupReader();
    }

    @Override
    public Board createBoard(int boardColumns, int boardRows) {
        Board board = new GameBoard(gameSetupReader.getBoardColumns(), gameSetupReader.getBoardRows());

        return board;
    }

    @Override
    public List<Player> createPlayers(Board board) {
        return null;
    }
}
