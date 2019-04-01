package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.*;

public class GameEngine {
    private Board board;

    public GameEngine() {
        final int boardRow = 6;
        final int boardColumn = 6;

        this.board = new Board(boardColumn, boardRow);
    }

    public void initialize() {
        Hexagon[][] hexagons = this.board.getHexagons();
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        Unit tank = new Tank(hexagons[0][0], playerOne);
        Unit plane = new Plane(hexagons[1][0], playerOne);
        Unit soldier = new Soldier(hexagons[2][0], playerOne);
        Unit juggernautZombie = new JuggernautZombie(hexagons[0][5], playerOne);
        Unit scoutZombie = new ScoutZombie(hexagons[1][5], playerOne);
        Unit zombat = new Zombat(hexagons[2][5], playerOne);
    }

    public Board getBoard() {
        return this.board;
    }
}
