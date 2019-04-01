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

        hexagons[0][0].setUnit(new Tank(playerOne));
        hexagons[1][0].setUnit(new Plane(playerOne));
        hexagons[2][0].setUnit(new Soldier(playerOne));
        hexagons[3][0].setUnit(new Soldier(playerOne));
        hexagons[4][0].setUnit(new Soldier(playerOne));
        hexagons[5][0].setUnit(new Soldier(playerOne));
        hexagons[0][5].setUnit(new JuggernautZombie(playerTwo));
        hexagons[1][5].setUnit(new ScoutZombie(playerTwo));
        hexagons[2][5].setUnit(new Zombat(playerTwo));
        hexagons[3][5].setUnit(new ScoutZombie(playerTwo));
        hexagons[4][5].setUnit(new ScoutZombie(playerTwo));
        hexagons[5][5].setUnit(new ScoutZombie(playerTwo));
    }

    public Board getBoard() {
        return this.board;
    }
}
