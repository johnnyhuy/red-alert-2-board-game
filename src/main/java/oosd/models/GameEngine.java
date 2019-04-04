package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.*;

public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;

    public GameEngine() {
        final int boardRow = 10;
        final int boardColumn = 10;

        this.board = new Board(boardColumn, boardRow);
    }

    /**
     * GRASP: The creator
     * Responsible to initialize game pieces and players in the board.
     */
    public void initialize() {
        Hexagon[][] hexagons = this.board.getHexagons();
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        hexagons[0][0].setUnit(new Tank(playerOne));
        hexagons[1][0].setUnit(new Plane(playerOne));
        hexagons[2][0].setUnit(new Soldier(playerOne));
        hexagons[3][0].setUnit(new Soldier(playerOne));
        hexagons[4][0].setUnit(new Soldier(playerOne));
        hexagons[4][4].setUnit(new Zombat(playerOne));
        hexagons[0][9].setUnit(new Zombat(playerTwo));
        hexagons[1][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[2][9].setUnit(new Zombat(playerTwo));
        hexagons[3][9].setUnit(new JuggernautZombie(playerTwo));
        hexagons[4][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[5][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[6][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[7][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[8][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[9][9].setUnit(new ScoutZombie(playerTwo));
    }

    public Board getBoard() {
        return this.board;
    }

    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }

    public void setSelectedHexagon(Hexagon selectedHexagon) {
        this.selectedHexagon = selectedHexagon;
    }
}
