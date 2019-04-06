package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.humans.Plane;
import oosd.models.units.humans.Soldier;
import oosd.models.units.humans.Tank;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;

    public GameEngine(int boardColumns, int boardRows) {
        this.board = new Board(boardColumns, boardRows);
    }

    /**
     * GRASP: The creator
     * Responsible to initialize game pieces and players in the board.
     */
    public void initialize() {
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        board.getHexagon(0, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(1, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(2, 0).setUnit(new Tank(playerOne));
        board.getHexagon(3, 0).setUnit(new Tank(playerOne));
        board.getHexagon(4, 0).setUnit(new Plane(playerOne));
        board.getHexagon(5, 0).setUnit(new Plane(playerOne));
        board.getHexagon(6, 0).setUnit(new Tank(playerOne));
        board.getHexagon(7, 0).setUnit(new Tank(playerOne));
        board.getHexagon(8, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(9, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(0, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(1, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(2, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(3, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(4, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(5, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(6, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(7, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(8, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(9, 9).setUnit(new ScoutZombie(playerTwo));
    }

    /**
     * Get the game board.
     *
     * @return board that contains the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the selected hexagon user clicks.
     *
     * @return selected hexagon
     */
    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }

    /**
     * Set the selected hexagon on in the game.
     *
     * @param selectedHexagon selected hexagon
     */
    public void setSelectedHexagon(Hexagon selectedHexagon) {
        this.selectedHexagon = selectedHexagon;
    }
}
