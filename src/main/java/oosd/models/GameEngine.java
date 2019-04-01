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

        Unit tank = new Tank(playerOne);
        Unit plane = new Plane(playerOne);
        Unit soldier = new Soldier(playerOne);
        Unit juggernautZombie = new JuggernautZombie(playerTwo);
        Unit scoutZombie = new ScoutZombie(playerTwo);
        Unit zombat = new Zombat(playerTwo);
    }

    public Board getBoard() {
        return this.board;
    }
}
