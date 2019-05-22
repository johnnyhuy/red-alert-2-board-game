package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

public class JsonGameSetupFactory implements GameSetupFactory {
    @Override
    public Board createBoard(int boardColumns, int boardRows) {return null;

    }

    @Override
    public List<Player> createPlayers(Board board) {
        return null;
    }
}
