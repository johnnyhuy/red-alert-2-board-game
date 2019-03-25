package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.ScoutZombie;
import oosd.models.units.Unit;

public class GameEngine {
    private Board board;

    public GameEngine() {
        this.board = new Board(6, 6);
    }

    public void initialize() {
        Hexagon[][] hexagons = this.board.getHexagons();
        Player playerOne = new Player("Johnny Dave", Team.RED);

        Unit scoutZombie = new ScoutZombie(hexagons[1][1], playerOne);
    }

    public Board getBoard() {
        return this.board;
    }
}
