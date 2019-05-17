package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

public class JsonConfigFactory extends ConfigFactory {
    public Board createBoard() {
        return null;
    }

    @Override
    public List<Player> createPlayers(Board board) {
        return null;
    }
}
